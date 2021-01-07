/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.sql.SQLException;
import lapr.project.data.ProductDB;

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
   

    @Override
    public String toString() {
        return name;
    }
    
    
    
}
