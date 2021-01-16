/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.controller.StartDeliveryRunController;
import lapr.project.data.DeliveryRunDB;
import lapr.project.data.GeographicalPointDB;

/**
 *
 * @author Diogo
 */
public class StartDeliveryRunController {
    private StartDeliveryRunController controller;
    
    public StartDeliveryRunController() {
        controller = new StartDeliveryRunController(new DeliveryRunDB(), new GeographicalPointDB(), 1, 65.7, "courier1@lapr3.com");
        try {
            showAvailableRuns();
            selectOne();
            startDelivery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void showAvailableRuns() throws SQLException{
        System.out.println("Available runs:");
        for(String run : controller.getDeliveryRuns()){
            System.out.println(run);
        }
        System.out.println();
             
    }
    
    private void selectOne(){
        System.out.println("Delivery Selected:");
        System.out.println(controller.selectDeliveryRun(1));
        System.out.println();
    }    
    
    private void startDelivery() throws SQLException{
        System.out.println("Starting the delivery...");
            
        int nr = controller.startDeliveryRun();
        if(nr == 0){
            System.out.println("There are currently no scooters available");
        }else{
            System.out.println("The delivery run was assigned to you. Start it.");
            System.out.println("Vehicle QRCode:" +nr);
        }
        }
    
    
    
}
