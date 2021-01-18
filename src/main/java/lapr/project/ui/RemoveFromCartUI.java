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
            showCart();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("An error ocurred when tried to communciate with the database");
        }
    }
    
    private void showCart() throws SQLException{
        String res = controller.getProductsInCart();
        if(res == null){
            System.out.println("Your shopping cart is empty.");
        }else{
            System.out.println("Products in cart:");
            System.out.println(res);
            selectOne();
        }
    }

    private void selectOne() throws SQLException {
        System.out.println();
        System.out.println("Select the product you want to remove (0 to end).");
        int id = Integer.parseInt(read.nextLine());
        if(id != 0){
            String res = controller.getSelectedProduct(id);
            if(res == null){
                System.out.println("The selected product is invalid or doesn't exist in the cart. Please try again.");
                showCart();
            }else{
                System.out.println("Product selected:");
                System.out.println(res);
                System.out.println("Do you want to remove this product from the cart? (y/n)");
                if(read.nextLine().equalsIgnoreCase("y")){
                    controller.removeFromCart();
                    System.out.println("Sucess: The product was removed from your cart.");
                }
            }
        }
    }
    
    
     
    
}
