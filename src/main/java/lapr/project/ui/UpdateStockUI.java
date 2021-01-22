/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.controller.UpdateStockController;
import lapr.project.data.PharmacyStockDB;
import lapr.project.data.ProductDB;
import static lapr.project.ui.UtilsUI.header;

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
            header("x Update stock x");
            showAvailableProducts();
        } catch (SQLException ex) {
            System.out.println("An error occurred when tried to communicate with the database.");
        }
    }
    
    private void showAvailableProducts() throws SQLException{
        System.out.println();
        List<String> res = controller.getProducts();
        if(res.isEmpty()){
            System.out.println("Error: There are no products available in the moment.");
        }else{
            System.out.println("Available products:");
            System.out.println();
            for(String p : res){
                 System.out.println(p);
            }
            selectOne();
        }
    }
    
    private void selectOne() throws SQLException{
         System.out.println();
         int id = Integer.parseInt(read.nextLine());
         System.out.println();
         String res = controller.getSelectedProduct(id);
         if(res == null){
             System.out.println("Error: Invalid product, try again.");
             showAvailableProducts();
         }else{
              System.out.println("Quantity (unity):");
              int quantity = Integer.parseInt(read.nextLine());
              System.out.println();
              System.out.println(res);
              System.out.println();
              System.out.println("Do you want to add " +quantity+" unitys of this product to the stock? (y/n)");
              if(read.nextLine().equalsIgnoreCase("y")){
                   System.out.println();
                   controller.addToQueue(quantity);
                   System.out.println("Product added to the queue.");
                   System.out.println();
                   System.out.println("Do you want to add more products? (y/n)");
                   if(read.nextLine().equalsIgnoreCase("y")){
                       showAvailableProducts();
                   }else{
                       System.out.println();
                        System.out.println("Updating stock...");
                        System.out.println();
                        boolean updated = controller.updateStock();
                        if(updated){
                             System.out.println("Sucess: The pharmacy stock was updated.");
                        }else{
                            System.out.println("Error: The pharmacy with that id is not registed in the system.");
                        }
                       
                   }
              }else{
                  System.out.println();
                  System.out.println("Operation aborted");
              }
                        
 
              
         }
    }
    

    
    
}
