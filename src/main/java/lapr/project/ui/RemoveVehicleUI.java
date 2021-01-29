/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import lapr.project.controller.RemoveVehicleController;
import lapr.project.data.VehicleDB;
import static lapr.project.ui.UtilsUI.header;

/**
 *
 * @author Helder
 */
public class RemoveVehicleUI {
    
    private static Scanner read = new Scanner(System.in);

    private RemoveVehicleController controller;
    
    public RemoveVehicleUI(int idPharmacy){
        controller=new RemoveVehicleController(idPharmacy);
        try {
            header("x Remove vehicle");
            showVehicles();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void showVehicles() throws SQLException {
       System.out.println();
       List<String> vehicles = controller.getVehicles();
       if(vehicles.isEmpty()){
           System.out.println("The pharmacy has no vehicle registed.");
       }else{
           System.out.println("All vehicles registed:");
           System.out.println();
           for(String s : vehicles){
               System.out.println(s);
           }
           selectOne();
       }
    }

    private void selectOne() throws SQLException {
        System.out.println();
        System.out.println("Select one (id):");
        try{
            int id = Integer.parseInt(read.nextLine());
            String choose = controller.selectVehicle(id);
            if(choose == null){
                System.out.println();
                System.out.println("Error: Invalid vehicle selected, try again.");
                selectOne();
            }else{
                System.out.println();
                System.out.println("Selected vehicle:");
                System.out.println();
                System.out.println(choose);
                System.out.println();
                System.out.println("Do you want to remove this vehicle? (y/n)");
                if(read.nextLine().equalsIgnoreCase("y")){
                    System.out.println();
                    System.out.println("Removing vehicle...");
                    boolean res = controller.remove();
                    System.out.println();
                    System.out.printf("%s %n",res?"Success: The vehicle was removed.":"Error: The vehicle was not removed.");
                }else{
                    System.out.println();
                    System.out.println("Operation aborted.");
                }
            }
        }catch(NumberFormatException e){
            System.out.println();
            System.out.println("Error: Invalid format data, try again.");
            selectOne();
        }
    }
    
    
}
