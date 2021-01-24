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
import lapr.project.utils.Utils;

/**
 *
 * @author Diogo
 */
public class PrepareOrderController {
    
    private final OrderDB odb;
    private final int idPhamarcy;
    private Order ord;
    private List<Order> ords;


    public PrepareOrderController(OrderDB odb, int idPhamarcy) {
        this.odb = odb;
        this.idPhamarcy = idPhamarcy;
        ords = new ArrayList<>();
    }

    public PrepareOrderController(int idPharmacy) {
        this.odb = new OrderDB();
        this.idPhamarcy = idPharmacy;
        this.ords = new ArrayList<>();
    }
    
    
    
    public List<String> getReadyToPrepareOrders() throws SQLException{
        ords = odb.getOrdersByStatus(idPhamarcy, "Processed");
        return Utils.listToString(ords);
    }
    
    public String getSelectedOrder(int id) throws SQLException{
        ord = odb.getOrder(id);
        return (ord == null || !ords.contains(ord)) ? null : ord.toString();
    }
    
    public boolean prepareOrder(){
        if(ord!=null && ords.contains(ord)){
            return odb.setStatus(ord.getId(), "Preparing", idPhamarcy);
        }
        return false;
    }
    
    

    
    
    
}
