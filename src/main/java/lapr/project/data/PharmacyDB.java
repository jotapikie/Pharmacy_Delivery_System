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
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Diogo
 */
public class PharmacyDB extends DataHandler {

    private AddressDB adb= new AddressDB();
    private ParkDB pdb= new ParkDB();

    public Pharmacy newPharmacy(int id, int phoneNumber, String name, String email, Address address, HashSet<Park> parks) {
        return new Pharmacy(id,phoneNumber,name,email,address,parks);
    }

    /**
     * Saves the pharmacy passed by parameter in our database.
     *
     * @param p - instance of pharmacy that will be saved.
     * @return true if we're able to add the pharmacy, false if we're not
     * @throws java.sql.SQLException
     */
    public boolean savePharmacy(Pharmacy p) throws SQLException {
        adb.saveAddress(p.getAddress());
        getConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procRegisterPharmacy(?,?,?,?,?) }")) {
            callStmt.setInt(1,p.getId());
            callStmt.setInt(2,p.getPhoneNumber());
            callStmt.setString(3,p.getEmailAdmin());
            callStmt.setDouble(4,p.getAddress().getLatitude());
            callStmt.setDouble(5,p.getAddress().getLongitude());
            callStmt.execute();


        for (Park park: p.getParks()){
            pdb.savePark(park);
        }}
        return true;
    }


    public int savePharmacies(List<Pharmacy> pharmaciesList) throws SQLException {
        Connection con = getConnection();
        int[] rows;
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procRegisterPharmacy(?,?,?) }")) {
            for (Pharmacy p : pharmaciesList) {
                    callStmt.setInt(1,p.getId());
                    callStmt.setInt(2,p.getPhoneNumber());
                    callStmt.setString(3,p.getEmailAdmin());
                    callStmt.execute();

                    adb.saveAddress(p.getAddress());
                    for (Park park: p.getParks()){
                        pdb.savePark(park);
                    }}

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
    public Pharmacy getPharmacyByAdministrator(String administratorEmail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
