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
    private double weight;

    public Courier(String name, String email, String password, int nif, int nss, double weight) {
        super(name, email, password);
        this.nif = nif;
        this.nss = nss;
        this.weight = weight;
    }

    public int getNif() {
        return nif;
    }

    public int getNss() {
        return nss;
    }
    
    

    


    public double getWeight() {
        return weight;
    }

 

    
    
    
    
    
    
}
