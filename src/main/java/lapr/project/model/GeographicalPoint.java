/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.utils.route.VertexInterface;

/**
 *
 * @author Diogo
 */
public class GeographicalPoint implements VertexInterface{
    

    private double longitude;
    private double latitude;
    private double altitude;
    
    private int counter;

    public GeographicalPoint(double longitude, double latitude, double altitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        counter = 0;
    }

    @Override
    public int getCounter() {
        return counter;
    }

    @Override
    public void incrementCounter() {
        counter++;
    }

    @Override
    public void resetCounter() {
        counter = 0;
    }
    
    
    
   




    
}
