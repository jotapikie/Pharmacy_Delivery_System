/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Date;

/**
 *
 * @author ivoal
 */
public class DeliveryOrder {
    
    private int id;
    private Date bdate;
    private Date edate;
    private String status;
    private double price;
    private String email;
    private int delivrun_id;
    private int pharm_id;

    public DeliveryOrder(int id, Date bdate, Date edate, String status, double price, String email, int delivrun_id, int pharm_id) {
        this.id = id;
        this.bdate = bdate;
        this.edate = edate;
        this.status = status;
        this.price = price;
        this.email = email;
        this.delivrun_id = delivrun_id;
        this.pharm_id = pharm_id;
    }

    public int getId() {
        return id;
    }

    public Date getBdate() {
        return bdate;
    }

    public Date getEdate() {
        return edate;
    }

    public String getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }

    public String getEmail() {
        return email;
    }

    public int getDelivrun_id() {
        return delivrun_id;
    }

    public int getPharm_id() {
        return pharm_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDelivrun_id(int delivrun_id) {
        this.delivrun_id = delivrun_id;
    }

    public void setPharm_id(int pharm_id) {
        this.pharm_id = pharm_id;
    }

    @Override
    public String toString() {
        return "DeliveryOrder{" + "id=" + id + ", bdate=" + bdate + ", edate=" + edate + ", status=" + status + ", price=" + price + ", email=" + email + ", delivrun_id=" + delivrun_id + ", pharm_id=" + pharm_id + "}";
    }
    
    
    
    
}
