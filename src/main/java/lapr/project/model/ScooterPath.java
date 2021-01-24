/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.utils.Constants;
import lapr.project.utils.Utils;

/**
 *
 * @author Diogo
 */
public final class ScooterPath extends Pathway{
     
    
    /**
     * Total weight (client + vehicle + order) in Kg.
     */
    private double totalWeight;



    /**
     * Energy cost of the path in Wh.
     */
    private double energyCost;

    /**
     * Constructs an instance of PathEnergy, a path that calculates the cost
     * based on energy efficiency.
     *
     * @param origPoint
     * @param destPoint
     * @param distance
     * @param type
     * @param wind
     * @param totalWeight
     * @param vehicleAerodynamicCoef
     */
    public ScooterPath(GeographicalPoint origPoint, GeographicalPoint destPoint, double distance, StreetType type, double wind, double totalWeight, String street) {
        super(origPoint, destPoint, type, distance, wind, street);
        setTotalWeight(totalWeight);
        calculateEnergyCost();
    }

    /**
     * Returns the total weight.
     *
     * @return weight.
     */
    public double getTotalWeight() {
        return totalWeight;
    }



    /**
     * Modifies the total weight.
     *
     * @param totalWeight new weight.
     */
    public void setTotalWeight(double totalWeight) {
        if (totalWeight <= 0) {
            throw new IllegalArgumentException("Invalid weight!");
        }
        this.totalWeight = totalWeight;
    }



    /**
     * Calculates the energy cost in Wh.
     */
    private void calculateEnergyCost() {
        energyCost = Utils.pathEnergyCost(totalWeight, super.getKineticCoef(),Constants.SCOOTER_AERO_COEF, super.getWind(), super.getElevationDif(), super.getDistance());
    }

    /**
     * Returns the cost of the edge.
     *
     * @return cost of the edge.
     */
   
    @Override
    public double getCost() {
        return energyCost;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ScooterPath other = (ScooterPath) obj;
        if(this.energyCost != other.energyCost){
            return false;
        }
        
        if(this.totalWeight != other.totalWeight){
            return false;
        }
        
        
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
}