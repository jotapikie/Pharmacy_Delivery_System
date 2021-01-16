/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import lapr.project.utils.Constants;

/**
 *
 * @author Diogo
 */

public class Order implements Comparable<Order>{
    
    private int id;
    private Timestamp beginDate;
    private Timestamp endDate;
    private String status;
    private double price;
    private Map<Product, Integer> products;
    private Order associatedOrder;

    public Order() {
        id = Constants.DEFAULT_ID;
        beginDate = Constants.DEFAULT_DATE;
        endDate = Constants.DEFAULT_DATE;
        status = Constants.DEFAULT_STATUS;
        price = Constants.DEFAULT_PRICE;
        products = new HashMap<>();
        associatedOrder = null;
    }
    

    
    public Order(int id, Timestamp beginDate, Timestamp endDate, String status, double price) {
        this.id = id;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.status = status;
        this.price = price;
        this.products = new HashMap<>();
        this.associatedOrder = null;
        
    }


    
    
    public int getId() {
        return id;
    }

    public Timestamp getBeginDate() {
        return beginDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public double getPrice() {
        return price;
    }
    
    public String getStatus() {
        return status;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public Order getAssociatedOrder() {
        return associatedOrder;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setBeginDate(Timestamp beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProducts(HashMap<Product, Integer> products) {
        this.products = products;
    }

    public void setAssociatedOrder(Order associatedOrder) {
        this.associatedOrder = associatedOrder;
    }
    
    
    
    
    
    

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    

    @Override
    public String toString() {
        return String.format("Id: %d | Status: %s | Price: %.2f €", id, status, price);
    }
    
    

    @Override
    public int compareTo(Order o) {
        return this.beginDate.compareTo(o.getBeginDate());
    }


    
    

    
}
