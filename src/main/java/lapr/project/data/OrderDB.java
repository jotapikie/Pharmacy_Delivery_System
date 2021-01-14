/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import lapr.project.model.Invoice;
import lapr.project.model.Order;
import lapr.project.model.Product;
import lapr.project.utils.Constants;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Diogo
 */
public class OrderDB extends DataHandler{

    private OrderProductDB opdb = new OrderProductDB();
    private InvoiceDB idb = new InvoiceDB();
    private PharmacyStockDB ppdb = new PharmacyStockDB();
    
    
    public List<Order> getOrdersByStatus(int idPhamarcy, String status) throws SQLException {
        List<Order> listOrders = new ArrayList<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetOrdersByStatus(?, ?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, idPhamarcy);
            callStmt.setString(3, status);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                Order o = new Order(rs.getInt(1), rs.getTimestamp(2), rs.getTimestamp(3), rs.getString(4), rs.getFloat(5));
                listOrders.add(o);
            }
        }
        return listOrders;
    }

    public Order getOrder(int id) throws SQLException {
        if (id < 1) {
            throw new IllegalArgumentException("Invalid ID!");
        }
        Order o = null;
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetOrder(?)}")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, id);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                o = new Order(rs.getInt(1), rs.getTimestamp(2), rs.getTimestamp(3), rs.getString(4), rs.getFloat(5));
                return o;
            }
            throw new IllegalArgumentException("Product does not exist");
        }
    }

    public void setStatus(int idOrder, String status, int idPhamarcy) throws SQLException {
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procSetOrderStatus(?,?,?) }")) {
              callStmt.setInt(1, idOrder);
              callStmt.setString(2, status);
              callStmt.setInt(3, idPhamarcy);
              callStmt.execute();
        }
    }

    public Set<Order> getOrdersByPhamarcy(int idPhamarcy) throws SQLException {
        Set<Order> listOrders = new TreeSet<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetOrdersByPhamarcy(?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, idPhamarcy);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                Order o = new Order(rs.getInt(1), rs.getTimestamp(2), rs.getTimestamp(3), rs.getString(4), rs.getFloat(5));
                listOrders.add(o);
            }
        }
        return listOrders;
    }
    
    public int saveOrder(Order ord, String email, int idPharmacy) throws SQLException{
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcSaveProcessedOrder(?,?,?,?,?) }")) {
                callStmt.registerOutParameter(1, OracleTypes.INTEGER);
                callStmt.setTimestamp(2,ord.getBeginDate());
                callStmt.setString(3, ord.getStatus());
                callStmt.setDouble(4, ord.getPrice());
                callStmt.setString(5, email);
                callStmt.setInt(6, idPharmacy);
                

                callStmt.execute();
                return callStmt.getInt(1);
        }
    }
    
    public int saveToFullfillOrder(Order ord, int idPharmacy, int idOrder) throws SQLException{
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcSaveProcessingOrder(?,?,?,?) }")) {
                callStmt.registerOutParameter(1, OracleTypes.INTEGER);
                callStmt.setTimestamp(2,ord.getBeginDate());
                callStmt.setString(3, ord.getStatus());
                callStmt.setInt(4, idPharmacy);
                callStmt.setInt(5, idOrder);
                

                callStmt.execute();
                return callStmt.getInt(1);
        }
    }



    public void saveOrderProcessed(Order ord, String email, int idPharmacy, Invoice invoice) throws SQLException {
        int orderId = saveOrder(ord, email, idPharmacy);
        saveProducts(ord, orderId);
        idb.saveInvoice(invoice, orderId);
        ppdb.updateStock(idPharmacy, ord.getProducts());
        
    }
       
    
    
    private void saveProducts(Order ord,int idOrder) throws SQLException{
       Map<Product, Integer> products = ord.getProducts();
       for(Product p : products.keySet()){
           opdb.saveOrderProduct(idOrder, p.getId(), products.get(p));
       }
    }
    
   public Order newOrder(double totalPrice, HashMap<Product, Integer> items) {
        return new Order(Constants.DEFAULT_ID, totalPrice, items);
    }


    public Order newOrder(Order ord, HashMap<Product, Integer> missingProducts) {
         return new Order(missingProducts, ord);
    }

    public void saveOrderProcessing(Order orderToFulfill,String email, int idPharmacy,int idPharmacy1, Invoice invoice) throws SQLException {
        int orderId = saveOrder(orderToFulfill.getAssociatedOrder(), email,idPharmacy1 );
        int otherOrderId = saveToFullfillOrder(orderToFulfill, idPharmacy, orderId);
        saveProducts(orderToFulfill.getAssociatedOrder(), orderId);
        saveProducts(orderToFulfill, otherOrderId);
        idb.saveInvoice(invoice, orderId);
        ppdb.updateStock(idPharmacy, orderToFulfill.getProducts());
        ppdb.updateStock(idPharmacy1, orderToFulfill.getAssociatedOrder().getProducts());
    }


    
    
    
    
}
