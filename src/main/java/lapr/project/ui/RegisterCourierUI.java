/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.util.Scanner;
import lapr.project.controller.RegisterCourierController;
import lapr.project.data.CourierDB;
import static lapr.project.ui.UtilsUI.header;

/**
 *
 * @author Helder
 */
class RegisterCourierUI {
    
    private RegisterCourierController controller;
    private static Scanner read = new Scanner(System.in);
    
    public RegisterCourierUI(int pharmacyID) {
        controller = new RegisterCourierController(new CourierDB(), pharmacyID);
        header("x Add courier x");
        try {
            newCourier();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("The email already exists.");
        }
 
    }
    private void newCourier() throws SQLException {
        try{
        System.out.println();
        System.out.println("Name (first and last):");
        String name = read.nextLine();
        System.out.println();
        System.out.println("Email:");
        String email = read.nextLine();
        System.out.println();
        System.out.println("Password:");
        String pwd = read.nextLine();
        System.out.println();
        System.out.println("Nif:");
        int nif = Integer.parseInt(read.nextLine());
        System.out.println();
        System.out.println("Nss:");
        int nss = Integer.parseInt(read.nextLine());
        System.out.println();
        System.out.println("Weight (kg):");
        double weight = Double.parseDouble(read.nextLine());
        System.out.println();
        try{
            String cou = controller.newCourier(name, email, pwd, nif, nss, weight);
            System.out.println(cou);
            System.out.println();
            System.out.println("Do you want to regist this courier? (y/n)");
            if(read.nextLine().equalsIgnoreCase("y")){
                System.out.println();
                controller.addToQueue();
                System.out.println("Courier added to the queue.");
                System.out.println();
                System.out.println("Do you want to regist other courier? (y/n)");
                if(read.nextLine().equalsIgnoreCase("y")){
                    newCourier();
                }else{
                    System.out.println();
                    System.out.println("Registing courier(s) ...");
                    int registed = controller.registCouriers();
                    System.out.println();
                    System.out.println("Sucess: " +registed+ " courier(s) were registed in the system.");
                }
            }else{
                System.out.println();
                System.out.println("Operation aborted");
            }
        }catch(IllegalArgumentException e){
            System.out.println("Error: " +e.getMessage());
            newCourier();
        }
        }catch(NumberFormatException e){
            System.out.println();
            System.out.println("Error: Invalid data format, try again.");
            newCourier();
        }
    }
    
}
