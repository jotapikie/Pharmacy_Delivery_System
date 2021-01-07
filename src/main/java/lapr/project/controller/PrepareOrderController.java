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
 * @author Diogo
 */
public class PrepareOrderController {
    
    private OrderDB odb;
    private String administratorEmail;
    private int orderId;

    public PrepareOrderController(OrderDB odb, String administratorEmail) {
        this.odb = odb;
        this.administratorEmail = administratorEmail;
        this.orderId = -1;
    }

    public PrepareOrderController(String administratorEmail) {
        this.odb = new OrderDB();
        this.administratorEmail = administratorEmail;
        this.orderId = -1;
    }
    
    public List<String> getReadyToPrepareOrders() throws SQLException{
        List<String> orders = new ArrayList<>();
        for(Order o : odb.getReadyToPrepareOrders(administratorEmail)){
            orders.add(o.toString());
        }
        return orders;
    }
    
    // ADICIONAR CONDICAO PARA VERIFICAR SE É DA SUA FARMACIA
    public String getSelectedOrder(int id) throws SQLException{
        Order ord = Order.getOrder(id);
        if(ord != null){
            orderId = ord.getId();
            return ord.toString();
        }
        orderId = -1;
        return null;
    }
    
    public void prepareOrder() throws SQLException{
        if(orderId > 0){
            odb.setStatus(orderId, "Preparing");
        }
    }
    
    

    
    
    
}
