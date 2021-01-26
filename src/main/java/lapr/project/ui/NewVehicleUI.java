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
import lapr.project.controller.NewVehicleController;
import static lapr.project.ui.UtilsUI.header;

/**
 *
 * @author Diogo
 */
public class NewVehicleUI {
    private static Scanner read = new Scanner(System.in);
    private NewVehicleController controller;

    public NewVehicleUI(int id) {
        try {
            controller = new NewVehicleController(id);
            header("x Add vehicle x");
            selectOne();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void selectOne() throws SQLException {
       System.out.println();
       System.out.println("Do you want to insert a scooter(1) or drone(2)?");
       String ans = read.nextLine();
       if(ans.equalsIgnoreCase("1")){
           System.out.println();
           System.out.println("Max battery:");
           double max = Double.parseDouble(read.nextLine());
           System.out.println();
           System.out.println("Max battery:");
           double current = Double.parseDouble(read.nextLine());
           String sc = controller.newEScooter(max, current);
           System.out.println();
           System.out.println(sc);
           System.out.println();
           System.out.println("Do you want to insert this scooter? (y/n)");
           if(read.nextLine().equalsIgnoreCase("y")){
               controller.addVehicleToQueue();
               System.out.println();
               System.out.println("Scooter added.");
               System.out.println();
               System.out.println("Do you want to insert more vehicles? (y/n)");
               if(read.nextLine().equalsIgnoreCase("y")){
                   selectOne();
               }else{
                   System.out.println();
                   System.out.println("Adding vehicles...");
                   int added = controller.registerVehicles();
                   System.out.println();
                   if(added > 0){
                       System.out.println("Sucess: "+added+" vehicles were added.");
                   }else{
                       System.out.println("Error: No vehicle was added, try again later.");
                   }
               }
            }else{
                System.out.println();
                System.out.println("Operation aborted");
            }
           
       }else if(ans.equalsIgnoreCase("2")){
           System.out.println();
           System.out.println("Max battery:");
           double max = Double.parseDouble(read.nextLine());
           System.out.println();
           System.out.println("Current battery:");
           double current = Double.parseDouble(read.nextLine());
           String sc = controller.newDrone(max, current);
           System.out.println();
           System.out.println(sc);
           System.out.println();
           System.out.println("Do you want to insert this drone? (y/n)");
           if(read.nextLine().equalsIgnoreCase("y")){
               controller.addVehicleToQueue();
               System.out.println();
               System.out.println("Drone added.");
               System.out.println();
               System.out.println("Do you want to insert more vehicles? (y/n)");
               if(read.nextLine().equalsIgnoreCase("y")){
                   selectOne();
               }else{
                   System.out.println();
                   System.out.println("Adding vehicles...");
                   int added = controller.registerVehicles();
                   System.out.println();
                   if(added > 0){
                       System.out.println("Sucess: "+added+" vehicles were added.");
                   }else{
                       System.out.println("Error: No vehicle was added, try again later.");
                   }
               }
            }else{
                System.out.println();
                System.out.println("Operation aborted");
            }
       }else{
           System.out.println();
           System.out.println("Error: Invalid vehicle selected, try again.");
           selectOne();
       }
    }
    
    
    
}
