/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.data.CourierDB;
import lapr.project.data.OrderDB;
import lapr.project.data.PhamarcyDB;
import lapr.project.model.Courier;
import lapr.project.model.Order;
import lapr.project.model.Pharmacy;

/**
 *
 * @author Diogo
 */
public class DeliverOrderController {
    
    private CourierDB cdb;
    private PhamarcyDB pdb;
    private OrderDB odb;
    private String courierEmail;
    private Courier cou;
    private Pharmacy pha;

    public DeliverOrderController(String courierEmail) {
        this.cdb = new CourierDB();
        this.pdb = new PhamarcyDB();
        this.odb = new OrderDB();
        this.courierEmail = courierEmail;
    }
    
    public List<String> getSuggestedOrders() throws SQLException{
        List<String> orders = new ArrayList<>();
        cou = cdb.getCourier(courierEmail);
        pha = pdb.getPhamarcyByCourier(cou.getEmail());
        pha.setOrders(odb.getOrdersByPhamarcy(pha.getId()));
        for(Order o : pha.getSuggestedOrders(cou)){
            orders.add(o.toString());
        }
        return orders;
    }
    
    public LinkedList<String> findRoute(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
}
