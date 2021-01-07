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
public class Courier {
    
    private String email;
    private int nif;
    private int nss;
    private double maxWeight;

    public Courier(String email, int nif, int nss, double maxWeight) {
        this.email = email;
        this.nif = nif;
        this.nss = nss;
        this.maxWeight = maxWeight;
    }

    public String getEmail() {
        return email;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public int getNif() {
        return nif;
    }

    public int getNss() {
        return nss;
    }
    
   
    
}
