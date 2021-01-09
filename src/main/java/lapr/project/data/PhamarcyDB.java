/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.sql.BatchUpdateException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Diogo
 */
public class PhamarcyDB extends DataHandler {
    
   private ParkDB pdb;

    public PhamarcyDB() {
        pdb = new ParkDB();
    }
   
   

    public Phamarcy newPhamarcy(int id, int phoneNumber, String name, Administrator administrator, Address address, HashSet<Park> parks) {
        return new Phamarcy(id,phoneNumber,name,administrator,address,parks);
    }

    /**
     * Saves the pharmacy passed by parameter in our database.
     *
     * @param p - instance of pharmacy that will be saved.
     * @return true if we're able to add the pharmacy, false if we're not
     * @throws java.sql.SQLException
     */
    public boolean savePharmacy(Phamarcy p) throws SQLException {
        getConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procRegisterPharmacy(?,?,?,?,?,?,?,?,?) }")) {
            callStmt.setInt(1,p.getId());
            callStmt.setInt(2,p.getPhoneNumber());
            callStmt.setString(3, p.getAdministrator().getEmail());
            callStmt.setDouble(4,p.getAddress().getLatitude());
            callStmt.setDouble(5,p.getAddress().getLongitude());
            callStmt.setString(6,p.getAddress().getAddress());
            callStmt.setString(7,p.getAddress().getCity());
            callStmt.setInt(8, p.getAddress().getPortNumber());
            callStmt.setString(9, p.getAddress().getZipCode());
            callStmt.execute();


        }
        return true;
    }


    public int savePharmacies(List<Phamarcy> pharmaciesList) throws SQLException {
        Connection con = getConnection();
        int[] rows;
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procRegisterPharmacy(?,?,?,?,?,?,?,?,?) }")) {
            for (Phamarcy p : pharmaciesList) {
                callStmt.setInt(1, p.getId());
                callStmt.setInt(2, p.getPhoneNumber());
                callStmt.setString(3, p.getAdministrator().getEmail());
                callStmt.setDouble(4, p.getAddress().getLatitude());
                callStmt.setDouble(5, p.getAddress().getLongitude());
                callStmt.setString(6, p.getAddress().getAddress());
                callStmt.setString(7, p.getAddress().getCity());
                callStmt.setInt(8, p.getAddress().getPortNumber());
                callStmt.setString(9, p.getAddress().getZipCode());
                callStmt.execute();

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

    public Phamarcy getPharmacyByAdministrator(String administratorEmail) throws SQLException {
        Phamarcy p = null;
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetPhamarcyByAdministrator(?)}")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, administratorEmail);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                p = newPhamarcy(rs.getInt(1), rs.getInt(2), rs.getString(3), new Administrator(rs.getString(6), rs.getString(4), rs.getString(5)), new Address(rs.getString(9), rs.getDouble(8), rs.getDouble(7), rs.getString(10), rs.getInt(11), rs.getString(12)), new HashSet<>());
                //p.setParks(pdb.getParksByPhamarcy(rs.getInt(1)));
                return p;
            }
            throw new IllegalArgumentException("Phamarcy does not exist");
        }
    }

    public Phamarcy getPhamarcyByCourier(String courierEmail) throws SQLException {
         Phamarcy p = null;
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetPhamarcyByCourier(?)}")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, courierEmail);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                p = newPhamarcy(rs.getInt(1), rs.getInt(2), rs.getString(3), new Administrator(rs.getString(6), rs.getString(4), rs.getString(5)), new Address(rs.getString(9), rs.getDouble(8), rs.getDouble(7), rs.getString(10), rs.getInt(11), rs.getString(12)), new HashSet<>());
                //p.setParks(pdb.getParksByPhamarcy(rs.getInt(1)));
                return p;
            }
            throw new IllegalArgumentException("The courier wasn't found.");
        }
    }

}
