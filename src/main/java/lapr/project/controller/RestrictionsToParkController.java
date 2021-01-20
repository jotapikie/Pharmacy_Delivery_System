/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.Set;
import lapr.project.data.ParkDB;
import lapr.project.model.Park;
import lapr.project.model.Pharmacy;

/**
 *
 * @author ivoal
 */
public class RestrictionsToParkController {
    
    private Pharmacy pharm;
    private ParkDB parkdb;
    private Park park;
    
    
    public RestrictionsToParkController(Pharmacy pharmacy){
        this.pharm=pharmacy;
    }
    
    public Set<Park> getParks(){
        return pharm.getParks();
    }
    
    public void setPark(Park p){
        this.park=p;
    }
    
    public void addMaxEnergy(double max_energy) throws SQLException{
        parkdb.addMaxEnergy(park, max_energy);
    }
    
}
