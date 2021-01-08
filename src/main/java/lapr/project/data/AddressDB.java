package lapr.project.data;

import lapr.project.model.Address;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class AddressDB extends DataHandler {
    public boolean saveAddress(Address a) {
            getConnection();
            try (CallableStatement callStmt = getConnection().prepareCall("{ call procRegisterAddress(?,?,?,?,?,?) }")) {

                callStmt.setDouble(1, a.getLatitude());
                callStmt.setDouble(2,a.getLongitude());
                callStmt.setString(3,a.getAddress());
                callStmt.setString(4,a.getCity());
                callStmt.setInt(5,a.getPortNumber());
                callStmt.setString(6,a.getZipCode());

                callStmt.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        return true;
        }
    }

