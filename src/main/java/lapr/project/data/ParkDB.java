package lapr.project.data;

import lapr.project.model.Park;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class ParkDB extends DataHandler {
    public void savePark(Park park) {
        getConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procRegisterPark(?,?,?) }")) {

            callStmt.setInt(1,park.getId());
            callStmt.setInt(2,park.getnMaxVehicles());
            callStmt.setString(3,park.getType());

            callStmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
