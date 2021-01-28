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
     * Energy cost of the path in kWh.
     */
    private double energyCost;
    
    
    private long timeCost;
    
    

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
    public ScooterPath(GeographicalPoint origPoint, GeographicalPoint destPoint, double distance, StreetType type, Wind wind, double totalWeight, String street) {
        super(origPoint, destPoint, type, distance, wind, street, VehicleCategory.SCOOTER);
        setTotalWeight(totalWeight);
        calculateEnergyCost();
        calculateTimeCost();
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
        energyCost = Utils.pathEnergyCost(totalWeight, super.getKineticCoef(),Constants.SCOOTER_AERO_COEF, Utils.windToPath(Utils.pathDirection(super.getOriginPoint().getLatitude(),super.getOriginPoint().getLongitude(),super.getDestinationPoint().getLatitude(),super.getDestinationPoint().getLongitude()),super.getWind()), super.getElevationDif(), super.getDistance());
    }
    
   private void calculateTimeCost() {
        timeCost = (long) (super.getDistance() / (Utils.kmhTOms(Constants.SCOOTER_SPEED)));
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
    public long getTime() {
        return timeCost;
    }

    
    
    

    @Override
    public String toString() {
        return String.format("%s - Total Weight: %.2f kg", super.toString(), totalWeight);
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
