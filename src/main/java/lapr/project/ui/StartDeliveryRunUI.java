/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.controller.StartDeliveryRunController;
import lapr.project.data.ClientDB;
import lapr.project.data.DeliveryRunDB;
import lapr.project.data.GeographicalPointDB;

/**
 *
 * @author Diogo
 */
public class StartDeliveryRunUI {
    private StartDeliveryRunController controller;
    private static Scanner read = new Scanner (System.in);
    public StartDeliveryRunUI(int idPharmacy, double weight, String email) {
        controller = new StartDeliveryRunController(new DeliveryRunDB(), new GeographicalPointDB(),new ClientDB(), idPharmacy, weight, email);
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
        int reg = Integer.parseInt(read.nextLine());
        System.out.println(controller.selectDeliveryRun(reg));
        System.out.println();
    }    
    
    private void startDelivery() throws SQLException{
        System.out.println("Starting the delivery...");
            
        boolean res = controller.startDeliveryRun();
        if(res){
            String route = controller.getRoute();
            System.out.println("The delivery run was assigned to you");
            if(route == null){
                System.out.println("Couldn't find a route.");
            }else{
                System.out.println("Suggested Route:" +route);
            }
            System.out.println("Vehicle QRCode: " +controller.getVehicle());
        }else{
            System.out.println("There are no scooters available in the moment, try again later.");
        }
        }
    
    
    
}
