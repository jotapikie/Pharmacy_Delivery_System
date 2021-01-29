/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import lapr.project.controller.AssignOrderController;
import static lapr.project.ui.UtilsUI.header;

/**
 *
 * @author Diogo
 */
public class AssignOrderUI {
    private static Scanner read = new Scanner(System.in);
    
    private AssignOrderController controller;

    public AssignOrderUI(int idPharmacy) {
        controller = new AssignOrderController(idPharmacy);
        header("x Create delivery run x");
        try {
            showAvailableOrders();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void showAvailableOrders() throws SQLException{
        System.out.println();
        List<String> ord = controller.getAvailableOrders();
        if(ord.isEmpty()){
            System.out.println("In the moment there are no orders prepared.");
        }else{
            System.out.println("Available orders:");
            System.out.println();
            for(String s : ord){
                System.out.println(s);
            }
            selectOrder();
        }
        
    }

    private void selectOrder() throws SQLException {
        System.out.println();
        System.out.println("Select one (id)");
        try{
        int id = Integer.parseInt(read.nextLine());
        String res = controller.selectOrder(id);
        if(res == null){
            System.out.println();
            System.out.println("Error: Invalid order selected, try again.");
            selectOrder();
        }else{
            System.out.println();
            System.out.println(res);
            System.out.println();
            System.out.println("Do you want to add this order to the delivery? (y/n)");
            if(read.nextLine().equalsIgnoreCase("y")){
                boolean added = controller.addOrder();
                if(added){
                    System.out.println();
                    System.out.println("Order added. Do you want to add more orders? (y/n)");
                    if(read.nextLine().equalsIgnoreCase("y")){
                        showAvailableOrders();
                    }else{
                        newDelivery();
                    }
                }else{
                    System.out.println();
                    System.out.println("Error: This order can't be added, because it exceeds the weight limit of a delivery run.");
                    System.out.println();
                    System.out.println("Do you want to add other order? (y/n)");
                    if(read.nextLine().equalsIgnoreCase("y")){
                        showAvailableOrders();
                    }else{
                        newDelivery();
                    }
                }
            }
        }
        }catch(NumberFormatException e){
            System.out.println();
            System.out.println("Error: Invalid format data, try again.");
            selectOrder();
        }
    }

    private void newDelivery() throws SQLException {
        System.out.println();
        String land = controller.getLandRoute();
        String air = controller.getAirRoute();
        if(land == null){
            System.out.println("Couldn't find the land route for this delivery.");
        }else{
            System.out.println("Best land route:");
            System.out.println();
            System.out.println(land);
        }
        System.out.println();
        if(air == null){
            System.out.println("Couldn't find the air route for this delivery.");
        }else{
            System.out.println("Best air route:");
            System.out.println();
            System.out.println(air);
        }
        
        System.out.println();
        System.out.println("Plese select the vehicle you want to assign (Scooter or Drone)");
        String category = read.nextLine();
        System.out.println();
        String run = controller.newDeliveryRun(category);
        if(run == null){
            System.out.println("Error: Invalid category selected.");
            newDelivery();
        }else{
            System.out.println(run);
            System.out.println();
            System.out.println("Do you want to create this delivery run? (y/n)");
            if(read.nextLine().equalsIgnoreCase("y")){
                controller.addToQueue();
                System.out.println();
                System.out.println("Run added to thw queue.");
                System.out.println();
                System.out.println("Do you want to create more delivery runs? (y/n)");
                if(read.nextLine().equalsIgnoreCase("y")){
                    showAvailableOrders();
                }else{
                    int res = controller.saveDeliveryRuns();
                    System.out.println();
                    System.out.println("Sucess: " +res+ " delivery runs were created.");
                }
            }else{
                System.out.println();
                System.out.println("Operation aborted.");
            }
        }
    }
    
    
}
