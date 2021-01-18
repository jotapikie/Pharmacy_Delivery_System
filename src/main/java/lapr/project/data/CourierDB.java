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
import java.util.List;
import java.util.Set;
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

    public Courier newCourier(String name, String email, String password, int nif, int nss, double maxWeight) {
        return new Courier(name, email, password, nif, nss, maxWeight);
    }

    public int saveCouriers(Set<Courier> couriersList, int idPhamarcy) throws SQLException {
        Connection con = getConnection();
        int[] rows;
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procRegisterCourier(?,?,?,?,?,?,?) }")) {
            for (Courier c : couriersList) {
                callStmt.setString(1, c.getEmail());
                callStmt.setString(2, c.getPassword());
                callStmt.setString(3, c.getName());
                callStmt.setInt(4, c.getNif());
                callStmt.setInt(5, c.getNss());
                callStmt.setDouble(6, c.getWeight());
                callStmt.setInt(7, idPhamarcy);


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
   

 
    
}
