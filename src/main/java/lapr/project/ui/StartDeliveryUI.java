/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import lapr.project.controller.DeliverOrderController;
import lapr.project.data.DeliveryRunDB;
import lapr.project.data.GeographicalPointDB;

/**
 *
 * @author Diogo
 */
public class StartDeliveryUI {
    private DeliverOrderController controller;

    public StartDeliveryUI() {
        controller = new DeliverOrderController(new DeliveryRunDB(), new GeographicalPointDB(), 1, 67.8, "courier1@lapr.com");
        
    }
    
    
}
