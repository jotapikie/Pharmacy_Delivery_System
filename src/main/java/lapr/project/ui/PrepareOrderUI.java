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
import lapr.project.controller.PrepareOrderController;
import lapr.project.data.OrderDB;
import static lapr.project.ui.UtilsUI.header;

/**
 *
 * @author Diogo
 */
public class PrepareOrderUI {
    private PrepareOrderController controller;
    private static Scanner read = new Scanner(System.in);

    public PrepareOrderUI(int idPharmacy) {
        controller = new PrepareOrderController(new OrderDB(), idPharmacy);
        try {
            header("x Prepare order x");
            showAvailableOrders();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void showAvailableOrders() throws SQLException{
        System.out.println();
        List<String> orders = controller.getReadyToPrepareOrders();
        if(orders.isEmpty()){
            System.out.println("There are no orders to prepare in the moment.");
        }else{
            System.out.println("Orders ready to prepare:");
            System.out.println();
            for(String order : orders){
                System.out.println(order);
            }
            selectOne();
        }
    }

    private void selectOne() throws SQLException {
        try{
        System.out.println();
        System.out.println("Select one (id):");
        int id = Integer.parseInt(read.nextLine());
        System.out.println();
        String ord = controller.getSelectedOrder(id);
        if(ord == null){
            System.out.println("Error: Invalid option selected, try again");
            showAvailableOrders();
        }else{
            System.out.println(ord);
            System.out.println();
            System.out.println("Do you want to prepare this order? (y/n)");
            if(read.nextLine().equalsIgnoreCase("y")){
                System.out.println();
                System.out.println("Processing...");
                controller.prepareOrder();
                System.out.println("Sucess: The order status was set to 'Preparing'");
            }else{
                System.out.println();
                System.out.println("Operation aborted");
            }
        }
        }catch(NumberFormatException e){
            System.out.println();
            System.out.println("Error: Invalid data format, try again.");
            showAvailableOrders();
        }
    }
    
    
    
}
