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
public class Product {

    private int id;
    private String name;
    private double weight;
    private double price;


    public Product(int id, String name, double weight, double price) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }
    
    

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
        Product other = (Product) obj;
        return this.id == other.getId();
    }

    @Override
    public int hashCode() {
        return id;
    }
    
    
    
    
    
    
    
    
    
   
    
}
