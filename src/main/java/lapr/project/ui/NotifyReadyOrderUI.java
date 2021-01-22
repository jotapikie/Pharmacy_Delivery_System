/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import lapr.project.controller.NotifyReadyOrderController;
import lapr.project.data.OrderDB;
import static lapr.project.ui.UtilsUI.header;

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
            header("x Notify prepared order x");
            showAvailableOrders();
        } catch (SQLException ex) {
            System.out.println("An error ocurred when tried to communciate with the database");
        }
    }
    
    private void showAvailableOrders() throws SQLException{
        List<String> orders = controller.getPreparingOrders();
        System.out.println();
        if(orders.isEmpty()){
            System.out.println("There are no orders being prepared in the moment.");
        }else{
            System.out.println("Order being prepared:");
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
        String res = controller.getSelectedOrder(id);
        if(res == null){
            System.out.println("Error: Invalid order selected, try again.");
            showAvailableOrders();
        }else{
            System.out.println(res);
            System.out.println();
            System.out.println("Do tou want to set this order as prepared? (y/n)");
            if(read.nextLine().equalsIgnoreCase("y")){
                System.out.println();
                System.out.println("Processing...");
                controller.setOrderToReady();
                System.out.println("Succes: This order is now set as prepared.");
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