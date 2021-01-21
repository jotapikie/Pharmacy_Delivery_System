/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.util.Scanner;
import lapr.project.controller.RegisterProductController;
import lapr.project.data.ProductDB;

/**
 *
 * @author Diogo
 */
public class RegisterProductUI {
    private RegisterProductController controller;
    private static Scanner read = new Scanner(System.in);

    public RegisterProductUI() {
        controller = new RegisterProductController(new ProductDB());
        try {
            header();
            newProduct();
        } catch (SQLException ex) {
            System.out.println("Error: Database error.");
        }
    }
    
    private void newProduct() throws SQLException{
        try{
        System.out.println();
        System.out.println("Name: ");
        String name = read.nextLine();
        System.out.println();
        System.out.println("Weight (kg): ");
        double weig = Double.parseDouble(read.nextLine());
        System.out.println();
        System.out.println("Price (€): ");
        double price = Double.parseDouble((read.nextLine()));
        System.out.println();
        String res = controller.newProduct(name, weig, price);
        System.out.println(res);
        System.out.println();
        System.out.println("Do you really want to add this product? (y/n)");
        System.out.println();
        if(read.nextLine().equalsIgnoreCase("y")){
            controller.addToQueue();
            System.out.println();
            System.out.println("Product added to the queue.");
            System.out.println("Do you want to insert another product? (y/n)");
            System.out.println();
            if(read.nextLine().equalsIgnoreCase("y")){
                newProduct();
            }else{
                System.out.println();
                System.out.println("Processing...");
                int rs = controller.registProduct();
                System.out.println("Sucess: " +rs+ " product(s) were added to the system.");
            }
        }
        }catch(NumberFormatException e){
           System.out.println();
           System.out.println("Error: Invalid format, try again.");
           newProduct();
        }
        
    }

    private void header() {
        System.out.println();
        System.out.println("######################################################");
        System.out.printf("%35s%n", "x Add Product x");
        System.out.println("######################################################");
    }

}
