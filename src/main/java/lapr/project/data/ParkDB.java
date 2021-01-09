package lapr.project.data;

import lapr.project.model.Park;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import lapr.project.model.ParkSlot;
import oracle.jdbc.OracleTypes;

public class ParkDB extends DataHandler {
    
    private ParkSlotDB psdb;

    public ParkDB() {
        psdb = new ParkSlotDB();
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
    
    public HashSet<Park> getParksByPhamarcy(int phamarcyId) throws SQLException{
        HashSet<Park> parks = new HashSet<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetParksByPhamarcy(?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, phamarcyId);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                Park p = new Park(rs.getInt(1), rs.getInt(2), rs.getString(3), new HashSet<ParkSlot>());
                p.setSlots(psdb.getParkSlotsByPark(rs.getInt(1)));
                parks.add(p);
            }
        }
        return parks;
    }
}
