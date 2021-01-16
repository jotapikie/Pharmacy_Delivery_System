/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author Helder
 */
public class Drone extends Vehicle{
    
    public Drone(int id, double weight, State state, int maxBat, int actualBat, int motor) {
        super(id, weight, state, maxBat, actualBat, motor);
    }
    
    public void updateDroneData(double weight, State state, int maxBat, int motor) {
        super.setWeight(weight);
        super.setState(state);
        super.setMaxBat(maxBat);
        super.setMotor(motor);
    }


    
    
    
}
