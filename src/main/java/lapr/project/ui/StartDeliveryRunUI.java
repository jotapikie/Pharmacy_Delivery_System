/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.controller.StartDeliveryRunController;
import lapr.project.data.ClientDB;
import lapr.project.data.DeliveryRunDB;
import lapr.project.data.GeographicalPointDB;
import lapr.project.data.VehicleDB;

/**
 *
 * @author Diogo
 */
public class StartDeliveryRunUI {
    private StartDeliveryRunController controller;
    private static Scanner read = new Scanner (System.in);
    public StartDeliveryRunUI(int idPharmacy, double weight, String email) {
        controller = new StartDeliveryRunController(new DeliveryRunDB(), new GeographicalPointDB(),new ClientDB(),new VehicleDB(), idPharmacy, weight, email);
        try {
            showAvailableRuns();;
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
        selectOne();
        
             
    }
    
    private void selectOne() throws SQLException{
        System.out.println("Select one delivery (0 to end):");
        int reg = Integer.parseInt(read.nextLine());
        if(reg != 0){
            String res = controller.selectDeliveryRun(reg);
            if(res != null){
                System.out.println("Selected delivery:");
                System.out.println(res);
                System.out.println("Do you really want to start this delivrey? (y/n)");
                if(read.nextLine().equalsIgnoreCase("y")){
                    showAvailableScooters();
                }
            }
 
        }
    }    

    private void showAvailableScooters() throws SQLException {
        System.out.println();
        List<String> res = controller.getAvailableScooters();
        if(res.isEmpty()){
            System.out.println("No available scooters in the moment.");
        }else{
        System.out.println("Available Scooters:");
        for(String s : controller.getAvailableScooters()){
            System.out.println(s);
        }
        selectOneScooter();
        }
    }

    private void selectOneScooter() throws SQLException {
        System.out.println("Select one (0 to end)");
        int id = Integer.parseInt(read.nextLine());
        if(id != 0){
            String res = controller.selectScooter(id);
            if(res != null){
                System.out.println("Selected scooter:");
                System.out.println(res);
                System.out.println("Do you want to take this scooter for the delivery? (y/n)");
                if(read.nextLine().equalsIgnoreCase("y")){
                    startDelivery();
                }else{
                    showAvailableScooters();
                }
            }
        }
    }

    private void startDelivery() throws SQLException {
       System.out.println("Starting the delivery...");
       boolean res = controller.startDeliveryRun();
       if(res){
       System.out.println("Sucess: Delivery run assigned to you.");
       System.out.println(controller.getRoute());
       
       
       }else{
           System.out.println("Error: There is no way to reach all geographical points.");
       }



    }
    

    
    
    
}
