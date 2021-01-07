/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Date;

/**
 *
 * @author Diogo
 */
public class Order {
    private int id;
    private Date beginDate;
    private Date endDate;
    private String status;
    private float price;

    public Order(int id, Date beginDate, Date endDate, String status, float price) {
        this.id = id;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.status = status;
        this.price = price;
    }

    public int getId() {
        return id;
    }
    
    

    
}
