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
import lapr.project.controller.AddPathController;
import lapr.project.data.GeographicalPointDB;
import lapr.project.data.PathwayDB;
import lapr.project.model.StreetType;
import static lapr.project.ui.UtilsUI.header;

/**
 *
 * @author Helder
 */
public class AddPathUI {
    private AddPathController controller;
    private static Scanner read = new Scanner(System.in);

    public AddPathUI(){
        try {
            header("x Add Pathway x");
            controller=new AddPathController(new GeographicalPointDB(),new PathwayDB());
            showAvailablePoints();
        } catch (SQLException ex) {
            System.out.println();
            System.out.println("Error: There is an already defined path between those 2 points, try with others.");

        }
                
    }
    
    private void showAvailablePoints() throws SQLException{
        System.out.println();
        List<String> res = controller.getAvailableGeographicalPoints();
        if(res.isEmpty()){
            System.out.println("In the moment, there are no geographical points availables, add it first.");
        }else{
            System.out.println("Available Geographical points:");
            System.out.println();
            for(String s : res){
                System.out.println(s);
            }
            System.out.println();
            selectPoints();
        }
 
    }

    private void selectPoints() throws SQLException {
        try{
        System.out.println("Longitude (Origin):");
        double longitude1 = Double.parseDouble(read.nextLine());
        System.out.println();
        System.out.println("Latitude (Origin):");
        double latitude1 = Double.parseDouble(read.nextLine());
        System.out.println();
        System.out.println("Longitude (Destination):");
        double longitude2 = Double.parseDouble(read.nextLine());
        System.out.println();
        System.out.println("Latitude (Destination):");
        double latitude2 = Double.parseDouble(read.nextLine());
        System.out.println();
        System.out.println("Wind Direction (Degrees):");
        int windDirection = Integer.parseInt(read.nextLine());
        System.out.println();
        System.out.println("Wind Speed:");
        double windSpeed = Double.parseDouble(read.nextLine());
        System.out.println();
        System.out.println("Road Category (Asphalt, Off-Road, Sidewalk):");
        String streetType = read.nextLine();
        System.out.println();
        System.out.println("Name (Street):");
        String street = read.nextLine();
        System.out.println();
        String res = controller.selectPoints(longitude1, latitude1, longitude2, latitude2, streetType, windDirection, windSpeed, street);
        if(res == null){
            System.out.println();
            System.out.println("Error: Invalid data inserted, try again.");
            showAvailablePoints();
        }else{
            System.out.println(res);
            System.out.println();
            System.out.println("Do you want to add this path to the system? (y/n)");
            if(read.nextLine().equalsIgnoreCase("y")){
                controller.addToQueue();
                System.out.println();
                System.out.println("Path added to the queue.");
                System.out.println();
                System.out.println("Do you want to add more paths? (y/n)");
                if(read.nextLine().equalsIgnoreCase("y")){
                    showAvailablePoints();
                }else{
                    System.out.println();
                    System.out.println("Processing...");
                    int nr = controller.savePaths();
                    System.out.println("Sucess: " +nr+" path(s) were added to the system.");
                    
                }
            }
        }

        }catch(NumberFormatException e){
            System.out.println();
            System.out.println("Error: Invalid data inserted, try again.");
            showAvailablePoints();
        }
    }


      
    
    
}
