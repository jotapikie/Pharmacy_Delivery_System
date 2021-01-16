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
import lapr.project.controller.UpdateStockController;
import lapr.project.data.PharmacyStockDB;
import lapr.project.data.ProductDB;

/**
 *
 * @author Diogo
 */
public class UpdateStockUI {
    private UpdateStockController controller;
    private static Scanner read = new Scanner(System.in);

    public UpdateStockUI(int idPharmacy) {
        controller = new UpdateStockController(new ProductDB(), new PharmacyStockDB(),idPharmacy);
        try {
            showAvailableProducts();
        } catch (SQLException ex) {
            System.out.println("An error occurred when tried to communicate with the database.");
        }
    }
    
    private void showAvailableProducts() throws SQLException{
        System.out.println("Available Products:");
        for(String s : controller.getProducts()){
            System.out.println(s);
        }
        System.out.println("Select the desired product to add to the stock (id) or 0 to end");
        selectOne();
    }
    
    private void selectOne() throws SQLException{
        int id = Integer.parseInt(read.nextLine());
        if(id != 0){
            System.out.println();
            String res = controller.getSelectedProduct(id);
            if(res == null){
               System.out.println("Invalid product.");
                System.out.println();
                showAvailableProducts();
            }else{
                insertQuantity(res);
            }
        }
        controller.updateStock();
    }
    
    private void insertQuantity(String product) throws SQLException{
        System.out.println();
        System.out.println("Insert the quantity");
        int quantity = Integer.parseInt(read.nextLine());
        System.out.println("Product selected:");
        System.out.println(product);
        System.out.println("Do you want to add " +quantity+ " of this product? (y/n)");
        String ans = read.nextLine();
        if(ans.equalsIgnoreCase("y")){
            controller.addToQueue(quantity);
        }
        System.out.println();
        showAvailableProducts();
    }
    
    
}
