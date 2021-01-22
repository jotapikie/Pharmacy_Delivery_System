/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.util.Scanner;
import static lapr.project.ui.UtilsUI.header;


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
      
    }
    
    private static void showOptions() throws SQLException{
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

    private static void doLogin() throws SQLException {
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
        header("x Menu Administrator x");
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
                new RegisterPharmacyUI();
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

    private static void asPharmacyAdministrator() throws SQLException {
        System.out.println();
        System.out.println("######################################################");
        System.out.printf("%35s%n", "x Menu Administrator x");
        System.out.println("######################################################");
        System.out.println();
        System.out.println("1. New vehicle");
        System.out.println("2. Remove vehicle");
        System.out.println("3. Update vehicle");
        System.out.println("4. Update stock");
        System.out.println("5. Notify Ready Order");
        System.out.println("6. Restrictions to park");
        System.out.println();
        System.out.println("0. Logout");
        System.out.println();
        String ans = read.nextLine();
        switch(ans){
            case "1":
                System.out.println("Not supported yet.");
                asPharmacyAdministrator();
                break;
            case "2":
                new RemoveVehicleUI();
                asPharmacyAdministrator();
                break;
            case "3":
                System.out.println("Not supported yet.");
                asPharmacyAdministrator();
                break;
            case "4":
                new UpdateStockUI(1);
                asPharmacyAdministrator();
                break;
            case "5":
                new NotifyReadyOrderUI(1);
                asPharmacyAdministrator();
                break;
            case "6":
                System.out.println("Not supported yet.");
                asPharmacyAdministrator();
                break;    
            case "0":
                break;
            default:
                System.out.println();
                System.out.println("Error: Invalid option, try again");
                asPharmacyAdministrator();
        }
    }

    private static void asCourier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void asClient() {
        header("x Login x");
        System.out.println();
        System.out.println("Email:");
        String email = read.nextLine();
        showMenu(email);
    }
    
    private static void showMenu(String email){
        header("x Menu Client x");
        System.out.println();
        System.out.println("1. Add to Cart");
        System.out.println("2. Remove from cart");
        System.out.println("3. Make order");
        System.out.println();
        System.out.println("0. Logout");
        System.out.println();
        String ans = read.nextLine();
        switch(ans){
            case "1":
                new AddToCartUI(email);
                showMenu(email);
                break;
            case "2":
                new RemoveFromCartUI(email);
                showMenu(email);
                break;
            case "3":
                new MakeOrderUI(email);
                showMenu(email);
                break;
            case "0":
                break;
            default:
                System.out.println();
                System.out.println("Error: Invalid oprion, try again.");
                showMenu(email);
        }
    }
}
