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

/**
 *
 * @author Helder
 */
public class NotifyReadyOrderController {
    
    private Order order;
    private final OrderDB orderDB;
    private final int idPharmacy;




    public NotifyReadyOrderController(OrderDB odb, int idPharmacy) {
        this.orderDB= odb;
        this.idPharmacy = idPharmacy;
        
    }
    
    public List<String> getPreparingOrders() throws SQLException{
        List<String> lst = new ArrayList<>();
        for(Order o : orderDB.getOrdersByStatus(idPharmacy, "Preparing")){
            lst.add(o.toString());
        }
        return lst;
        
    }
    
    public String getSelectedOrder(int id) throws SQLException{
        order = orderDB.getOrder(id);
        return order== null ? null : order.toString();
    }
    
    public boolean setOrderToReady() throws SQLException{
        if(order == null){
            return false;
        }
        
        orderDB.setStatus(order.getId(), "Prepared", idPharmacy);
        return true;
    }
    
    
}
