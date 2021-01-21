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
    private final int pharmacyId;
    private Park park;
    private final Set<Park> parks;

    
    public AddParkController(PharmacyDB pdb, ParkDB parkdb, int pharmacyId) {
        this.pdb = pdb;
        this.parkdb = parkdb;
        this.pharmacyId=pharmacyId;
        parks = new HashSet<>();
    }
   
    
    
    public String newPark(int nMaxVehicles,int ableToCharge, String type, double max_energy){
        park=parkdb.newPark(nMaxVehicles,ableToCharge, type, max_energy);
        return (park==null)? null : park.toString();
    }
    
    public boolean addToQueue(){
        if(park != null){
            return parks.add(park);
        }
        return false;
    }

    public int saveParks(){
        if(!parks.isEmpty()){
            parkdb.saveParks(parks, pharmacyId);
        }
        return 0;
    }
    
    
 
    
    
    

    
    
    
}
