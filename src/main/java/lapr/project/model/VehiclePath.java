/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.utils.Utils;

/**
 *
 * @author Diogo
 */
public final class VehiclePath extends Pathway{
     
    
    /**
     * Total weight (client + vehicle + order) in Kg.
     */
    private double totalWeight;

    /**
     * Aerodynamic coefficient of the vehicle.
     */
    private double vehicleAerodynamicCoef;

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
    public VehiclePath(GeographicalPoint origPoint, GeographicalPoint destPoint, double distance, StreetType type, double wind, double totalWeight, double vehicleAerodynamicCoef, String street) {
        super(origPoint, destPoint, type, distance, wind, street);
        setTotalWeight(totalWeight);
        setVehicleAerodynamicCoef(vehicleAerodynamicCoef);
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
     * Returns the aerodynamic coefficient of the vehicle.
     *
     * @return aerodynamic coefficient.
     */
    public double getVehicleAerodynamicCoef() {
        return vehicleAerodynamicCoef;
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
     * Modifies the total weight.
     *
     * @param vehicleAerodynamicCoef
     */
    public void setVehicleAerodynamicCoef(double vehicleAerodynamicCoef) {
        if (vehicleAerodynamicCoef <= 0) {
            throw new IllegalArgumentException("Invalid aerodynamic coefficient!");
        }
        this.vehicleAerodynamicCoef = vehicleAerodynamicCoef;
    }

    /**
     * Calculates the energy cost in Wh.
     */
    private void calculateEnergyCost() {
        energyCost = Utils.pathEnergyCost(totalWeight, super.getKineticCoef(),vehicleAerodynamicCoef, super.getWind(), super.getElevationDif(), super.getDistance());
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
}
