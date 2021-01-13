/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.HashSet;
import lapr.project.data.ParkDB;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Park;

/**
 *
 * @author Helder
 */
public class AddParkController {
    
    
    private final PharmacyDB pdb;
    private final ParkDB parkdb;
    private final String administratorEmail;
    private Park park;

    public AddParkController(PharmacyDB pdb, ParkDB parkdb, String administratorEmail) {
        this.pdb = pdb;
        this.parkdb = parkdb;
        this.administratorEmail = administratorEmail;
    }
   
    
    public String getSelectedPark(int id) throws SQLException{
        park = parkdb.getParkByID(id);
        return park.toString();
    }
    
    public void addParkToPharmacy() throws SQLException{
            HashSet<Park> parks=pdb.getPharmacyByAdministrator(administratorEmail).getParks();
            parks.add(park);
            pdb.getPharmacyByAdministrator(administratorEmail).setParks(parks);
        
    }
    
    
    
}
