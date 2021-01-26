/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.util.Scanner;
import lapr.project.controller.RegisterPharmacyController;
import lapr.project.data.AddressDB;
import lapr.project.data.AdministratorDB;
import lapr.project.data.ParkDB;
import lapr.project.data.PharmacyDB;
import static lapr.project.ui.UtilsUI.header;

/**
 *
 * @author Diogo
 */
public class RegisterPharmacyUI {
    private RegisterPharmacyController controller;
    private static Scanner read = new Scanner(System.in);

    public RegisterPharmacyUI() {
        controller = new RegisterPharmacyController(new PharmacyDB(), new AddressDB(), new AdministratorDB(), new ParkDB());
        header("x Add Pharmacy x");
        try {
            newAddress();
        } catch (SQLException ex) {
            System.out.println("Error: An error occured when tried to communicate with database.");
        }
    }

    private void newAddress() throws SQLException {
        try{
        System.out.println();
        System.out.println("1. Address information");
        System.out.println();
        System.out.println("Street:");
        String street = read.nextLine();
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
        System.out.println("City:");
        String city = read.nextLine();
        System.out.println();
        System.out.println("Port Number:");
        int port = Integer.parseInt(read.nextLine());
        System.out.println();
        System.out.println("Zip Code:");
        String zip = read.nextLine();
        System.out.println();
        System.out.println("Point description:");
        String desc = read.nextLine();
        System.out.println();
        try{
        String add = controller.newAddress(street, longitude, latitude, elevation, city, zip, port, desc);
        System.out.println();
        System.out.println("Address:");
        System.out.println(add);
        System.out.println();
        System.out.println("Do you want this to be your address? (y/n)");
        if(read.nextLine().equalsIgnoreCase("y")){
            newAdministrator();
        }else{
            System.out.println();
            System.out.println("Operation aborted.");
        }
        }catch(IllegalArgumentException e){
            System.out.println();
            System.out.println("Error: "+e.getMessage());
            newAddress();
        }
        }catch(NumberFormatException e){
            System.out.println();
            System.out.println("Error: Invalid format, try again.");
            newAddress();
            
        }
    }

    private void newAdministrator() throws SQLException {
        System.out.println();
        System.out.println("2. Administrator information");
        System.out.println();
        System.out.println("Name: ");
        String name = read.nextLine();
        System.out.println();
        System.out.println("Email: ");
        String email = read.nextLine();
        System.out.println();
        System.out.println("Password: ");
        String pass = read.nextLine();
        System.out.println();
        try{
        String admin = controller.newAdministrator(name, email, pass);
        System.out.println(admin);
        System.out.println();
        System.out.println("Do you confirm the pharmacy administrator? (y/n)");
        if(read.nextLine().equalsIgnoreCase("y")){
            newPark();
        }else{
            System.out.println();
            System.out.println("Operation aborted");
        }
        }catch(IllegalArgumentException e){
            System.out.println("Error: "+e.getMessage());
            newAdministrator();
        }
    }

    private void newPark() throws SQLException {
        try{
        System.out.println();
        System.out.println("3. Park information");
        System.out.println();
        System.out.println("Type (Scooter or Drone): ");
        String category = read.nextLine();
        System.out.println();
        System.out.println("Maximum vehicles: ");
        int maxVehicles = Integer.parseInt(read.nextLine());
        System.out.println();
        System.out.println("Park slots that can charge: ");
        int ableToCharge = Integer.parseInt(read.nextLine());
        System.out.println();
        System.out.println("Maximum Energy: ");
        double maxEnergy = Double.parseDouble(read.nextLine());
        System.out.println();
        try{
        String park = controller.newPark(category, maxVehicles, ableToCharge, maxEnergy);
        System.out.println(park);
        System.out.println();
        System.out.println("Do you confirm the park? (y/n)");
        if(read.nextLine().equalsIgnoreCase("y")){
            newPharmacy();
        }else{
            System.out.println();
            System.out.println("Operation aborted");
        }
        }catch(IllegalArgumentException e){
            System.out.println("Error: " +e.getMessage());
            newPark();
            
        }
        }catch(NumberFormatException e){
            System.out.println();
            System.out.println("Error: Invalid data format, try again.");
            newPark();
        }
    }

    private void newPharmacy() throws SQLException {
        try{
        System.out.println();
        System.out.println("4. Pharmacy information");
        System.out.println();
        System.out.println("Name:");
        String name = read.nextLine();
        System.out.println();
        System.out.println("Phone number:");
        int phone = Integer.parseInt(read.nextLine());
        System.out.println();
        try{
        String pharmacy = controller.newPharmacy(name, phone);
        System.out.println(pharmacy);
        System.out.println();
        System.out.println("Do you want to regist this pharmacy? (y/n)");
        if(read.nextLine().equalsIgnoreCase("y")){
            System.out.println();
            controller.addToQueue();
            System.out.println("Pharmacy added to the queue.");
            System.out.println();
            System.out.println("Do you want to add more pharmacies? (y/n)");
            if(read.nextLine().equalsIgnoreCase("y")){
                newAddress();
            }else{
                System.out.println();
                System.out.println("Processing...");
                int added = controller.registPharmacies();
                System.out.println();
                System.out.println("Sucess: " +added+" pharmacies were added to the system.");
            }
        }else{
            System.out.println();
            System.out.println("Operation aborted");
        }
        }catch(IllegalArgumentException e){
            System.out.println("Error: "+e.getMessage());
            newPharmacy();
        }
        }catch(NumberFormatException e){
            System.out.println();
            System.out.println("Error: Invalid data format, try again.");
            newPharmacy();
            
        }
        
    }
    
    
}
