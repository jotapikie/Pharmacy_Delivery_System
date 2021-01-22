/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import lapr.project.controller.AddToCartController;
import lapr.project.data.CartProductDB;
import lapr.project.data.ClientDB;
import lapr.project.data.ProductDB;
import static lapr.project.ui.UtilsUI.header;

/**
 *
 * @author Diogo
 */
public class AddToCartUI {
    private AddToCartController controller;
    private static Scanner read = new Scanner(System.in);

    public AddToCartUI(String email){
        this.controller = new AddToCartController(email, new ProductDB(), new CartProductDB());
        try {
            header("x Add to Cart x");
            showAvailableProducts();
        } catch (SQLException ex) {
            System.out.println("Error: The products weren't added to your cart, try again later.");
        }
    }
    
    private void showAvailableProducts() throws SQLException{
        System.out.println();
        List<String> res = controller.getAvailableProducts();
        if(res.isEmpty()){
            System.out.println("Error: There are no products available in the moment.");
        }else{
            System.out.println("Available Products:");
            System.out.println();
            for(String p : res){
                System.out.println(p);
            }
            System.out.println();
            selectOne();
        }
    }
    
    private void selectOne() throws SQLException{
        System.out.println("Select one (id): ");
        System.out.println();
        try{
        int id = Integer.parseInt(read.nextLine());
        System.out.println();
        String res = controller.getSelectedProduct(id);
        if(res == null){
            System.out.println("Error: Invalid option, try again.");
            showAvailableProducts();
        }else{
            System.out.println("Quantity (un): ");
            System.out.println();
            int quantity = Integer.parseInt(read.nextLine());
            System.out.println();
            System.out.println(res);
            System.out.println("Do you want to add " +quantity+" unitys of this product to the cart? (y/n)");
            if(read.nextLine().equalsIgnoreCase("y")){
                controller.addToQueue(quantity);
                System.out.println();
                System.out.println("Product added to the queue.");
                System.out.println("Do you want to add more products? (y/n)");
                if(read.nextLine().equalsIgnoreCase("y")){
                    showAvailableProducts();
                }else{
                    System.out.println();
                    System.out.println("Processing...");
                    boolean added = controller.addToCart();
                    if(added){
                        System.out.println();
                        System.out.println("Sucess: The product(s) were added to your cart.");
                    }else{
                        System.out.println();
                        System.out.println("Error: The client is not registed in the system.");
                    }
                }
            }
        }
        }catch(NumberFormatException e){
            System.out.println();
            System.out.println("Error: Invalid option, try again.");
            showAvailableProducts();
        }
        
    }
    
    
}
