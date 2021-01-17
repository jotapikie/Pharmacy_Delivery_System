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
import lapr.project.controller.PrepareOrderController;
import lapr.project.data.OrderDB;

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
            showAvailableOrders();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("An error ocurred when tried to communciate with the database");
        }
    }
    
    private void showAvailableOrders() throws SQLException{
        System.out.println("Orders to be prepaed:");
        for(String s: controller.getReadyToPrepareOrders()){
            System.out.println(s);
        }
        System.out.println("Select one or 0 to end");
        selectOne();
    }

    private void selectOne() throws SQLException {
        int id = Integer.parseInt(read.nextLine());
        String res = controller.getSelectedOrder(id);
        if(res == null){
            System.out.println("Invalid order selected. Try again");
            showAvailableOrders();
        }else{
            System.out.println("Order selected:");
            System.out.println(res);
            System.out.println("Do tou want to prepare this order? (y/n)");
            if(read.nextLine().equalsIgnoreCase("y")){
                controller.prepareOrder();
                System.out.println("Succes: This order is now being prepared.");
            }
        }
    }
    
    
    
}
