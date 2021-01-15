/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.util.Scanner;
import lapr.project.controller.AddToCartController;
import lapr.project.data.CartProductDB;
import lapr.project.data.ClientDB;
import lapr.project.data.ProductDB;

/**
 *
 * @author Diogo
 */
public class AddToCartUI {
    private AddToCartController controller;
    private static Scanner ler = new Scanner(System.in);

    public AddToCartUI(String email){
        this.controller = new AddToCartController(email, new ProductDB(), new ClientDB(), new CartProductDB());
        try {
            showAvailableProducts();
            selectOne();
            controller.addToCart();
            System.out.println("Operation successful");
        } catch (SQLException ex) {
            ex.printStackTrace();
            //System.out.println("An error occured when tried to communicate with the database.");
        }
    }
    
    private void showAvailableProducts() throws SQLException{
        System.out.println("Available Products:");
        for(String s : controller.getAvailableProducts()){
            System.out.println(s);
        }
        System.out.println();
    }
    
    private void selectOne() throws SQLException{
        System.out.println("Select the desired product (id) or 0 to end");
        int id = Integer.parseInt(ler.nextLine());
        if(id != 0){
        System.out.println("Choosen Product:");
        String res = controller.getSelectedProduct(id);
        if(res == null){
            System.out.println("Invalid Product");
        }else{
            System.out.println(res);
            System.out.println("Do you want to add this product to the cart? (y/n)");
            if(ler.nextLine().equalsIgnoreCase("y")){
                System.out.println("Insert the quantity desired");
                int quantity = Integer.parseInt(ler.nextLine());
                controller.addToQueue(quantity);
                System.out.println("Product added");
                selectOne();
            }
        }
        }
        
    }
    
    
}
