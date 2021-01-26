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
import lapr.project.model.DeliveryRun;
import lapr.project.model.Order;
import lapr.project.model.Product;
import lapr.project.model.VehicleCategory;
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

    public String endDeliveryRun(int scooterID) throws SQLException {
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcEndDeliveryRun(?,?)}")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, scooterID);
            callStmt.setTimestamp(3, Timestamp.from(Instant.now()));
            callStmt.execute();
            return callStmt.getString(1);
        }
    }

    public boolean startDelivery(int id, String email, Route r, int vehicleId) {
        DeliveryRun p = null;
        try (CallableStatement callStmt = getConnection().prepareCall("{ = call procStartDeliveryRun(?,?,?,?,?,?)}")) {
            callStmt.setInt(1, id);
            callStmt.setString(2, email);
            if(r == null){
                callStmt.setDouble(3, -1);
                callStmt.setDouble(4, -1);
            }else{
                callStmt.setDouble(3, r.getTotalDistance());
                callStmt.setDouble(4, r.getTotalEnergy());
            }
            callStmt.setTimestamp(5, Timestamp.from(Instant.now()));
            callStmt.setInt(6, vehicleId);
            callStmt.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }



    public int saveRuns(Set<DeliveryRun> runs) throws SQLException {
        getConnection();
        int rows=0;
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcAddDeliveryRun(?) }")) {
            for (DeliveryRun p : runs) {
                callStmt.registerOutParameter(1, OracleTypes.INTEGER);
                callStmt.setString(2, p.getVehicleAssigned().getName());
  
                callStmt.execute();
                odb.assignDelivery(p.getOrders(), callStmt.getInt(1));
                rows++;

                
            }

            return rows;
        }
    }

    public DeliveryRun newDeliveryRun(int id, String category, List<Order> ordersSelected) {
        return new DeliveryRun(id, ordersSelected, VehicleCategory.fromString(category));
    }
    
}