/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import lapr.project.data.GeographicalPointDB;
import lapr.project.model.GeographicalPoint;

/**
 *
 * @author Diogo
 */
public class AddGeographicalPointController {
    
    private final GeographicalPointDB gpdb;
    private final Set<GeographicalPoint> points;
    private GeographicalPoint point;

    public AddGeographicalPointController(GeographicalPointDB gpdb) {
        this.gpdb = gpdb;
        this.points = new HashSet<>();
    }

    public AddGeographicalPointController() {
        this.gpdb = new GeographicalPointDB();
        this.points = new HashSet<>();
    }
    
    
    
    public String newGeographicalPoint(double longitude, double latitude, double elevation, String description){
        point = gpdb.newGeographicalPoint(longitude, latitude, elevation, description);
        return (point==null)?null:point.toString();
    }
    
    public boolean addToQueue(){
        if(point != null){
            return points.add(point);
        }
        return false;
    }
    
    public int savePoints() throws SQLException{
        if(!points.isEmpty()){
            return gpdb.savePoints(points);
        }
        return 0;
    }
}
