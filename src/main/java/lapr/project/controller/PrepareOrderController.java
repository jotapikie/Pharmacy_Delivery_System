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
    private Order ord;

    public PrepareOrderController(OrderDB odb, String administratorEmail) {
        this.odb = odb;
        this.administratorEmail = administratorEmail;
    }

    public PrepareOrderController(String administratorEmail) {
        this.odb = new OrderDB();
        this.administratorEmail = administratorEmail;
    }
    
    public List<String> getReadyToPrepareOrders() throws SQLException{
        List<String> lst = new ArrayList<>();
        for(Order o : odb.getOrdersByStatus(administratorEmail, "Processed")){
            lst.add(o.toString());
        }
        return lst;
        
    }
    
    public String getSelectedOrder(int id) throws SQLException{
        ord = odb.getOrder(id);
        if(isValid()){
            return ord.toString();
        }
        
        return null;
    }
    
    private boolean isValid(){
        if(ord == null){
            return false;
        }
        
        // adicionar restantes condicoes
        return true;
    }
    
    public void prepareOrder() throws SQLException{
        if(isValid()){
            odb.setStatus(ord.getId(), "Preparing");
        }
    }
    
    

    
    
    
}
