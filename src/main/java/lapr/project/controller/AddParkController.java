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
import lapr.project.model.Park;
import lapr.project.utils.Constants;


/**
 *
 * @author Helder
 */
public class AddParkController {
    
    
    private final ParkDB parkdb;
    private final int pharmacyId;
    private Park park;
    private final Set<Park> parks;

    
    public AddParkController(ParkDB parkdb, int pharmacyId) {
        this.parkdb = parkdb;
        this.pharmacyId=pharmacyId;
        parks = new HashSet<>();
    }
   
    
    
    public String newPark(int nMaxVehicles,int ableToCharge, String type, double maxEnergy){
        park=parkdb.newPark(Constants.DEFAULT_ID + parks.size(), nMaxVehicles,ableToCharge, type, maxEnergy);
        return (park==null)? null : park.toString();
    }
    
    public boolean addToQueue(){
        if(park != null){
            return parks.add(park);
        }
        return false;
    }

    public int saveParks() throws SQLException{
        if(!parks.isEmpty()){
           return parkdb.saveParks(parks, pharmacyId);
        }
        return 0;
    }
    
    
 
    
    
    

    
    
    
}
