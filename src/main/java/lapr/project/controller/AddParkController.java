/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.data.ParkDB;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Park;
import lapr.project.model.Pharmacy;

/**
 *
 * @author Helder
 */
public class AddParkController {
    
    private final int parkID;
    private final PharmacyDB pdb;
    private final ParkDB parkdb;
    private final String administratorEmail;
    private Park park;
    private Pharmacy pha;

    public AddParkController(int parkID, PharmacyDB pdb, ParkDB parkdb, String administratorEmail) {
        this.parkID = parkID;
        this.pdb = pdb;
        this.parkdb = parkdb;
        this.administratorEmail = administratorEmail;
    }
   
    
    public String getSelectedPark(int id) throws SQLException{
        park = parkdb.getParkByID(id);
        return park == null ? null : park.toString();
    }
    
    public void addParkToPharmacy() throws SQLException{
        if(park!=null){
            pdb.getPharmacyByAdministrator(administratorEmail).setParks(park);
        }
    }
    
    
    
}
