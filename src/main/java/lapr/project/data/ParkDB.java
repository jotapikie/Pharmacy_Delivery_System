package lapr.project.data;

import java.sql.BatchUpdateException;
import lapr.project.model.Park;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import lapr.project.model.ParkSlot;
import lapr.project.model.VehicleCategory;
import lapr.project.utils.Constants;
import oracle.jdbc.OracleTypes;

public class ParkDB extends DataHandler {
    
    private final ParkSlotDB psdb;

    public ParkDB() {
        psdb = new ParkSlotDB();
    }
    
    public Park newPark(int maxVehicles, int ableToCharge, String vehicleCategory, double doubleMaxEnergy) {
        Set<ParkSlot> slots = new HashSet<>();
        for(int i = 0; i < maxVehicles;i++){
            if(ableToCharge > 0){
                slots.add(psdb.newParkSlot(true));
                ableToCharge--;
            }else{
                slots.add(psdb.newParkSlot(false));
            }
        }
        return new Park(Constants.DEFAULT_ID, maxVehicles, VehicleCategory.fromString(vehicleCategory), doubleMaxEnergy, slots);
    }
    
  
    
    public Set<Park> getParksByPhamarcy(int phamarcyId) throws SQLException{
        HashSet<Park> parks = new HashSet<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetParksByPhamarcy(?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, phamarcyId);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                Park p = new Park(rs.getInt(1), rs.getInt(2), VehicleCategory.fromString(rs.getString(3)), rs.getDouble(4), new HashSet<>());
                p.setSlots(psdb.getParkSlotsByPark(rs.getInt(1)));
                parks.add(p);
            }
        }
        return parks;
    }

    public Park getParkByID(int id) throws SQLException {
        if (id < 1) {
            throw new IllegalArgumentException("Invalid ID!");
        }
        Park p = null;
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetPark(?)}")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, id);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                Set<ParkSlot> slots = null;
                //to complete
                p = new Park(rs.getInt(4), rs.getInt(2), VehicleCategory.fromString(rs.getString(3)), rs.getDouble(4), new HashSet<>());
                return p;
            }
            throw new IllegalArgumentException("Product does not exist");
        }
    }

    public int saveParks(Set<Park> parks, int pharmacyId) throws SQLException {
        Connection con = getConnection();
        int[] rows;
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcAddPark(?,?,?,?) }")) {
            for (Park p : parks) {
                callStmt.registerOutParameter(1, OracleTypes.INTEGER);
                callStmt.setInt(2, p.getnMaxVehicles());
                callStmt.setString(3, p.getType().getName());
                callStmt.setDouble(4, p.getMaxEnergy());
                callStmt.setInt(5, pharmacyId);

                callStmt.execute();
                psdb.saveParkSlots(p.getSlots(), callStmt.getInt(1));
                

                con.setAutoCommit(false);
            }
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

 
}
