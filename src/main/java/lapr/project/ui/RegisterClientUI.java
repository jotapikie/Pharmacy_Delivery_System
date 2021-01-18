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

/**
 *
 * @author Diogo
 */
public class RegisterClientUI {
    private RegisterClientController controller;
    private static Scanner read = new Scanner(System.in);
    
    public RegisterClientUI() {
        controller = new RegisterClientController(new ClientDB(), new AddressDB(), new CreditCardDB());
        newAddress();
    }

    private void newAddress(){
        System.out.println("1. Address information");
        System.out.println("Street");
        String street = read.nextLine();
        System.out.println("Longitude:");
        double longitude = Double.parseDouble(read.nextLine());
        System.out.println("Latitude:");
        double latitude = Double.parseDouble(read.nextLine());
        System.out.println("Elevation (m):");
        double elevation = Double.parseDouble(read.nextLine());
        System.out.println("City:");
        String city = read.nextLine();
        System.out.println("Port Number:");
        int port = Integer.parseInt(read.nextLine());
        System.out.println("Zip Code:");
        String zip = read.nextLine();
        try{
        String add = controller.newAddress(street, longitude, latitude, elevation, city, port, zip);
        System.out.println("Address:");
        System.out.println(add);
        System.out.println("Do you want this to be your address? (y/n)");
        if(read.nextLine().equalsIgnoreCase("y")){
            newCreditCard();
        }else{
            newAddress();
        }
        }catch(IllegalArgumentException e){
            System.out.println();
            System.out.println("Error: "+e.getMessage());
            System.out.println();
            newAddress();
        }
        
    }

    private void newCreditCard(){
        System.out.println("2. Credit Card Information");
        System.out.println("Credit car number:");
        long nr = Long.parseLong(read.nextLine());
        System.out.println("Validaty Date (mm/yyyy): ");
        String date = read.nextLine();
        System.out.println("CCV: ");
        int ccv = Integer.parseInt(read.nextLine());
        try{
        String card = controller.newCreditCard(nr, date, ccv);
        System.out.println("Credit Card:");
        System.out.println(card);
        System.out.println("Is this your credit card? (y/n)");
        if(read.nextLine().equalsIgnoreCase("y")){
            newClient();
        }else{
            newCreditCard();
        }
        }catch(IllegalArgumentException e){
            System.out.println();
            System.out.println("Error: "+e.getMessage());
            System.out.println();
            newCreditCard();
        }
    }

    private void newClient(){
        System.out.println("3. Personal Information");
        System.out.println("Phone number:");
        int phoneNr = Integer.parseInt(read.nextLine());
        System.out.println("NIF:");
        int nif = Integer.parseInt(read.nextLine());
        System.out.println("Name (First and Last):");
        String name = read.nextLine();
        System.out.println("Email: ");
        String email=read.nextLine();
        System.out.println("Password:");
        String pwd = read.nextLine();
        try{
        String cli = controller.newClient(name, email, pwd, nif, phoneNr);
        System.out.println(cli);
        System.out.println("Do you confirm the information? (y/n)");
        if(read.nextLine().equalsIgnoreCase("y")){
           
            try {
                controller.registClient();
                System.out.println("Sucess: You been registed in the system.");
            } catch (SQLException ex) {
                System.out.println("The email already exists in the system. Login into yout acccout or try again.");
            }
            
        }else{
            newClient();
        }
        }catch(IllegalArgumentException e){
            System.out.println();
            System.out.println("Error: "+e.getMessage());
            System.out.println();
            newClient();
        }
    }
}
