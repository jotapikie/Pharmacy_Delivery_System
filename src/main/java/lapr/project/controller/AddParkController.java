/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import lapr.project.data.ParkDB;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Park;
import lapr.project.model.Pharmacy;

/**
 *
 * @author Helder
 */
public class AddParkController {
    
    
    private final PharmacyDB pdb;
    private final ParkDB parkdb;
    private final int pharmacy_id;
    private Park park;
    private Pharmacy pharm;

    public AddParkController(PharmacyDB pdb, ParkDB parkdb, int pharmacy_id, Pharmacy pharm) {
        this.pdb = pdb;
        this.parkdb = parkdb;
        this.pharmacy_id=pharmacy_id;
        this.pharm=pharm;
    }
   
    
//    public String getSelectedPark(int id) throws SQLException{
//        park = parkdb.getParkByID(id);
//        return park.toString();
//    }
    
    
    public String newPark(int nMaxVehicles, String type, double max_energy){
        park=parkdb.newPark(nMaxVehicles, type, max_energy);
        return (park==null)? null : park.toString();
    }
    
    public void registPark(){
        parkdb.savePark(park, pharmacy_id);
        Set<Park> parks=pharm.getParks();
        pharm.setParks(addPark(parks));
    }
    
    public Set<Park> addPark(Set<Park> parks){
        parks.add(park);
        return parks;
    }
    
    
    
    
//    public void addParkToPharmacy() throws SQLException{
//            Set<Park> parks=pdb.getPharmacyByAdministrator(administratorEmail).getParks();
//            parks.add(park);
//            pdb.getPharmacyByAdministrator(administratorEmail).setParks(parks);
//        
//    }
    
    
    
}
