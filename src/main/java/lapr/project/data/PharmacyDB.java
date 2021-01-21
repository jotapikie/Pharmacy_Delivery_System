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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Diogo
 */
public class PharmacyDB extends DataHandler {

    private final ParkDB pdb= new ParkDB();
    private final ParkSlotDB psdb = new ParkSlotDB();
    
    public PharmacyDB() {
    }
   
   

    public Pharmacy newPhamarcy(int id, int phoneNumber, String name, Administrator administrator, Address address, Set<Park> parks) {
        return new Pharmacy(id,phoneNumber,name,administrator,address, (HashSet<Park>) parks);
    }



    /**
     * Method to save a instance of pharmacy in the dataBase
     *
     * @param p
     * @return
     * @throws java.sql.SQLException
     */
    public boolean save(Pharmacy p) throws SQLException {
        addPharmacy(p);
        return true;
    }

    public int save(Set<Pharmacy> pharmacies) throws SQLException {
        return addPharmacies(pharmacies);
    }
    /**
     * Saves the pharmacy passed by parameter in our database.
     *
     * @param p - instance of pharmacy that will be saved.
     * @return true if we're able to add the pharmacy, false if we're not
     * @throws java.sql.SQLException
     */
    public boolean addPharmacy(Pharmacy p) throws SQLException {
        getConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procRegisterPharmacy(?,?,?,?,?,?,?,?,?) }")) {
            callStmt.setInt(1,p.getId());
            callStmt.setInt(2,p.getPhoneNumber());
            callStmt.setString(3, p.getAdministrator().getEmail());
            callStmt.setDouble(4,p.getAddress().getGeographicalPoint().getLatitude());
            callStmt.setDouble(5,p.getAddress().getGeographicalPoint().getLongitude());
            callStmt.setString(6,p.getAddress().getStreet());
            callStmt.setString(7,p.getAddress().getCity());
            callStmt.setInt(8, p.getAddress().getPortNumber());
            callStmt.setString(9, p.getAddress().getZipCode());
            callStmt.execute();


        }
        return true;
    }


    public int addPharmacies(Set<Pharmacy> pharmaciesList) throws SQLException {
        Connection con = getConnection();
        int[] rows;
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcRegisterPharmacy(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }")) {
            for (Pharmacy p : pharmaciesList) {
                callStmt.registerOutParameter(1, OracleTypes.INTEGER);
                callStmt.setString(2, p.getName());
                callStmt.setInt(3, p.getPhoneNumber());
                
                callStmt.setString(4, p.getAdministrator().getEmail());
                callStmt.setString(5, p.getAdministrator().getName());
                callStmt.setString(6, p.getAdministrator().getPassword());
                
                callStmt.setString(7, p.getAddress().getStreet());
                callStmt.setDouble(8, p.getAddress().getGeographicalPoint().getLongitude());
                callStmt.setDouble(9, p.getAddress().getGeographicalPoint().getLatitude());
                callStmt.setDouble(10, p.getAddress().getGeographicalPoint().getElevation());
                callStmt.setString(11, p.getAddress().getCity());
                callStmt.setString(12, p.getAddress().getZipCode());
                callStmt.setInt(13, p.getAddress().getPortNumber());
                
                Park park = p.getParks().iterator().next();
                callStmt.setInt(14, park.getMaxVehicles());
                callStmt.setString(15, park.getType().getName());
                callStmt.setDouble(16, park.getMaxEnergy());
                
                // ADICIONAR PARK SLOTS

                callStmt.execute();
                psdb.saveParkSlots(park.getSlots(), callStmt.getInt(1));
                

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

//    public Pharmacy getPharmacyByAdministrator(String administratorEmail) throws SQLException {
//        Pharmacy p = null;
//        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetPhamarcyByAdministrator(?)}")) {
//            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
//            callStmt.setString(2, administratorEmail);
//            callStmt.execute();
//            ResultSet rs = (ResultSet) callStmt.getObject(1);
//            while (rs.next()) {
////                p = newPhamarcy(rs.getInt(1), rs.getInt(2), rs.getString(3), new Administrator(rs.getString(6), rs.getString(4), rs.getString(5)), new Address(rs.getString(9), rs.getDouble(8), rs.getDouble(7), rs.getString(10), rs.getInt(11), rs.getString(12)), new HashSet<>());
////                //p.setParks(pdb.getParksByPhamarcy(rs.getInt(1)));
////                return p;
//            }
//            throw new IllegalArgumentException("Phamarcy does not exist");
//        }
//    }
    
    public Pharmacy getPharmacyByid(int id) throws SQLException {
        Pharmacy p = null;
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetPhamarcyByID(?)}")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, id);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                p = new Pharmacy(rs.getInt(1), rs.getInt(2), rs.getString(3), new Administrator(rs.getString(6), rs.getString(4), rs.getString(5)), new Address(rs.getString(9), new GeographicalPoint(rs.getDouble(7),rs.getDouble(8),0), rs.getString(10), rs.getInt(11), rs.getString(12)), new HashSet<>());
                p.setParks(pdb.getParksByPhamarcy(rs.getInt(1)));
                return p;
            }
            throw new IllegalArgumentException("Phamarcy does not exist");
        }
    }
    
    

    public Pharmacy getPhamarcyByCourier(String courierEmail) throws SQLException {
         Pharmacy p = null;
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetPhamarcyByCourier(?)}")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, courierEmail);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
//                p = newPhamarcy(rs.getInt(1), rs.getInt(2), rs.getString(3), new Administrator(rs.getString(6), rs.getString(4), rs.getString(5)), new Address(rs.getString(9), rs.getDouble(8), rs.getDouble(7), rs.getString(10), rs.getInt(11), rs.getString(12)), new HashSet<>());
//                //p.setParks(pdb.getParksByPhamarcy(rs.getInt(1)));
//                return p;
            }
            throw new IllegalArgumentException("The courier wasn't found.");
        }
    }
    
    public List<Pharmacy> getPharmacies() throws SQLException{
        List<Pharmacy> listPharmacies = new ArrayList<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetPharmacies() }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                Pharmacy p = new Pharmacy(rs.getInt(1), rs.getInt(2), rs.getString(3), new Administrator(rs.getString(4), rs.getString(5), rs.getString(6)), new Address(rs.getString(7), new GeographicalPoint(rs.getFloat(8), rs.getFloat(9), rs.getFloat(10)), rs.getString(11), rs.getInt(12), rs.getString(13)));
                listPharmacies.add(p);
            }
        }
        return listPharmacies;
    }





}
