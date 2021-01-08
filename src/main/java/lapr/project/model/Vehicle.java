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
    private int maxBat;
    private int actualBat;
    private int motor;

    public Vehicle() {
    }

    public Vehicle(int id, int weight, State state, int maxBat, int actualBat, int motor) {
        setID(id);
        setWeight(weight);
        setState(state);
        setMaxBat(maxBat);
        setActualBat(actualBat);
        setMotor(motor);
    }

    /**
     * returns the description of a bicycle
     *
     * @return
     */
    public int getid() {
        return id;
    }

    /**
     * sets the id of a vehicle
     *
     * @param id
     */
    public void setID(int id) {
        if(id == 0) throw new IllegalArgumentException();
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
    public int getMaxBat() {
        return maxBat;
    }

    /**
     * sets the max battery of a scooter
     * @param maxBat
     */
    public void setMaxBat(int maxBat) {
        if (maxBat<=0) throw new IllegalArgumentException("Maximum battery cannot be negative");
        this.maxBat = maxBat;
    }

    /**
     * returns the max bat of the scooter
     * @return
     */
    public double getActualBat() {
        return actualBat;
    }

    /**
     * sets the max battery of a scooter
     * @param currentBat
     */
    public void setActualBat(int currentBat) {
        if (!(currentBat>=0&&currentBat>maxBat)) throw new IllegalArgumentException("Current battery cannot be negative and greater than maxBattery");
        this.actualBat = currentBat;
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




    /**
     * Hash Code
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
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
    public String toString() {
         final StringBuilder sb = new StringBuilder();
         sb.append("id: ").append(id);
         sb.append(", weight=").append(weight);
         sb.append(", state=").append(state.toString());
         sb.append(", maxBattery=").append(maxBat);
         sb.append(", currentBattery=").append(actualBat);
         sb.append(", motor=").append(motor);
        return sb.toString();
    }
}
