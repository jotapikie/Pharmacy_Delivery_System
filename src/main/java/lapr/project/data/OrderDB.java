/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.BatchUpdateException;
import java.sql.CallableStatement;
import java.sql.Connection;
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
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetOrdersByStatus(?,?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, idPhamarcy);
            callStmt.setString(3, status);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                Order o = new Order(rs.getInt(1), rs.getTimestamp(2), rs.getTimestamp(3), rs.getString(4), rs.getFloat(5), new Address(rs.getString(6),new GeographicalPoint(rs.getDouble(7), rs.getDouble(8), rs.getDouble(9)), rs.getString(10), rs.getInt(12), rs.getString(11)));
                HashMap<Product, Integer> products = opdb.getProductsByOrder(o.getId());
                o.setProducts(products);
                listOrders.add(o);
            }
            verifyAssociatedOrders(idPhamarcy, status, listOrders);
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
                o = new Order(rs.getInt(1), rs.getTimestamp(2), rs.getTimestamp(3), rs.getString(4), rs.getDouble(5), new Address(rs.getString(6),new GeographicalPoint(rs.getDouble(7), rs.getDouble(8), rs.getDouble(9), rs.getString(10)), rs.getString(11), rs.getInt(13), rs.getString(12)));
                HashMap<Product, Integer> products = opdb.getProductsByOrder(o.getId());
                o.setProducts(products);
                return o;
            }
            Order res = getAssociatedOrder(id);
            if(res == null){
                throw new IllegalArgumentException("No order with that id.");
            }
            return res;
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
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcSaveProcessingOrder(?,?,?,?,?) }")) {
                callStmt.registerOutParameter(1, OracleTypes.INTEGER);
                callStmt.setTimestamp(2,ord.getBeginDate());
                callStmt.setString(3, ord.getStatus());
                callStmt.setDouble(4, ord.getPrice());
                callStmt.setInt(5, idPharmacy);
                callStmt.setInt(6, idOrder);
                

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
        orderToFulfill.setStatus("Processed");
        double price = 0;
        for(Map.Entry<Product, Integer> p : orderToFulfill.getProducts().entrySet()){
            price = price + (p.getKey().getPrice()*p.getValue());
        }
        orderToFulfill.setPrice(price);
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
                Order o = new Order(rs.getInt(1), rs.getTimestamp(2), rs.getTimestamp(3), rs.getString(4), rs.getFloat(5), new Address(rs.getString(6),new GeographicalPoint(rs.getDouble(7), rs.getDouble(8), rs.getDouble(9), rs.getString(10)),rs.getString(11), rs.getInt(13), rs.getString(12)));
                listOrders.add(o);
            }
        }
        verifyAssociated(idDelivery, listOrders);
        return listOrders;
    }

    public int assignDelivery(List<Order> orders, int runID) throws SQLException {
        Connection con = getConnection();
        int[] rows;
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procAssignDeliveryRun(?,?) }")) {
            for (Order o : orders) {
                callStmt.setInt(1, o.getId());
                callStmt.setInt(2, runID);

                callStmt.addBatch();
            }

            con.setAutoCommit(false);

            try {
                rows = callStmt.executeBatch();
                con.commit();
            } catch (BatchUpdateException e) {
                con.rollback();
                throw new SQLException(e.getNextException());
            } finally {
                con.setAutoCommit(true);
            }

            return rows.length;
    }
}

    private Order getAssociatedOrder(int id) throws SQLException {
         if (id < 1) {
            throw new IllegalArgumentException("Invalid ID!");
        }
        Order o = null;
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetAssociatedOrder(?)}")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, id);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                o = new Order(rs.getInt(1), rs.getTimestamp(2), rs.getTimestamp(3), rs.getString(4), rs.getDouble(5), new Address(rs.getString(6),new GeographicalPoint(rs.getDouble(7), rs.getDouble(8), rs.getDouble(9), rs.getString(10)), rs.getString(11), rs.getInt(13), rs.getString(12)));
                HashMap<Product, Integer> products = opdb.getProductsByOrder(o.getId());
                o.setProducts(products);
                return o;
            }
    }
        return null;
    }

    private void verifyAssociatedOrders(int idPhamarcy, String status, List<Order> listOrders) throws SQLException {
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetAssociatedOrderByStatus(?,?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, idPhamarcy);
            callStmt.setString(3, status);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                Order o = new Order(rs.getInt(1), rs.getTimestamp(2), rs.getTimestamp(3), rs.getString(4), rs.getDouble(5), new Address(rs.getString(6),new GeographicalPoint(rs.getDouble(7), rs.getDouble(8), rs.getDouble(9), rs.getString(10)), rs.getString(11), rs.getInt(13), rs.getString(12)));
                HashMap<Product, Integer> products = opdb.getProductsByOrder(o.getId());
                o.setProducts(products);
                listOrders.add(o);
            }
        }
        
        
    }

    private void verifyAssociated(int idDelivery, List<Order> listOrders) throws SQLException {
         try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetAssociatedOrderByRun(?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, idDelivery);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                Order o = new Order(rs.getInt(1), rs.getTimestamp(2), rs.getTimestamp(3), rs.getString(4), rs.getDouble(5), new Address(rs.getString(6),new GeographicalPoint(rs.getDouble(7), rs.getDouble(8), rs.getDouble(9), rs.getString(10)), rs.getString(11), rs.getInt(13), rs.getString(12)));
                HashMap<Product, Integer> products = opdb.getProductsByOrder(o.getId());
                o.setProducts(products);
                listOrders.add(o);
            }
        }
    }

    
}
