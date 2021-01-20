/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.util.Scanner;
import lapr.project.controller.AddPathController;
import lapr.project.data.GeographicalPointDB;
import lapr.project.data.PathwayDB;

/**
 *
 * @author Helder
 */
public class AddPathUI {
    private AddPathController controller;
    private static Scanner read = new Scanner(System.in);

    public AddPathUI() throws SQLException {
        controller=new AddPathController(new GeographicalPointDB(),new PathwayDB());
        showAvailableGeoPoint();
        selectPoints();
                
    }
    
    private void showAvailableGeoPoint() throws SQLException{
        System.out.println("Geographical Points:");
        for(String s : controller.getAvailableGeographicalPoints()){
            System.out.println(s);
        }
        System.out.println();
    }

    private void selectPoints() throws SQLException {
        System.out.println("Origin longitude:");
        double longitude = Double.parseDouble(read.nextLine());
        System.out.println("Origin latitude:");
        double latitude = Double.parseDouble(read.nextLine());
        System.out.println("Destination latitude:");
        double longitude1 = Double.parseDouble(read.nextLine());
        System.out.println("Destination longitude:");
        double latitude1 = Double.parseDouble(read.nextLine());
        System.out.println("kinetic Coeficient:");
        double kineticCoef = Double.parseDouble(read.nextLine());
        System.out.println("Wind Direction:");
        int windDirection = Integer.parseInt(read.nextLine());
        System.out.println("Wind speed:");
        double windSpeed = Double.parseDouble(read.nextLine());
        System.out.println("Street:");
        String street = read.nextLine();
        controller.selectPoints(longitude,latitude,longitude1,latitude1,kineticCoef,windDirection,windSpeed,street);
        System.out.println();
        System.out.println("Do you really want to add this Pathway? (y/n)");
        if(read.nextLine().equalsIgnoreCase("y")){
            controller.addToQueue();
            System.out.println("Pathway added to the queue.");
            System.out.println("Do you want to insert another Pathway? (y/n)");
            if(read.nextLine().equalsIgnoreCase("y")){
                selectPoints();
            }else{
                int rs = controller.savePaths();
                System.out.println("Sucess: " +rs+ " Pathway(s) were added to the system.");
            }
        }
    }
    
    
}
