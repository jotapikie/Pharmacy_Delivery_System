/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.util.Scanner;
import lapr.project.controller.RegisterClientController;
import lapr.project.data.AddressDB;
import lapr.project.data.ClientDB;
import lapr.project.data.CreditCardDB;
import static lapr.project.ui.UtilsUI.header;

/**
 *
 * @author Diogo
 */
public class RegisterClientUI {
    private RegisterClientController controller;
    private static Scanner read = new Scanner(System.in);
    
    public RegisterClientUI() {
        controller = new RegisterClientController(new ClientDB(), new AddressDB(), new CreditCardDB());
        header("x Regist as Client x");
        newAddress();
    }

    private void newAddress(){
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
        String add = controller.newAddress(street, longitude, latitude, elevation, city, port, zip, desc);
        System.out.println();
        System.out.println("Address:");
        System.out.println(add);
        System.out.println();
        System.out.println("Do you want this to be your address? (y/n)");
        if(read.nextLine().equalsIgnoreCase("y")){
            newCreditCard();
        }else{
            System.out.println();
            System.out.println("Operation aborted.");
        }
        }catch(IllegalArgumentException e){
            System.out.println();
            System.out.println("Error: "+e.getMessage());
            System.out.println();
            newAddress();
        }
        }catch(NumberFormatException e){
            System.out.println();
            System.out.println("Error: Invalid format, try again.");
            newAddress();
            
        }
        
    }

    private void newCreditCard(){
        try{
        System.out.println();
        System.out.println("2. Credit Card Information");
        System.out.println();
        System.out.println("Credit car number:");
        long nr = Long.parseLong(read.nextLine());
        System.out.println();
        System.out.println("Validaty Date (mm/yy): ");
        String date = read.nextLine();
        System.out.println();
        System.out.println("CCV: ");
        int ccv = Integer.parseInt(read.nextLine());
        System.out.println();
        try{
        String card = controller.newCreditCard(nr, date, ccv);
        System.out.println("Credit Card:");
        System.out.println(card);
        System.out.println();
        System.out.println("Is this your credit card? (y/n)");
        if(read.nextLine().equalsIgnoreCase("y")){
            newClient();
        }else{
            System.out.println();
            System.out.println("Operation aborted.");
        }
        }catch(IllegalArgumentException e){
            System.out.println();
            System.out.println("Error: "+e.getMessage());
            newCreditCard();
        }
        }catch(NumberFormatException x){
            System.out.println();
            System.out.println("Error: Invalid format, try again.");
            newCreditCard();
        }
    }

    private void newClient(){
        try{
        System.out.println();
        System.out.println("3. Personal Information");
        System.out.println();
        System.out.println("Phone number:");
        int phoneNr = Integer.parseInt(read.nextLine());
        System.out.println();
        System.out.println("NIF:");
        int nif = Integer.parseInt(read.nextLine());
        System.out.println();
        System.out.println("Name (First and Last):");
        String name = read.nextLine();
        System.out.println();
        System.out.println("Email: ");
        String email=read.nextLine();
        System.out.println();
        System.out.println("Password:");
        String pwd = read.nextLine();
        System.out.println();
        try{
        String cli = controller.newClient(name, email, pwd, nif, phoneNr);
        System.out.println(cli);
        System.out.println();
        System.out.println("Do you confirm the information? (y/n)");
        if(read.nextLine().equalsIgnoreCase("y")){
           
            try {
                System.out.println();
                System.out.println("Processing request...");
                controller.registClient();
                System.out.println();
                System.out.println("Sucess: You have been registed in the system.");
            } catch (SQLException ex) {
                System.out.println();
                System.out.println("Error: The email already exists in the system. Login into your acccout or try again.");
            }
            
        }else{
            System.out.println();
            System.out.println("Operation aborted.");
        }
        }catch(IllegalArgumentException e){
            System.out.println();
            System.out.println("Error: "+e.getMessage());
            newClient();
        }
        }catch(NumberFormatException e){
            System.out.println();
            System.out.println("Error: Invalid format, try again.");
        }
    }


}
