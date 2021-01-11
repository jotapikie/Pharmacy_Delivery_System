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
    
    private int nif;
    private int nss;
    private double maxWeight;

    public Courier(String name, String email, String password, int nif, int nss, double maxWeight) {
        super(name, email, password);
        this.nif = nif;
        this.nss = nss;
        this.maxWeight = maxWeight;
    }

    public int getNif() {
        return nif;
    }

    public int getNss() {
        return nss;
    }
    
    

    


    public double getMaxWeight() {
        return maxWeight;
    }

 

    
    
    
    
    
    
}
