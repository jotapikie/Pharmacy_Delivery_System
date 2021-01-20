/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lapr.project.data.GeographicalPointDB;
import lapr.project.data.PathwayDB;
import lapr.project.model.GeographicalPoint;
import lapr.project.model.Pathway;
import lapr.project.utils.Utils;

/**
 *
 * @author Diogo
 */
public class AddPathController {
    
    private final GeographicalPointDB gpdb;
    private final PathwayDB pdb;
    private Pathway path;
    private final Set<Pathway> paths;

    public AddPathController(GeographicalPointDB gpdb, PathwayDB pdb) {
        this.gpdb = gpdb;
        this.pdb = pdb;
        this.paths = new HashSet<>();
    }
    
    public List<String> getAvailableGeographicalPoints() throws SQLException{
      return Utils.listToString(gpdb.getGeographicalPoints());
    }
    
    public String selectPoints(double longitude1, double latitude1, double longitude2, double latitude2, double kineticCoef, int windDirection, double windSpeed, String street) throws SQLException{
        GeographicalPoint or = gpdb.getGeographicalPoint(longitude1, latitude1);
        GeographicalPoint dest = gpdb.getGeographicalPoint(longitude2, latitude2);
        if(or != null && dest != null){
            path = pdb.newPath(or, dest, kineticCoef, windDirection, windSpeed, street);
            return path.toString();
        }
        return null;
    }
    
    public boolean addToQueue(){
        if(path != null){
            return paths.add(path);
        }
        return false;
    }
    
    public int savePaths() throws SQLException{
        if(!paths.isEmpty()){
            return pdb.savePaths(paths);
        }
        return 0;
    }
    
    
}
