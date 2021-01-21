/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.util.Scanner;


/**
 *
 * @author Diogo
 */
public class TestUCs {

    static Scanner read = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        showOptions();
        
 
            
        
        
//        AddToCartUI addToCart = new AddToCartUI("clien1@lapr3.com"); // FUNCIONA
//        MakeOrderUI makeOrder = new MakeOrderUI("clien1@lapr3.com"); // FUNCIONA
//        StartDeliveryRunUI startDelivery = new StartDeliveryRunUI(1, 67.5, "courier1@lapr3.com"); // FUNCIONA
//        UpdateStockUI updateStock = new UpdateStockUI(1); // FUNCIONA
//        PrepareOrderUI prepareOrder = new PrepareOrderUI(1); // FUNCIONA
//        NotifyReadyOrderUI preparedOrder = new NotifyReadyOrderUI(1); //FUNCIONA
//        RegisterCourierUI registCourier  = new RegisterCourierUI(1);//FUNCIONA
//        RemoveVehicleUI removeVehicle =new RemoveVehicleUI(); // FUNCIONA
//        RegisterClientUI registClient = new RegisterClientUI(); // FUNCIONA
//        RegisterProductUI registProduct = new RegisterProductUI(); // FUNCIONA
//        AddGeographicalPointUI agp =new AddGeographicalPointUI(); //FUNCIONA
//        AddPathUI ap = new AddPathUI();
            


          

    }
    
    private static void showOptions(){
        System.out.println("######################################################");
        System.out.println();
        System.out.println("1. Login.");
        System.out.println("2. Regist as Client.");
        System.out.println();
        System.out.println("0. Exit");
        System.out.println();
        try{
            int option = Integer.parseInt(read.nextLine());
            switch(option){
                case 1:
                    doLogin();
                    showOptions();
                    break;
                case 2:
                    new RegisterClientUI();
                    showOptions();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Error: Invalid option, try again.");
                    System.out.println();
                    showOptions();
            }
        }catch(NumberFormatException e){
            System.out.println("Error: Invalid option, try again.");
            System.out.println();
            showOptions();
            
        }
    }

    private static void doLogin() {
        System.out.println();
        System.out.println("######################################################");
        System.out.printf("%35s%n", "x Simulate Login x");
        System.out.println("######################################################");
        System.out.println();
        System.out.println("Dou you wish to login as what?");
        System.out.println();
        System.out.println("1. (Super) Administrator");
        System.out.println("2. (Pharmacy) Administrator");
        System.out.println("3. Courier");
        System.out.println("4. Client");
        System.out.println();
        try{
            int option = Integer.parseInt(read.nextLine());
            switch(option){
                case 1:
                    asSuperAdministrator();
                    break;
                case 2: 
                    asPharmacyAdministrator();
                    break;
                case 3:
                    asCourier();
                    break;
                case 4:
                    asClient();
                    break;
                default:
                    System.out.println();
                    System.out.println("Error: Invalid option.");
            }
        }catch(NumberFormatException e){
            System.out.println();
            System.out.println("Error: Invalid option.");
        }
    }

    private static void asSuperAdministrator() {
        System.out.println();
        System.out.println("######################################################");
        System.out.printf("%35s%n", "x Menu Administrator x");
        System.out.println("######################################################");
        System.out.println();
        System.out.println("1. Add Pharmacy");
        System.out.println("2. Add Product");
        System.out.println("3. Add Geographical Point");
        System.out.println("4. Add Pathway");
        System.out.println();
        System.out.println("0. Logout");
        System.out.println();
        String ans = read.nextLine();
        switch(ans){
            case "1":
                System.out.println("Not supported yet.");
                asSuperAdministrator();
                break;
            case "2":
                new RegisterProductUI();
                asSuperAdministrator();
                break;
            case "3":
                new AddGeographicalPointUI();
                asSuperAdministrator();
                break;
            case "4":
                new AddPathUI();
                asSuperAdministrator();
                break;
            case "0":
                break;
            default:
                System.out.println();
                System.out.println("Error: Invalid option, try again");
                asSuperAdministrator();
        }
        
    }

    private static void asPharmacyAdministrator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void asCourier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void asClient() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
