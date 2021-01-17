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
import java.util.Objects;
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
        setId(Constants.DEFAULT_ID);
        setBeginDate(Constants.DEFAULT_DATE);
        setEndDate(Constants.DEFAULT_DATE);
        setStatus(Constants.DEFAULT_STATUS);
        setPrice(Constants.DEFAULT_PRICE);
        setProducts(new HashMap<>());
        setAssociatedOrder(null);
    }
    

    
    public Order(int id, Timestamp beginDate, Timestamp endDate, String status, double price) {
        setId(id);
        setBeginDate(beginDate);
        setEndDate(endDate);
        setStatus(status);
        setPrice(price);
        setProducts(new HashMap<>());
        setAssociatedOrder(null);
        
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
    
    public final void setId(int id) {
        if(id <= 0){
            throw new IllegalArgumentException("Invalid order id.");
        }
        this.id = id;
    }

    public final void setBeginDate(Timestamp beginDate) {
        if(beginDate == null){
            throw new IllegalArgumentException("Invalid order begin date.");
        }
        this.beginDate = beginDate;
    }

    public final void setEndDate(Timestamp endDate) {
        if(endDate!= null && endDate.compareTo(beginDate)<0){
            throw new IllegalArgumentException("Invalid order end date.");
        }
        this.endDate = endDate;
    }

    public final void setPrice(double price) {
        if(price < 0){
            throw new IllegalArgumentException("Invalid order id.");
        }
        this.price = price;
    }

    public final void setProducts(Map<Product, Integer> products) {
        if(products == null){
            throw new IllegalArgumentException("Invalid order products.");
        }
        this.products = products;
    }

    public final void setAssociatedOrder(Order associatedOrder) {
        if(associatedOrder == this){
            throw new IllegalArgumentException("Invalid associated order.");
        }
        this.associatedOrder = associatedOrder;
    }
    
    public final void setStatus(String status) {
        if(status.isEmpty() || status == null || (!status.equals("Processing") && !status.equals("Processed") && !status.equals("Preparing") && !status.equals("Prepared") && !status.equals("Delivering") && !status.equals("Delivered"))){
            throw new IllegalArgumentException("Invalid order status");
        }
        this.status = status;
    }
    
    
    

    @Override
    public String toString() {
        return String.format("Id: %d | Status: %s | Price: %.2f €", id, status, price);
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
        final Order other = (Order) obj;
        
        return this.id == other.getId();
    }

    @Override
    public int hashCode() {
        return id;
    }
    
    
    
    
    

    @Override
    public int compareTo(Order o) {
        return this.beginDate.compareTo(o.getBeginDate());
    }


    
    

    
}
