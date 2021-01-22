/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.util.Scanner;
import lapr.project.controller.RemoveFromCartController;
import lapr.project.data.CartProductDB;
import lapr.project.data.ProductDB;
import static lapr.project.ui.UtilsUI.header;

/**
 *
 * @author Diogo
 */
public class RemoveFromCartUI {
    private RemoveFromCartController controller;
    private static Scanner read = new Scanner(System.in);

    public RemoveFromCartUI(String email) {
        controller = new RemoveFromCartController(email, new CartProductDB(), new ProductDB());
        try {
            header("x Remove from cart x");
            showCart();
        } catch (SQLException ex) {
            System.out.println("Error: The client with email " +email+" is not registed in the system.");
        }
    }
    
    private void showCart() throws SQLException{
        System.out.println();
        String res = controller.getProductsInCart();
        if(res == null){
            System.out.println("Your shopping cart is empty.");
        }else{
            System.out.println("Products in cart:");
            System.out.println();
            System.out.println(res);
            selectOne();
        }
    }

    private void selectOne() throws SQLException {
        System.out.println();
        System.out.println("Select the product (id):");
        int id = Integer.parseInt(read.nextLine());
        System.out.println();
        String res = controller.getSelectedProduct(id);
        if(res == null){
                System.out.println("Error: Invalid product,try again.");
                showCart();
        }else{
                System.out.println("Product selected:");
                System.out.println();
                System.out.println(res);
                System.out.println();
                System.out.println("Do you want to remove this product from the cart? (y/n)");
                if(read.nextLine().equalsIgnoreCase("y")){
                    System.out.println();
                    System.out.println("Processing...");
                    boolean removed = controller.removeFromCart();
                    System.out.println();
                    if(removed){  
                        System.out.println("Sucess: The product was removed from your cart.");
                    }else{
                        System.out.println("Error: The product wasn't removed form your cart.");
                    }
                }
            }
        
    }
    
    
     
    
}
