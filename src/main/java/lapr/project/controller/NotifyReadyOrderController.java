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
import lapr.project.model.Product;

/**
 *
 * @author Helder
 */
public class NotifyReadyOrderController {
    
    private Order order;
    private OrderDB orderDB;

    public NotifyReadyOrderController(OrderDB orderDB) {
        this.orderDB = orderDB;
    }

    public NotifyReadyOrderController() {
        this.orderDB= new OrderDB();
    }
    
    public List<String> getAllOrderIDPreparing() throws SQLException{
        List<String> lst = new ArrayList<>();
        for(Order o : orderDB.getAllOrderIDPreparing()){
            lst.add(o.toString());
        }
        return lst;
        
    }
    
    public String getOrderByID(int id) throws SQLException{
        order = orderDB.getOrder(id);
        return orderDB== null ? null : order.toString();
    }
    
    public boolean setOrderToReady(){
        if(order == null){
            return false;
        }
        
        orderDB.setOrderToReady();
        return true;
    }
    
    
}
