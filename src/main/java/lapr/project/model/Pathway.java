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
    private double distance;
    private double energy;
    private double kinetic_coef;
    private double wind;
    private String description;

    public Pathway(GeographicalPoint origPoint, GeographicalPoint destPoint, double distance, double energy, double kinetic_coef, double wind, String description) {
        this.origPoint = origPoint;
        this.destPoint = destPoint;
        this.distance = distance;
        this.energy = energy;
        this.kinetic_coef = kinetic_coef;
        this.wind = wind;
        this.description = description;
    }
    
    public Pathway(GeographicalPoint origPoint, GeographicalPoint destPoint, double distance, double energy, double kinetic_coef, double wind) {
        this.origPoint = origPoint;
        this.destPoint = destPoint;
        this.distance = distance;
        this.energy = energy;
        this.kinetic_coef = kinetic_coef;
        this.wind = wind;
        this.description = null;
    }
    
    public double getDistance() {
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
