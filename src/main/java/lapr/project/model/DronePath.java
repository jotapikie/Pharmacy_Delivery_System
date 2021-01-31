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
        calculateEnergyCost();
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

    private void calculateEnergyCost() {
        energyCost = Utils.pathEnergyCostDrone(totalWeight, Drone.getAeroCoef(), Drone.getPowerTransfer(), Drone.getLiftDrag(), Drone.getEletronicalConsumer(), Drone.getFrontalArea(), Drone.getTopArea(), Constants.DRONE_AVERAGE_SPEED, Constants.DRONE_VERTICAL_SPEED, super.getWind(),Math.abs(Constants.DRONE_ALTITUDE-super.getOriginPoint().getElevation()),Math.abs(Constants.DRONE_ALTITUDE-super.getDestinationPoint().getElevation()),super.getDistance());
    }
    
    private void calculateTimeCost() {
        double vDistance = (Math.abs(super.getOriginPoint().getElevation()-Constants.DRONE_ALTITUDE) + Math.abs(super.getDestinationPoint().getElevation()-Constants.DRONE_ALTITUDE));
        double hDistance = super.getDistance() - vDistance;
        timeCost = (long) ((hDistance / Constants.DRONE_HORIZONTAL_SPEED) + (vDistance / Constants.DRONE_VERTICAL_SPEED));
    }

    @Override
    public double getCost() {
        calculateEnergyCost();
        return energyCost;
    }

    @Override
    public long getTime() {
        calculateTimeCost();
        return timeCost;
    }
    
    


    
    
    
    
    
    
    
    
}
