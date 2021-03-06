/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;

/**
 *
 * @author tiago
 */
public abstract class Vehicle {

    private int id;
    private double weight;
    private State state;
    private double maxBat;
    private double currentBat;
    private int motor;
    private double maxWeight;

    public Vehicle() {
    }

    public Vehicle(int id, double weight, State state, double maxBat, double currentBat, int motor, double maxWeight) {
        setId(id);
        setWeight(weight);
        setState(state);
        setMaxBat(maxBat);
        setCurrentBat(currentBat);
        setMotor(motor);
        setMaxWeight(maxWeight);
    }

    /**
     * returns the id of a vehicle
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * sets the id of a vehicle
     *
     * @param id
     */
    public void setId(int id) {
        if(id <= 0) throw new IllegalArgumentException();
        this.id = id;
    }

    /**
     * returns the weight of a vehicle
     *
     * @return
     */
    public double getWeight() {
        return weight;
    }

    /**
     * sets the weight of a bicycle
     *
     * @param weight
     */
    public void setWeight(double weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        this.weight = weight;
    }

    /**
     * returns the max bat of the scooter
     * @return
     */
    public double getMaxBat() {
        return maxBat;
    }

    /**
     * sets the max battery of a scooter
     * @param maxBat
     */
    public void setMaxBat(double maxBat) {
        if (maxBat<=0) throw new IllegalArgumentException("Maximum battery cannot be negative");
        this.maxBat = maxBat;
    }

    /**
     * returns the max bat of the scooter
     * @return
     */
    public double getCurrentBat() {
        return currentBat;
    }

    /**
     * sets the max battery of a scooter
     * @param currentBat
     */
    public void setCurrentBat(double currentBat) {
        if (!(currentBat>=0&&currentBat<=maxBat)) throw new IllegalArgumentException("Current battery cannot be negative and greater than maxBattery");
        this.currentBat = currentBat;
    }

    /**
     * returns the State of a vehicle
     * @return
     */
    public State getState() {
        return state;
    }

    /**
     * sets the State of a vehicle
     * @param state
     */
    public void setState(State state) {
        if (state==null) throw new IllegalArgumentException("A state is necessary");
        this.state = state;
    }

    /**
     * returns the max bat of the scooter
     * @return
     */
    public double getMotor() {
        return motor;
    }

    /**
     * sets the motor Potency
     * @param motor
     */
    public void setMotor(int motor) {
        if (motor<=0) throw new IllegalArgumentException("Motor cannot be negative");
        this.motor = motor;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        if(maxWeight <= 0){
            throw new IllegalArgumentException("Vehicle max weight is invalid.");
        }
        this.maxWeight = maxWeight;
    }
    
    
    
    

    /**
     * Equals method
     * @param obj
     * @return 
     */
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
        Vehicle other = (Vehicle) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return id;
    }
    
    

    @Override
    public String toString() {
        return String.format("Id: %d - Weight: %.2fkg | Status: %s | Max Battery: %.2fkWh | Current Bat: %.2fkWh", id, weight, state.getName(), maxBat, currentBat);
    }
}
