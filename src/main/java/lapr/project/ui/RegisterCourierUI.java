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

/**
 *
 * @author Helder
 */
class RegisterCourierUI {
    
    private RegisterCourierController controller;
    private static Scanner read = new Scanner(System.in);
    
    public RegisterCourierUI(int pharmacyID) throws SQLException {
        controller = new RegisterCourierController(new CourierDB(), pharmacyID);
        RegisterCourier();
        
        controller.newCourier("Francisco","diogo@gmail.com","1234", 454654,32425,200);
        controller.addToQueue();
        controller.registCouriers();
    }
    private void RegisterCourier() throws SQLException {
        System.out.println("Name:");
        String nome = read.nextLine();
        System.out.println("Email:");
        String email = read.nextLine();
        System.out.println("Password:");
        String pass = read.nextLine();
        System.out.println("NIF:");
        int nif = Integer.parseInt(read.nextLine());
        System.out.println("NSS:");
        int nss = Integer.parseInt(read.nextLine());
        System.out.println("Max Weight:");
        double maxWeight = Double.parseDouble(read.nextLine());
        
        String cou = controller.newCourier(nome,email,pass, nif,nss,maxWeight);
        System.out.println();
        System.out.println(cou);
        System.out.println("Do you really want to add Courier? (y/n)");
        if(read.nextLine().equalsIgnoreCase("y")){
            controller.addToQueue();
            System.out.println("Courier added to the queue.");
            System.out.println("Do you want to insert another Courier? (y/n)");
            if(read.nextLine().equalsIgnoreCase("y")){
                RegisterCourier();
            }else{
                int rs = controller.registCouriers();
                System.out.println("Sucess: " +rs+ " Courier(s) were added to the system.");
            }
        }
    }
    
}
