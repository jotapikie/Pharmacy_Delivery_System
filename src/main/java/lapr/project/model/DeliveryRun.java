/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diogo
 */
public class DeliveryRun {
    
    private int id;
    private List<Order> orders;
    private VehicleCategory vehicleAssigned;

    public DeliveryRun(int id, List<Order> orders, VehicleCategory vehicleAssigned) {
        setId(id);
        setOrders(orders);
        this.vehicleAssigned = vehicleAssigned;
    }
    
    

    public DeliveryRun(int id, List<Order> orders) {
        setId(id);
        setOrders(orders);
    }

    public int getId() {
        return id;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public VehicleCategory getVehicleAssigned() {
        return vehicleAssigned;
    }
    
    

    public final void setId(int id) {
        if(id <= 0){
            throw new IllegalArgumentException("Invalid delivery run id");
        }
        this.id = id;
    }

    public final void setOrders(List<Order> orders) {
        if(orders == null){
            throw new IllegalArgumentException("Invalid delivery run id");
        }
        this.orders = new ArrayList<>(orders);
    }

    public void setVehicleAssigned(VehicleCategory vehicleAssigned) {
        this.vehicleAssigned = vehicleAssigned;
    }
    
    
    
    
    

    @Override
    public String toString() {
        return String.format("%d %s", id, orders);
    }

    
    
}
