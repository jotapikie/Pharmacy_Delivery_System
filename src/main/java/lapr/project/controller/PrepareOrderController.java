/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.data.OrderDB;
import lapr.project.model.Order;

/**
 *
 * @author Diogo
 */
public class PrepareOrderController {
    
    private OrderDB odb;
    private String administratorEmail;
    private Order ord;

    public PrepareOrderController(OrderDB odb, String administratorEmail) {
        this.odb = odb;
        this.administratorEmail = administratorEmail;
    }

    public PrepareOrderController(String administratorEmail) {
        this.odb = new OrderDB();
        this.administratorEmail = administratorEmail;
    }
    
    public List<String> getReadyToPrepareOrders(){
        return odb.getReadyToPrepareOrders(administratorEmail);
    }
    
    public String getSelectedOrder(int id){
        ord = Order.getOrder(id);
        return ord == null ? null : ord.toString();
    }
    
    public void prepareOrder(){
        if(ord != null){
            odb.setStatus(ord, "Preparing");
        }
    }
    
    

    
    
    
}
