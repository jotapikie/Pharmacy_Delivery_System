/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;



/**
 *
 * @author Diogo
 */
public class Pathway{
    
    private GeographicalPoint origPoint;
    private GeographicalPoint destPoint;
    private String description;
    private int distance;
    private double energy;

    public Pathway(GeographicalPoint origPoint, GeographicalPoint destPoint, String description, int distance, double energy) {
        this.origPoint = origPoint;
        this.destPoint = destPoint;
        this.description = description;
        this.distance = distance;
        this.energy = energy;
    }
    
    

    public int getDistance() {
        return distance;
    }

    public double getEnergy() {
        return energy;
    }

    public GeographicalPoint getOriginPoint() {
        return origPoint;
    }

    public GeographicalPoint getDestinationPoint() {
        return destPoint;
    }
    
    
    
    
    
    
    










    
}
