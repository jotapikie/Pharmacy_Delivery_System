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
public class DronePath extends Pathway{
    
    private double totalWeight;
    
    private double energyCost;
    
    private long timeCost;

    public DronePath(double totalWeight, GeographicalPoint origLocation, GeographicalPoint destLocation, double distance, Wind wind) {
        super(origLocation, destLocation, null, distance, wind, null, VehicleCategory.DRONE);
        setTotalWeight(totalWeight);
        calculateEnegyCost();
        calculateTimeCost();
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        if (totalWeight <= 0) {
            throw new IllegalArgumentException("Invalid weight!");
        }
        this.totalWeight = totalWeight;
    }

    private void calculateEnegyCost() {
        energyCost = Utils.pathEnergyCostDrone(totalWeight, Drone.getAeroCoef(), Drone.getPowerTransfer(), Drone.getLiftDrag(), Drone.getEletronicalConsumer(), Drone.getFrontalArea(), Drone.getTopArea(), Constants.DRONE_AVERAGE_SPEED, super.getWind(),Constants.DRONE_ALTITUDE-super.getOriginPoint().getElevation(),Constants.DRONE_ALTITUDE-super.getDestinationPoint().getElevation(),super.getDistance());
    }
    
    private void calculateTimeCost() {
        timeCost = (long) (super.getDistance() / Constants.DRONE_HORIZONTAL_SPEED);
    }

    @Override
    public double getCost() {
        return energyCost;
    }

    @Override
    public long getTime() {
        return timeCost;
    }
    
    


    
    
    
    
    
    
    
    
}
