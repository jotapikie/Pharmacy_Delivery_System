/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.data.CourierDB;
import lapr.project.data.PhamarcyDB;
import lapr.project.model.Courier;
import lapr.project.model.Order;
import lapr.project.model.Phamarcy;

/**
 *
 * @author Diogo
 */
public class DeliverOrderController {
    
    private CourierDB cdb;
    private PhamarcyDB pdb;
    private String courierEmail;
    private Courier cou;
    private Phamarcy pha;

    public DeliverOrderController( String courierEmail) {
        this.cdb = new CourierDB();
        this.pdb = new PhamarcyDB();
        this.courierEmail = courierEmail;
    }
    
    public List<String> getSuggestedOrders(){
        List<String> orders = new ArrayList<>();
        cou = cdb.getCourier(courierEmail);
        pha = pdb.getPhamarcyByCourier(cou.getEmail());
        for(Order o : pha.getSuggestedOrders(cou)){
            orders.add(o.toString());
        }
        return orders;
    }
    
    public LinkedList<String> findRoute(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
}
