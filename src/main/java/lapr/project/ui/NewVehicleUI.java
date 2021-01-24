/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.util.Scanner;
import lapr.project.controller.NewVehicleController;
import lapr.project.data.VehicleDB;
import static lapr.project.ui.UtilsUI.header;

/**
 *
 * @author Helder
 */
public class NewVehicleUI {
    
    private NewVehicleController controller;
    private static Scanner read = new Scanner(System.in);
    private static int added=0;
    
    public NewVehicleUI(int id) {
        controller = new NewVehicleController(new VehicleDB(),id);
        header("x New Vehicle x");
        newVehicle();
       
    }

    private void newVehicle() {
        try{
        System.out.println("Type (scooter/drone)");
        String type=read.nextLine();
        System.out.println();
        System.out.println("ID:");
        int id = Integer.parseInt(read.nextLine());
        System.out.println();
        System.out.println("Max Battery:");
        int max = Integer.parseInt(read.nextLine());
        System.out.println();
        System.out.println("Current Battery:");
        int current = Integer.parseInt(read.nextLine());
        try{
            if(type.equalsIgnoreCase("scooter")){
            System.out.println(controller.newEScooter(id,max, current));
            }else if(type.equalsIgnoreCase("drone")){
                System.out.println();
                System.out.println("Weight:");
                int weight = Integer.parseInt(read.nextLine());
                System.out.println();
                System.out.println("Motor:");
                int motor = Integer.parseInt(read.nextLine());
                System.out.println();
                System.out.println(controller.newDrone(id,weight,max, current,motor));
            }
        System.out.println();
        System.out.println("Do you want to add this vehicle to the pharmacy? (y/n)");
        if(read.nextLine().equalsIgnoreCase("y")){
            System.out.println();
            controller.addVehicleToQueue();
            System.out.println("Vehicle added to the queue.");
            System.out.println();
            System.out.println("Do you want to add another vehicle? (y/n)");
            if(read.nextLine().equalsIgnoreCase("y")){
                newVehicle();
            }else{
                System.out.println();
                added += 1;
                controller.addVehicleToQueue();
                System.out.println();
                if(added>0){
                    System.out.println("Success: " +added+" vehicles(s) were added to the pharmacy.");
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
            newVehicle();
        }
    }
}
