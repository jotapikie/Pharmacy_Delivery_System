/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.List;

/**
 *
 * @author Diogo
 */
public class DeliveryRun {
    
    private int id;
    private List<Order> orders;

    public DeliveryRun(int id, List<Order> orders) {
        this.id = id;
        this.orders = orders;
    }

    public int getId() {
        return id;
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return String.format("%d %s", id, orders);
    }

    
    
}
