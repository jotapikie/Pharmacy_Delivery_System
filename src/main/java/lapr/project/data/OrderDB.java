/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import lapr.project.model.*;
import lapr.project.utils.Constants;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Diogo
 */
public class OrderDB extends DataHandler{

    private final OrderProductDB opdb = new OrderProductDB();
    private final InvoiceDB idb = new InvoiceDB();
    private final PharmacyStockDB ppdb = new PharmacyStockDB();
    private final ClientDB cdb = new ClientDB();
    
    
    public List<Order> getOrdersByStatus(int idPhamarcy, String status) throws SQLException {
        List<Order> listOrders = new ArrayList<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetOrdersByStatus(?, ?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, idPhamarcy);
            callStmt.setString(3, status);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                Order o = new Order(rs.getInt(1), rs.getTimestamp(2), rs.getTimestamp(3), rs.getString(4), rs.getFloat(5), new Address(rs.getString(6),new GeographicalPoint(rs.getDouble(7), rs.getDouble(8), rs.getDouble(9)), rs.getString(10), rs.getInt(11), rs.getString(12)));
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
                o = new Order(rs.getInt(1), rs.getTimestamp(2), rs.getTimestamp(3), rs.getString(4), rs.getFloat(5), new Address(rs.getString(6),new GeographicalPoint(rs.getDouble(7), rs.getDouble(8), rs.getDouble(9)), rs.getString(10), rs.getInt(11), rs.getString(12)));
                return o;
            }
            throw new IllegalArgumentException("Product does not exist");
        }
    }

    public boolean setStatus(int idOrder, String status, int idPhamarcy) {
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procSetOrderStatus(?,?,?) }")) {
              callStmt.setInt(1, idOrder);
              callStmt.setString(2, status);
              callStmt.setInt(3, idPhamarcy);
              callStmt.execute();
              return true;
        }catch(SQLException e){
            return false;
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
                Order o = new Order(rs.getInt(1), rs.getTimestamp(2), rs.getTimestamp(3), rs.getString(4), rs.getFloat(5), new Address(rs.getString(6),new GeographicalPoint(rs.getDouble(7), rs.getDouble(8), rs.getDouble(9)), rs.getString(10), rs.getInt(11), rs.getString(12)));
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



    public void saveOrderProcessed(Order ord, Client cli, int idPharmacy, Invoice invoice) throws SQLException {
        int orderId = saveOrder(ord, cli.getEmail(), idPharmacy);
        saveProducts(ord, orderId);
        idb.saveInvoice(invoice, orderId);
        cdb.updateAfterOrder(cli);
        ppdb.updateStockAfterSale(idPharmacy, ord.getProducts());
        
    }
       
    
    
    private void saveProducts(Order ord,int idOrder) throws SQLException{
       Map<Product, Integer> products = ord.getProducts();
       for(Product p : products.keySet()){
           opdb.saveOrderProduct(idOrder, p.getId(), products.get(p));
       }
    }
    
   public Order newOrder(double totalPrice, Map<Product, Integer> items) {
        Order res = new Order(); res.setPrice(totalPrice);res.setProducts(items);res.setBeginDate(Timestamp.from(Instant.now()));
        return res;
    }


    public Order newOrder(Order ord, Map<Product, Integer> missingProducts) {
         Order res = new Order(); res.setProducts(missingProducts);res.setAssociatedOrder(ord);res.setBeginDate(Timestamp.from(Instant.now()));
         return res;
    }

    public void saveOrderProcessing(Order orderToFulfill,Client cli, int idPharmacy,int idPharmacy1, Invoice invoice) throws SQLException {
        int orderId = saveOrder(orderToFulfill.getAssociatedOrder(), cli.getEmail(),idPharmacy1 );
        int otherOrderId = saveToFullfillOrder(orderToFulfill, idPharmacy, orderId);
        saveProducts(orderToFulfill.getAssociatedOrder(), orderId);
        saveProducts(orderToFulfill, otherOrderId);
        idb.saveInvoice(invoice, orderId);
        cdb.updateAfterOrder(cli);
        ppdb.updateStockAfterSale(idPharmacy, orderToFulfill.getProducts());
        ppdb.updateStockAfterSale(idPharmacy1, orderToFulfill.getAssociatedOrder().getProducts());
    }

    List<Order> getOrdersByDeliveryRun(int idDelivery) throws SQLException {
        List<Order> listOrders = new ArrayList<>();
        getConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetOrdersByDeliveryRun(?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, idDelivery);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt(1));
                o.setBeginDate(rs.getTimestamp(2));
                o.setStatus(rs.getString(3));
                o.setPrice(rs.getDouble(4));
                listOrders.add(o);
            }
        }
        return listOrders;
    }
}
