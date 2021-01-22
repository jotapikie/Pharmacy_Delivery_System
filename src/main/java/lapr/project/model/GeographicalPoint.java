/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.utils.route.PointInterface;

/**
 *
 * @author Diogo
 */
public class GeographicalPoint implements PointInterface{
    

    private double longitude;
    private double latitude;
    private double elevation;
    private String description;
    
    private int counter;

    public GeographicalPoint(double longitude, double latitude, double elevation) {
        setLongitude(longitude);
        setLatitude(latitude);
        setElevation(elevation);
        setDescription("Point");
        counter = 0;
    }

    public GeographicalPoint(double longitude, double latitude, double elevation, String description) {
        setLongitude(longitude);
        setLatitude(latitude);
        setElevation(elevation);
        setDescription(description);
        counter = 0;
    }

    public final void setLongitude(double longitude) {
        if(Math.abs(longitude) > 90){
            throw new IllegalArgumentException("Invalid longitude!");
        }
        this.longitude = longitude;
    }

    public final void setLatitude(double latitude) {
        if(Math.abs(latitude) > 180){
            throw new IllegalArgumentException("Invalid latitude!");
        }
        this.latitude = latitude;
    }

    public final void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public void setDescription(String description) {
        if(description == null || description.isEmpty()){
            throw new IllegalArgumentException("Invalid geographical point descriprion");
        }
        this.description = description;
    }
    
    
    
    

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getElevation() {
        return elevation;
    }

    public String getDescription() {
        return description;
    }
    
    

    @Override
    public String toString() {
        return String.format("Descripition: %s - Longitude: %.5f - Latitude: %.5f - Elevation: %.1f m", description, longitude, latitude, elevation);
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
