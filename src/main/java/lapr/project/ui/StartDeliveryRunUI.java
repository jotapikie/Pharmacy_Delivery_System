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
import static lapr.project.ui.UtilsUI.header;

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
            header("x Start delivery run x");
            showAvailableRuns();;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void showAvailableRuns() throws SQLException{
        System.out.println();
        List<String> runs = controller.getDeliveryRuns();
        if(runs.isEmpty()){
            System.out.println("There are no delivery runs available in the moment, try again later.");
        }else{
            System.out.println("Available delivery runs:");
            System.out.println();
            for(String run : runs){
                System.out.println(run);
            }
            selectOne();
        }
        
             
    }
    
    private void selectOne() throws SQLException{
        System.out.println();
        System.out.println("Select one (id):");
        int id = Integer.parseInt(read.nextLine());
        System.out.println();
        String run = controller.selectDeliveryRun(id);
        if(run == null){
            System.out.println("Error: Invalid delivery run, try again.");
            showAvailableRuns();
        }else{
            System.out.println(run);
            System.out.println();
            System.out.println("Do you want to start this delivery? (y/n)");
            if(read.nextLine().equalsIgnoreCase("y")){
                showAvailableScooters();
            }else{
                System.out.println();
                System.out.println("Operation aborted");
            }
        }
    }    

    private void showAvailableScooters() throws SQLException {
        System.out.println();
        List<String> res = controller.getAvailableScooters();
        if(res.isEmpty()){
            System.out.println("No available scooters in the moment, try again later.");
        }else{
        System.out.println("Available Scooters:");
        System.out.println();
        for(String s : controller.getAvailableScooters()){
            System.out.println(s);
        }
        selectOneScooter();
        }
    }

    private void selectOneScooter() throws SQLException {
        System.out.println();
        System.out.println("Select one (id)");
        int id = Integer.parseInt(read.nextLine());
        String res = controller.selectScooter(id);
        System.out.println();
            if(res != null){
                System.out.println(res);
                System.out.println();
                System.out.println("Do you want to take this scooter for the delivery? (y/n)");
                if(read.nextLine().equalsIgnoreCase("y")){
                    startDelivery();
                }else{
                    System.out.println();
                    System.out.println("Operation aborted");
                }
            }else{
                System.out.println("Error: Invalid scooter selected, try again.");
                showAvailableScooters();
            }
        
    }

    private void startDelivery() throws SQLException {
       System.out.println("Starting the delivery...");
       boolean res = controller.startDeliveryRun();
       if(res){
       System.out.println("Sucess: Delivery run assigned to you.");
       System.out.println();
       String route = controller.getRoute();
       System.out.println(route==null?"No route found.":route);
       
       
       
       }else{
           System.out.println("Error: There is no way to reach all geographical points.");
       }



    }
    

    
    
    
}
