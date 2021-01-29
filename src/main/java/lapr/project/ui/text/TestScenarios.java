/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui.text;

import java.util.Scanner;
import static lapr.project.ui.text.Utils.clearAllData;

/**
 *
 * @author Diogo
 */
public class TestScenarios {
    
    private static Scanner read = new Scanner(System.in);
    private static final String CLIENTS = "textFiles/Test_Scenarios/clients.csv";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        clearAllData();
        System.out.println("Inserting clients...");
        System.out.printf("%d clients were registed. %n", OurScenarios.insertClients(CLIENTS));
        menu();
    }

    private static void menu() {
        System.out.println();
        System.out.println("Scenarios:");
        System.out.println();
        System.out.println("1. Scenario 02");
        System.out.println("2. Scenario 03");
        System.out.println("3. Scenario 04");
        System.out.println();
        String ans = read.nextLine();
        switch(ans){
            case "1":
                scenario02();
                break;
            case "2":
                scenario03();
                break;
            case "3":
                scenario04();
                break;
            default:
                System.out.println();
                System.out.println("Invalid option");
                menu();
        }
        
    }

    private static void scenario02() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void scenario03() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void scenario04() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
