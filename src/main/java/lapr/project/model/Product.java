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
        setId(id);
        setName(name);
        setWeight(weight);
        setPrice(price);
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

    public double getPrice() {
        return price;
    }

    public final void setId(int id) {
         if (id <= 0) {
            throw new IllegalArgumentException("Product's id is invalid.");
        }
        this.id = id;
    }

    public final void setName(String name) {
         if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Product's name is invalid.");
        }
        this.name = name;
    }

    public final void setWeight(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Product's weight is invalid.");
        }
        this.weight = weight;
    }

    public final void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Product's price is invalid.");
        }
        this.price = price;
    }
    
    




    @Override
    public String toString() {
        return String.format("Id: %d | Name: %s | Weight: %.2f kg | Price: %.2f €", id, name, weight, price);
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
