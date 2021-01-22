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
import lapr.project.controller.MakeOrderController;
import lapr.project.data.CartProductDB;
import lapr.project.data.ClientDB;
import lapr.project.data.InvoiceDB;
import lapr.project.data.OrderDB;
import lapr.project.data.PharmacyDB;
import lapr.project.data.PharmacyStockDB;
import static lapr.project.ui.UtilsUI.header;

/**
 *
 * @author Diogo
 */
public class MakeOrderUI {
    private MakeOrderController controller;
    private static Scanner read = new Scanner(System.in);

    public MakeOrderUI(String email) {
        try {
            controller = new MakeOrderController(new ClientDB(), new CartProductDB(), new OrderDB(), new InvoiceDB(), new PharmacyStockDB(), new PharmacyDB(), email);
            header("x Make Order x");
            showCart();
        } catch (SQLException ex) {
            System.out.println("Error: The client with email " +email+" is not registed in the system.");
        }
        
    }
    
    private void showCart() throws SQLException{
        System.out.println();
        String cart = controller.getCart();
        if(cart == null){
            System.out.println("Error: You can't make an order with the products you have in cart.");
        }else{
            System.out.println("Products in the cart:");
            System.out.println();
            System.out.println(cart);
            System.out.println();
            System.out.println("Do you confirm? (y/n)");
            if(read.nextLine().equalsIgnoreCase("y")){
                showAddress();
            }
        }
    }
    
    private void showAddress() throws SQLException{
        System.out.println();
        System.out.println("Address (to where it will be delivered):");
        System.out.println(controller.getAddress());
        System.out.println();
        System.out.println("Do you confirm? (y/n)");
        if(read.nextLine().equalsIgnoreCase("y")){
            showNif();
        }
    }
    
    private void showNif() throws SQLException{
        System.out.println();
        int nif = controller.getDefaultNif();
        System.out.println("Default Nif: " +nif);
        System.out.println();
        System.out.println("Do you want to use this nif for the order? (y/n)");
        if(read.nextLine().equalsIgnoreCase("y")){
            showCredits(nif);
        }else{
            System.out.println();
            System.out.println("Insert the nif:");
            try{
            nif = Integer.parseInt(read.nextLine());
            if(String.valueOf(nif).length()!= 9){
                System.out.println();
                System.out.println("Error: The nif should have 9 digits, try again.");
                showNif();
            }else{
                showCredits(nif);
            }
            }catch(NumberFormatException e){
                System.out.println();
                System.out.println("Error: Invalid nif, try again.");
                showNif();
            }
        }
        
    }
    
    private void showCredits(int nif) throws SQLException{
        System.out.println();
        int credits = controller.getCredits();
        System.out.println("Credits: " +credits);
        System.out.println();
        System.out.println("Do you want to use them to get a discount? (y/n)");
        if(read.nextLine().equalsIgnoreCase("y")){
            controller.discount();
            System.out.println("Discount made");
        }
        showFinalPrice(nif);
    }
    
    private void showFinalPrice(int nif) throws SQLException{
        System.out.println();
        double price = controller.getFinalPrice();
        System.out.println("Final Price: "+price+"€");
        System.out.println();
        System.out.println("Do you want to make the order? (y/n)");
        if(read.nextLine().equalsIgnoreCase("y")){
            makeOrder(nif);
        }
    }
    
    private void makeOrder(int nif) throws SQLException{
        System.out.println();
        System.out.println("Making Order...");
        boolean res = controller.makeOrder(nif);
        if(res){
            System.out.println("Success: Your order is made, check your email for more details.");
        }else{
            System.out.println("Error: The order couldn't be made. (No pharmacy has stock for that order)");
        }
    }
    
    
}
