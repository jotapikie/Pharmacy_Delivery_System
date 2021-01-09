/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import lapr.project.model.Courier;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Diogo
 */
public class CourierDB extends DataHandler{

    public Courier getCourier(String courierEmail) throws SQLException {
        Courier c = null;
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetCourier(?)}")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, courierEmail);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                c = new Courier(rs.getString(3), rs.getString(1), rs.getString(2), rs.getInt(4), rs.getInt(5), rs.getDouble(6));
                return c;
            }
            throw new IllegalArgumentException("Courier does not exist");
        }
    }

    public Courier newCourier(double maxWeight, String name, String email, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean saveCourier(Courier courier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int saveCouriers(List<Courier> couriersList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
