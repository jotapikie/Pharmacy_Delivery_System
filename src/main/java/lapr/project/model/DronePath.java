/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.utils.Constants;
import lapr.project.utils.Utils;
import static lapr.project.utils.Utils.windToPath;

/**
 *
 * @author Diogo
 */
public class DronePath extends Pathway{
    
    private double totalWeight;
    
    private double energyCost;

    public DronePath(double totalWeight, GeographicalPoint origLocation, GeographicalPoint destLocation, StreetType type, double distance, Wind wind, String street) {
        super(origLocation, destLocation, type, distance, wind, street);
        this.totalWeight = totalWeight;
        calculateEnegyCost();
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
        energyCost = Utils.pathEnergyCostDrone(totalWeight, Drone.getAeroCoef(), Drone.getPowerTransfer(), Drone.getLiftDrag(), Drone.getEletronicalConsumer(), Drone.getFrontalArea(), Drone.getTopArea(), Constants.DRONE_AVERAGE_SPEED, new Wind(1, 1, 1) ,Constants.DRONE_ALTITUDE-super.getOriginPoint().getElevation(),Constants.DRONE_ALTITUDE-super.getDestinationPoint().getElevation(),super.getDistance());
    }

    @Override
    public double getCost() {
        return energyCost;
    }
    
    
    
    
    
    
    
    
}
