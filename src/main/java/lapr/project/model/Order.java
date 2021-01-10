/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Date;
import java.util.HashSet;

/**
 *
 * @author Diogo
 */


/**
 *
 * @author Diogo
 */
public class Order implements Comparable<Order>{
    
    private int id;
    private Date beginDate;
    private Date endDate;
    private String status;
    private float price;
    private HashSet<Product> products;

    public Order(int id, Date beginDate, Date endDate, String status, float price, HashSet<Product> products) {
        this.id = id;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.status = status;
        this.price = price;
        this.products = products;
    }
    
    public Order(int id, Date beginDate, Date endDate, String status, float price) {
        this.id = id;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.status = status;
        this.price = price;
        this.products = new HashSet<>();
        
    }
    
    public int getId() {
        return id;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public float getPrice() {
        return price;
    }
    
    public String getStatus() {
        return status;
    }

    public HashSet<Product> getProducts() {
        return products;
    }
    
    public double getTotalWeight(){
        double weight = 0;
        for(Product p : products){
            weight = weight + p.getWeight();
        }
        return weight;
    }

    @Override
    public String toString() {
        return String.format("Id: %d | Begin Date: %s | End Date: %s | Status: %s | Price: %.2f €", id, beginDate, endDate, status, price);
    }
    
    

    @Override
    public int compareTo(Order o) {
        return this.beginDate.compareTo(o.beginDate);
    }
    
    

    
}
