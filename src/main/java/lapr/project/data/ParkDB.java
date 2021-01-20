package lapr.project.data;

import lapr.project.model.Park;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import lapr.project.model.ParkSlot;
import lapr.project.utils.Constants;
import oracle.jdbc.OracleTypes;

public class ParkDB extends DataHandler {
    
    private final ParkSlotDB psdb;

    public ParkDB() {
        psdb = new ParkSlotDB();
    }
    
    public Park newPark(int nMaxVehicles, String type, double max_energy){
        return new Park(Constants.DEFAULT_ID, nMaxVehicles, type, max_energy);
    }
    
    public void savePark(Park park,int pharmId) {
        getConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procRegisterPark(?,?,?,?) }")) {

            callStmt.setInt(1,park.getId());
            callStmt.setInt(2,park.getnMaxVehicles());
            callStmt.setString(3,park.getType());
            callStmt.setInt(4,pharmId);

            callStmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    public Set<Park> getParksByPhamarcy(int phamarcyId) throws SQLException{
        HashSet<Park> parks = new HashSet<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetParksByPhamarcy(?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, phamarcyId);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                Park p = new Park(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4));
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
                p = new Park(rs.getInt(4), rs.getInt(2), rs.getString(3), rs.getDouble(4));
                return p;
            }
            throw new IllegalArgumentException("Product does not exist");
        }
    }
}
