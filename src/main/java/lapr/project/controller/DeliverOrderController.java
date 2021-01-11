/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.LinkedList;

import lapr.project.data.CourierDB;
import lapr.project.data.OrderDB;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Courier;
import lapr.project.model.Pharmacy;

/**
 *
 * @author Diogo
 */
public class DeliverOrderController {
    
    private CourierDB cdb;
    private PharmacyDB pdb;
    private OrderDB odb;
    private String courierEmail;
    private Courier cou;
    private Pharmacy pha;

    public DeliverOrderController(String courierEmail) {
        this.cdb = new CourierDB();
        this.pdb = new PharmacyDB();
        this.odb = new OrderDB();
        this.courierEmail = courierEmail;
    }
    
    /*public List<String> getSuggestedOrders() throws SQLException{
        List<String> orders = new ArrayList<>();
        cou = cdb.getCourier(courierEmail);
        pha = pdb.getPhamarcyByCourier(cou.getEmail());
        pha.setOrders(odb.getOrdersByPhamarcy(pha.getId()));
        for(Order o : pha.getSuggestedOrders(cou)){
            orders.add(o.toString());
        }
        return orders;
    }*/
    
    public LinkedList<String> findRoute(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
}
