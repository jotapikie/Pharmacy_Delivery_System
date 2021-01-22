/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.util.Scanner;
import lapr.project.controller.AddGeographicalPointController;
import lapr.project.data.GeographicalPointDB;
import static lapr.project.ui.UtilsUI.header;

/**
 *
 * @author Helder
 */
public class AddGeographicalPointUI {
    
    private AddGeographicalPointController controller;
    private static Scanner read = new Scanner(System.in);

    public AddGeographicalPointUI() {
        this.controller = new AddGeographicalPointController(new GeographicalPointDB());
        try{
        header("x Add Point x");
        newGeographicalPoint();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void newGeographicalPoint() throws SQLException {
        System.out.println();
        System.out.println("Longitude:");
        double longitude = Double.parseDouble(read.nextLine());
        System.out.println();
        System.out.println("Latitude:");
        double latitude = Double.parseDouble(read.nextLine());
        System.out.println();
        System.out.println("Elevation (m):");
        double elevation = Double.parseDouble(read.nextLine());
        System.out.println();
        System.out.println("Description:");
        String description = read.nextLine();
        System.out.println();
        String geo = controller.newGeographicalPoint(longitude,latitude, elevation, description);
        System.out.println();
        System.out.println(geo);
        System.out.println("Do you really want to add this geographical Point? (y/n)");
        System.out.println();
        if(read.nextLine().equalsIgnoreCase("y")){
            controller.addToQueue();
            System.out.println();
            System.out.println("Geographical point added to the queue.");
            System.out.println("Do you want to insert another Geographical Point? (y/n)");
            System.out.println();
            if(read.nextLine().equalsIgnoreCase("y")){
                newGeographicalPoint();
            }else{
                System.out.println();
                System.out.println("Processing...");
                int rs = controller.savePoints();
                System.out.println("Sucess: " +rs+ " Geographical Point(s) were added to the system.");
            }
        }
    
    }


    
}
