/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui.text;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lapr.project.controller.AddPathController;
import static lapr.project.ui.text.Utils.executeScript;

/**
 *
 * @author Diogo
 */
public class TestScenarios {
    
    private static Scanner read = new Scanner(System.in);
    private static final String CLIENTS = "textFiles/Test_Scenarios/clients.csv";
    private static final String PHARMACIES = "textFiles/Test_Scenarios/pharmacies.csv";
    private static final String LAND_RESTRICTIONS = "textFiles/Test_Scenarios/landRestrictions.sql";
    private static final String AIR_RESTRICTIONS = "textFiles/Test_Scenarios/airRestrictions.sql";
    private static List<String> points;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        System.out.println("Clearing data...");
        Utils.executeScript("textFiles/clear.sql");
        System.out.println("Old data cleared.");
        points = new ArrayList<>();
        System.out.println("Inserting clients...");
        System.out.printf("%d clients were registed. %n", OurScenarios.insertClients(CLIENTS,points));
        System.out.println("Inserting pharmacies...");
        System.out.printf("%d pharmacies were registed. %n", OurScenarios.insertPharmacies(PHARMACIES));
        System.out.println("Creating land paths...");
        System.out.printf("%d land paths were created. %n", createPaths("Scooter"));
        System.out.println("Creating air paths...");
        System.out.printf("%d air paths were created. %n", createPaths("Drone"));
        
        
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
    
    private static int createPaths(String vehicle) throws SQLException{
        AddPathController controller = new AddPathController();
        double latitude1, longitude1, latitude2, longitude2;
        String line1[], line2[];
        for(int i = 0; i < points.size() -1 ;i++){
            line1 = points.get(i).split(";");
            latitude1 = Double.parseDouble(line1[0]);
            longitude1 = Double.parseDouble(line1[1]);
            for(int j = i+1; j < points.size();j++){
                line2 = points.get(j).split(";");
                latitude2 = Double.parseDouble(line2[0]);
                longitude2 = Double.parseDouble(line2[1]);
                controller.selectPoints(longitude1, latitude1, longitude2, latitude2, "Asphalt", 1, 1, 1, vehicle, null);
                controller.addToQueue(true);
                
            }
        }
        if(vehicle.equalsIgnoreCase("Scooter")){
            executeScript(LAND_RESTRICTIONS);
        }else{
            executeScript(AIR_RESTRICTIONS);
        }
        return controller.savePaths();
    }
    
}
