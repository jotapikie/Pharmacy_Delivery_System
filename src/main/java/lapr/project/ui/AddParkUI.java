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
import lapr.project.controller.AddParkController;
import lapr.project.data.ParkDB;
import static lapr.project.ui.UtilsUI.header;

/**
 *
 * @author Diogo
 */
public class AddParkUI {

   private static Scanner read = new Scanner(System.in);
   private AddParkController controller;

    public AddParkUI(int id) {
         controller = new AddParkController(new ParkDB(), id);
         header("x Add Park x");
       try {
           newPark();
       } catch (SQLException ex) {
           System.out.println("The pharmacy with id "+id+ " is not registed in the system.");
       }
    }

    private void newPark() throws SQLException {
        try{
        System.out.println();
        System.out.println("Type (Scooter or Drone)");
        String type = read.nextLine();
        System.out.println();
        System.out.println("Maximum vehicles:");
        int maxVehicles = Integer.parseInt(read.nextLine());
        System.out.println();
        System.out.println("Number of slots that can charge:");
        int ableToCharge = Integer.parseInt(read.nextLine());
        System.out.println();
        System.out.println("Maximum energy:");
        double maxEnergy = Double.parseDouble(read.nextLine());
        System.out.println();
        try{
        System.out.println(controller.newPark(maxVehicles, ableToCharge, type, maxEnergy));
        System.out.println();
        System.out.println("Do you want to add this park to the pharmacy? (y/n)");
        if(read.nextLine().equalsIgnoreCase("y")){
            System.out.println();
            controller.addToQueue();
            System.out.println("Park added to the queue.");
            System.out.println();
            System.out.println("Do you want to add another park? (y/n)");
            if(read.nextLine().equalsIgnoreCase("y")){
                newPark();
            }else{
                System.out.println();
                System.out.println("Processing...");
                int added = controller.saveParks();
                System.out.println();
                if(added>0){
                    System.out.println("Success: " +added+" park(s) were added to the pharmacy.");
                }else{
                    System.out.println("Error: No park was added, try again later.");
                }
            }
        }else{
            System.out.println();
            System.out.println("Operation aborted");
        }
        }catch(IllegalArgumentException e){
            System.out.println("Error:" +e.getMessage());
        }
        }catch(NumberFormatException e){
            System.out.println();
            System.out.println("Error: Invalid data format, try again.");
            newPark();
        }
    }
    
}
