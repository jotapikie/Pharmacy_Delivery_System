/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.util.Scanner;
import lapr.project.controller.NotifyReadyOrderController;
import lapr.project.data.OrderDB;

/**
 *
 * @author Diogo
 */
public class NotifyReadyOrderUI {
    private NotifyReadyOrderController controller;
    private static Scanner read = new Scanner(System.in);

    public NotifyReadyOrderUI(int idPharmacy) {
        controller = new NotifyReadyOrderController(new OrderDB(), idPharmacy);
        try {
            showAvailableOrders();
        } catch (SQLException ex) {
            System.out.println("An error ocurred when tried to communciate with the database");
        }
    }
    
    private void showAvailableOrders() throws SQLException{
        System.out.println("Orders being prepared:");
        for(String s: controller.getPreparingOrders()){
            System.out.println(s);
        }
        System.out.println("Select one (0 to end)");
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
                controller.setOrderToReady();
                System.out.println("Succes: This order is now set as prepared.");
            }
        }
    }
}
