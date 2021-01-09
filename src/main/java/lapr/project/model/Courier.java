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
public class Courier extends User{
    private double maxWeight;

    public Courier(double maxWeight, String name, String email, String password) {
        super(name, email, password);
        this.maxWeight = maxWeight;
    }

    public double getMaxWeight() {
        return maxWeight;
    }
    
    
    
    
}
