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
import lapr.project.data.PhamarcyDB;
import lapr.project.model.Order;
import lapr.project.model.Phamarcy;

/**
 *
 * @author Diogo
 */
public class PrepareOrderController {
    
    private OrderDB odb;
    private PhamarcyDB pdb;
    private String administratorEmail;
    private Order ord;
    private Phamarcy pha;


    public PrepareOrderController(String administratorEmail) {
        this.odb = new OrderDB();
        this.pdb = new PhamarcyDB();
        this.administratorEmail = administratorEmail;
    }
    
    public List<String> getReadyToPrepareOrders() throws SQLException{
        pha = pdb.getPharmacyByAdministrator(administratorEmail);
        List<String> lst = new ArrayList<>();
        for(Order o : odb.getOrdersByStatus(pha.getId(), "Processed")){
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
            odb.setStatus(ord.getId(), "Preparing", pha.getId());
        }
    }
    
    

    
    
    
}
