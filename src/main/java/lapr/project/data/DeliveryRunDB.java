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
import lapr.project.model.DeliveryRun;
import lapr.project.model.Order;
import lapr.project.model.Product;
import lapr.project.utils.Constants;
import lapr.project.utils.route.Route;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Diogo
 */
public class DeliveryRunDB extends DataHandler{
    
    private final OrderDB odb = new OrderDB();
    private final OrderProductDB opdb = new OrderProductDB();
    

    public List<DeliveryRun> getDeliveryRuns(int idPharmacy) throws SQLException {
        List<DeliveryRun> listRuns = new ArrayList<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetDeliveryRuns(?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, idPharmacy);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                int idDelivery = rs.getInt(1);
                List<Order> orders = odb.getOrdersByDeliveryRun(idDelivery);
                for(Order o : orders){
                    HashMap<Product, Integer> products = opdb.getProductsByOrder(o.getId());
                    o.setProducts(products);
                }
                listRuns.add(new DeliveryRun(idDelivery, orders));
                

            }
        }
        return listRuns;
    }


    public String getCourierEmail(int scooterID) throws SQLException{
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call triggerGetCourierEmailByScooterID(?,?)}")) {
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, scooterID);
            callStmt.setTimestamp(3, Timestamp.from(Instant.now()));
            callStmt.execute();
            return callStmt.getString(1);

        }
    }

    public boolean endDeliveryRun(int scooterID) throws SQLException {
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcEndDeliveryRun(?,?)}")) {
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, scooterID);
            callStmt.setTimestamp(3, Timestamp.from(Instant.now()));
            callStmt.execute();
            return callStmt.getBoolean(1);
        }
    }

    public void startDelivery(int id, String email, Route r, int vehicleId) throws SQLException {
        DeliveryRun p = null;
        try (CallableStatement callStmt = getConnection().prepareCall("{ = call funcStartDeliveryRun(?,?,?,?,?,?)}")) {
            callStmt.setInt(1, id);
            callStmt.setString(2, email);
            if(r == null){
                callStmt.setFloat(3, -1);
                callStmt.setFloat(4, -1);
            }else{
                callStmt.setFloat(3, (float) r.getTotalDistance());
                callStmt.setFloat(4, (float) r.getTotalEnergy());
            }
            callStmt.setTimestamp(5, Timestamp.from(Instant.now()));
            callStmt.setInt(6, vehicleId);
            callStmt.execute();

        }
    }
    
}