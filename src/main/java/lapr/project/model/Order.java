/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
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
    private double price;
    private HashMap<Product, Integer> products;

    public Order(int id, Date beginDate, Date endDate, String status, double price, HashMap<Product, Integer> products) {
        this.id = id;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.status = status;
        this.price = price;
        this.products = products;
    }
    
    public Order(int id, Date beginDate, Date endDate, String status, double price) {
        this.id = id;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.status = status;
        this.price = price;
        this.products = new HashMap<>();
        
    }
    
    public Order(int id, double price, HashMap<Product, Integer> products){
        this.id = id;
        this.beginDate = Date.from(Instant.now());
        this.endDate = null;
        this.status = "Processing";
        this.price = price;
        this.products = products;
        
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

    public double getPrice() {
        return price;
    }
    
    public String getStatus() {
        return status;
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    public double getTotalWeight(){
        double weight = 0;
        for(Product p : products.keySet()){
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

    public Invoice makeInvoice(Client cli, Address add, double priceToPay, int nif) {
        Invoice inv = new Invoice(cli, add, priceToPay, price, products, nif);
        return inv;
    }
    
    

    
}
