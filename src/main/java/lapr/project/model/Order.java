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

    
    public Order(int id, Date beginDate, Date endDate, String status, double price) {
        setId(id);
        setBeginDate(beginDate);
        setEndDate(endDate);
        setStatus(status);
        setPrice(price);
        setProducts(new HashMap<>());
        
    }
    
    public Order(int id, double price, HashMap<Product, Integer> products){
        setId(id);
        setBeginDate(new Date());
        setEndDate(null);
        setStatus("Processing");
        setPrice(price);
        setProducts(products);
        
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

    public final void setId(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("Order's id is invalid.");
        this.id = id;
    }

    public final void setBeginDate(Date beginDate) {
        if (beginDate == null || beginDate.after(new Date()))
            throw new IllegalArgumentException("Order's begin date is invalid.");
        this.beginDate = beginDate;
    }

    public final void setEndDate(Date endDate) {
        if  (endDate == null)
            this.endDate = null;
        else
            if (endDate.after(new Date()) || endDate.after(beginDate))
                throw new IllegalArgumentException("Order's end date is invalid.");
        this.endDate = endDate;
    }

    public final void setPrice(double price) {
        if (price < 0)
            throw new IllegalArgumentException("Order's price is invalid.");
        this.price = price;
    }

    public final void setProducts(HashMap<Product, Integer> products) {
        if (products == null)
            throw new IllegalArgumentException("Order's products is invalid.");
        this.products = products;
    }
    
    
    
   public final void setStatus(String status) {
        if (status == null || status.isEmpty())
            throw new IllegalArgumentException("Order's status is invalid.");
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
