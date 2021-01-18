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
            newProduct();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("An error ocurred when tried to communciate with the database");
        }
    }
    
    private void newProduct() throws SQLException{
        System.out.println("Insert the required data about the new product.");
        System.out.println("Name: ");
        String name = read.nextLine();
        System.out.println("Weight (kg): ");
        double weig = Double.parseDouble(read.nextLine());
        System.out.println("Price (€): ");
        double price = Double.parseDouble((read.nextLine()));
        String res = controller.newProduct(name, weig, price);
        System.out.println();
        System.out.println(res);
        System.out.println("Do you really want to add this product? (y/n)");
        if(read.nextLine().equalsIgnoreCase("y")){
            controller.addToQueue();
            System.out.println("Product added to the queue.");
            System.out.println("Do you want to insert another product? (y/n)");
            if(read.nextLine().equalsIgnoreCase("y")){
                newProduct();
            }else{
                int rs = controller.registProduct();
                System.out.println("Sucess: " +rs+ " product(s) were added to the system.");
            }
        }
        
    }

}
