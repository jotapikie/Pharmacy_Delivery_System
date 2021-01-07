/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Order;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Diogo
 */
public class OrderDB extends DataHandler{

    public List<Order> getReadyToPrepareOrders(String administratorEmail) throws SQLException {
        List<Order> listOrders = new ArrayList<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetReadyToPrepareOrders(?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, administratorEmail);
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

    public void setStatus(int id, String status) throws SQLException {
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procSetOrderStatus(?,?) }")) {
              callStmt.setInt(1, id);
              callStmt.setString(2, status);
              callStmt.execute();
        }
    }

    public Iterable<Order> getAllOrderIDPreparing() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setOrderToReady() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
