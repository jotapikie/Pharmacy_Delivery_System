/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.OrderDB;
import lapr.project.model.Order;
import lapr.project.utils.Utils;

/**
 *
 * @author Helder
 */
public class NotifyReadyOrderController {
    
    private Order order;
    private final OrderDB orderDB;
    private final int idPharmacy;
    private List<Order> ords;




    public NotifyReadyOrderController(OrderDB odb, int idPharmacy) {
        this.orderDB= odb;
        this.idPharmacy = idPharmacy;
        ords = new ArrayList<>();
        
    }

    public NotifyReadyOrderController(int idPharmacy) {
        this.orderDB = new OrderDB();
        this.idPharmacy = idPharmacy;
    }
    
    
    
    public List<String> getPreparingOrders() throws SQLException{
        ords = orderDB.getOrdersByStatus(idPharmacy, "Preparing");
        return Utils.listToString(ords);
    }
    
    public String getSelectedOrder(int id) throws SQLException{
        order = orderDB.getOrder(id);
        return (order== null || !ords.contains(order)) ? null : order.toString();
    }
    
    public boolean setOrderToReady() throws SQLException{
        if(order != null && ords.contains(order)){
             return orderDB.setStatus(order.getId(), "Prepared", idPharmacy);
        }
        return false;
    }
    
    
}
