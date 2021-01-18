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
        setNif(nif);
        setNss(nss);
        setWeight(weight);
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

    public void setNif(int nif) {
        if(String.valueOf(nif).length()!=9){
            throw new IllegalArgumentException("The courier's nif should have 9 digits.");
        }
        this.nif = nif;
    }

    public void setNss(int nss) {
        this.nss = nss;
    }

    public void setWeight(double weight) {
        if(weight < 0){
            throw new IllegalArgumentException("The courier's weight is invalid.");
        }
        this.weight = weight;
    }
    
    
    
    

    
 

    
    
    
    
    
    
}
