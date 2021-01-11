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
    
    private final OrderDB odb;
    private final int idPhamarcy;
    private Order ord;


    public PrepareOrderController(OrderDB odb, int idPhamarcy) {
        this.odb = odb;
        this.idPhamarcy = idPhamarcy;
    }
    
    public List<String> getReadyToPrepareOrders() throws SQLException{
        List<String> lst = new ArrayList<>();
        for(Order o : odb.getOrdersByStatus(idPhamarcy, "Processed")){
            lst.add(o.toString());
        }
        return lst;
        
    }
    
    public String getSelectedOrder(int id) throws SQLException{
        ord = odb.getOrder(id);
        return ord == null ? null : ord.toString();
    }
    
    public void prepareOrder() throws SQLException{
        if(ord!=null){
            odb.setStatus(ord.getId(), "Preparing", idPhamarcy);
        }
    }
    
    

    
    
    
}
