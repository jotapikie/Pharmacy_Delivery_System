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
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.controller.AddPathController;
import lapr.project.data.GeographicalPointDB;
import lapr.project.model.AirGraph;
import lapr.project.model.GeographicalPoint;
import lapr.project.model.LandGraph;
import lapr.project.model.comparator.EnergyComparator;
import lapr.project.model.comparator.TimeComparator;
import static lapr.project.ui.text.Utils.deleteFile;
import static lapr.project.ui.text.Utils.executeScript;
import static lapr.project.ui.text.Utils.importFile;
import static lapr.project.ui.text.Utils.write;
import lapr.project.utils.Constants;
import lapr.project.utils.route.Route;

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
    private static final String RUNS = "textFiles/Test_Scenarios/Scenario04/runs.csv";
    private static final String OUTPUT = "textFiles/Test_Scenarios/Scenario04/output.txt";
    private static List<String> points;
    private static GeographicalPointDB database;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        database = new GeographicalPointDB();
        System.out.println("Clearing data...");
        Utils.executeScript("textFiles/clear.sql");
        System.out.println("Old data cleared.");
        points = new ArrayList<>();
        System.out.println("Inserting clients...");
        System.out.printf("%d clients were registed. %n", OurScenarios.insertClients(CLIENTS,points));
        System.out.println("Inserting pharmacies...");
        System.out.printf("%d pharmacies were registed. %n", OurScenarios.insertPharmacies(PHARMACIES,points));
        System.out.println("Creating land paths...");
        System.out.printf("%d land paths were created. %n", createPaths("Scooter"));
        System.out.println("Creating air paths...");
        System.out.printf("%d air paths were created. %n", createPaths("Drone"));
        System.out.println("Applying land restrictions...");
        executeScript(LAND_RESTRICTIONS);
        System.out.println("Applying air restrictions...");
        executeScript(AIR_RESTRICTIONS);
        System.out.println("Restrictions applied.");
        
        
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
                menu();
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
        try {
            deleteFile(OUTPUT);
            System.out.println("Getting points and paths from database...");
            LandGraph land = new LandGraph(125);
            AirGraph air = new AirGraph(35);
            System.out.println("Generating the results...");
            String line[];
            String[] latitudes;
            String[] longitudes;
            List<GeographicalPoint> pointsToStop = new ArrayList<>();
            int i;
            int index = 0;
            for(String s : importFile(RUNS)){
                pointsToStop.clear();
                index++;
                line = s.split(";");
                if(line[0].equalsIgnoreCase("-")){
                    checkPharmacies(land, air, line[2], line[3],index);
                }else{
                latitudes = line[2].split("!");
                longitudes = line[3].split("!");
                if(latitudes.length != longitudes.length){
                    throw new IllegalArgumentException("The file has an invalid format.");
                }
                
                GeographicalPoint pharmacy = database.getGeographicalPoint(Double.parseDouble(line[1]), Double.parseDouble(line[0]));
                
                i = 0;
                while(i < latitudes.length){
                    pointsToStop.add(database.getGeographicalPoint(Double.parseDouble(longitudes[i]),Double.parseDouble(latitudes[i])));
                    i++;
                }
                List<Route> lRoutes = land.kBestPaths(pointsToStop, pharmacy, pharmacy, 20 ,Constants.SCOOTER_MAX_BATTERY);
                List<Route> aRoutes = air.kBestPaths(pointsToStop, pharmacy, pharmacy, 20, Constants.DRONE_MAX_BATTERY);
                
                for(Route r : lRoutes){
                    System.out.println(r);
                    System.out.println();
                }
                List<Route> landTime = new ArrayList<>(lRoutes);
                landTime.sort(new TimeComparator());
                List<Route> airTime = new ArrayList<>(aRoutes);
                airTime.sort(new TimeComparator());
                
                List<Route> landEnergy = new ArrayList<>(lRoutes);
                landEnergy.sort(new EnergyComparator());
                List<Route> airEnergy = new ArrayList<>(aRoutes);
                airEnergy.sort(new EnergyComparator());
                
                write(String.format("###################################################### 4.1.%d ###################################################### %n", index), OUTPUT);
                Route lTime = landTime.get(0);
                Route aTime = airTime.get(0);
                Route lEff = landEnergy.get(0);
                Route aEff = airEnergy.get(0);
                
                write(String.format("Land Route (less time): %n%s %n", lTime.toString()),OUTPUT);
                write(String.format("Land Route (more efficient): %n%s %n", lEff.toString()),OUTPUT);
                write(String.format("Air Route (less time): %n%s %n", aTime.toString()),OUTPUT);
                write(String.format("Air Route (more efficient): %n%s %n %n", aEff.toString()),OUTPUT);
                }
            }
            System.out.println("Routes succesful exported to output file.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
        return controller.savePaths();
    }

    private static void checkPharmacies(LandGraph land, AirGraph air, String latitude, String longitude,int index) {
        try {
            GeographicalPoint point = database.getGeographicalPoint(Double.parseDouble(longitude), Double.parseDouble(latitude));
            List<GeographicalPoint> toVisit = new ArrayList<>();
            toVisit.add(point);
            write(String.format("###################################################### 4.1.%d ###################################################### %n", index), OUTPUT);
            for(GeographicalPoint p : land.getVertexes()){
                if(p.getDescription().contains("Pharmacy")){
                    List<Route> lands = land.kBestPaths(toVisit, p, p, 1, Constants.SCOOTER_MAX_BATTERY);
                    List<Route> airs = air.kBestPaths(toVisit, p, p, 1, Constants.DRONE_MAX_BATTERY);
                    if(lands != null && !lands.isEmpty()){
                        write(String.format("%n%s can deliver an order to %s by land. %n", p.getDescription(), point.getDescription()),OUTPUT);
                    }
                    
                    if(airs != null && !airs.isEmpty()){
                        write(String.format("%s can deliver an order to %s by air. %n", p.getDescription(), point.getDescription()),OUTPUT);
                    }
                    
                    
                }
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        index++;
    }
    
}
