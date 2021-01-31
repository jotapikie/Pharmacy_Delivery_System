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
import lapr.project.model.*;
import lapr.project.model.comparator.EnergyComparator;
import lapr.project.model.comparator.TimeComparator;
import static lapr.project.ui.text.Utils.deleteFile;
import static lapr.project.ui.text.Utils.executeScript;
import static lapr.project.ui.text.Utils.importFile;
import static lapr.project.ui.text.Utils.variablesUsed;
import static lapr.project.ui.text.Utils.write;
import lapr.project.utils.Constants;
import static lapr.project.utils.Utils.battery;
import static lapr.project.utils.Utils.distance;
import static lapr.project.utils.Utils.kmhTOms;
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
    private static String RUNS = "textFiles/Test_Scenarios/Scenario04/runs.csv";
    private static String OUTPUT = "textFiles/Test_Scenarios/Scenario04/output.txt";
    private static String VARIABLES = "textFiles/Test_Scenarios/Scenario04/variables.txt";
    private static List<String> points;
    private static GeographicalPointDB database;
    private static LandGraph landGraph;
    private static AirGraph airGraph;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, CloneNotSupportedException {
        database = new GeographicalPointDB();
//        System.out.println("Clearing data...");
//        Utils.executeScript("textFiles/clear.sql");
//        System.out.println("Old data cleared.");
//        points = new ArrayList<>();
//        System.out.println("Inserting clients...");
//        System.out.printf("%d clients were registed. %n", OurScenarios.insertClients(CLIENTS,points));
//        System.out.println("Inserting pharmacies...");
//        System.out.printf("%d pharmacies were registed. %n", OurScenarios.insertPharmacies(PHARMACIES,points));
//        System.out.println("Creating land paths...");
//        System.out.printf("%d land paths were created. %n", createPaths("Scooter"));
//        System.out.println("Creating air paths...");
//        System.out.printf("%d air paths were created. %n", createPaths("Drone"));
//        System.out.println("Applying land restrictions...");
//        executeScript(LAND_RESTRICTIONS);
//        System.out.println("Applying air restrictions...");
//        executeScript(AIR_RESTRICTIONS);
//        System.out.println("Restrictions applied.");
        System.out.println("Creating graphs...");
        landGraph = new LandGraph(Constants.SCOOTER_WEIGHT + Constants.AVERAGE_COURIER_WEIGHT);
        airGraph = new AirGraph(Constants.DRONE_WEIGHT);
        menu();
        
    }

    private static void menu() throws CloneNotSupportedException {
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
                menu();
                break;
            case "2":
                scenario03();
                menu();
                break;
            case "3":
                scenario04();
                variablesUsed(VARIABLES);
                menu();
                break;
            default:
                System.out.println();
                System.out.println("Invalid option");
                menu();
        }
        
    }

    private static void scenario02() throws CloneNotSupportedException {
        OUTPUT = "textFiles/Test_Scenarios/Scenario02/output.txt";
        VARIABLES = "textFiles/Test_Scenarios/Scenario02/variables.txt";
        RUNS = "textFiles/Test_Scenarios/Scenario02/runs.csv";
        try{
           deleteFile(OUTPUT);
           deleteFile(VARIABLES);
           String line[];
           LandGraph graph;
           String latitudes[];
           String longitudes[];
           String elevations[];
           int i;
           List<GeographicalPoint> pois = new ArrayList<>();
           System.out.println("Generating result...");
           for(String run : importFile(RUNS)){
               graph =  landGraph.clone();
               pois.clear();
               line = run.split(";");
               elevations = line[6].split("!");
               GeographicalPoint origin = database.getGeographicalPoint(Double.parseDouble(line[3]), Double.parseDouble(line[2]));
               latitudes = line[4].split("!");
               longitudes = line[5].split("!");
               i = 0;
               while(i < latitudes.length){
                   pois.add(database.getGeographicalPoint(Double.parseDouble(longitudes[i]), Double.parseDouble(latitudes[i])));
                   i++;
               }
               int index = Integer.parseInt(line[0]);
               write(String.format("###################################################### 2.1.%d ###################################################### %n %n", index), OUTPUT);
               write(String.format("###################################################### 2.1.%d ###################################################### %n %n", index), VARIABLES);
               boolean charge = line[11].equalsIgnoreCase("y");
               double kinetic = Double.parseDouble(line[7]);
               double maxBat = Double.parseDouble(line[9]);
               double currentBat = battery(Double.parseDouble(line[8]), maxBat);
               double additionalWeight = Double.parseDouble(line[10]);
               double vx = kmhTOms(Double.parseDouble(line[12]));
               double vy = kmhTOms(Double.parseDouble(line[13]));
               double vz = kmhTOms(Double.parseDouble(line[14]));
               Wind wind = new Wind(vx, vy, vz);
               
               // FINDING BEST ROUTES WITHOUT CHANGING PARAMTERS
               List<Route> routes = new ArrayList<>();
               routes = graph.kBestPaths(pois, origin, origin, 1, 10);
               Route route = null;
               if(routes != null && !routes.isEmpty()){
                   route = routes.get(0);
               }
               
               if(route == null){
                   write(String.format("There's no path between the points given. %n"),OUTPUT);
               }else{
                   changeData(route, additionalWeight, kinetic, charge, pois, elevations, origin, currentBat, maxBat, wind);
               }
               
               
               
               
         }
           System.out.println("Results exported to output file.");
           
        } catch (SQLException ex) {
            Logger.getLogger(TestScenarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private static void scenario03() {
        OUTPUT = "textFiles/Test_Scenarios/Scenario03/output.txt";
        VARIABLES = "textFiles/Test_Scenarios/Scenario03/variables.txt";
        RUNS = "textFiles/Test_Scenarios/Scenario03/runs.csv";
        try{
            deleteFile(OUTPUT);
            deleteFile(VARIABLES);
            String line[];
            AirGraph graph;
            String latitudes[];
            String longitudes[];
            String elevations[];
            int i;
            List<GeographicalPoint> pois = new ArrayList<>();
            System.out.println("Generating result...");
            for(String run : importFile(RUNS)){
                graph =  airGraph.clone();
                pois.clear();
                line = run.split(";");
                elevations = line[6].split("!");
                GeographicalPoint origin = database.getGeographicalPoint(Double.parseDouble(line[3]), Double.parseDouble(line[2]));
                latitudes = line[4].split("!");
                longitudes = line[5].split("!");
                i = 0;
                while(i < latitudes.length){
                    pois.add(database.getGeographicalPoint(Double.parseDouble(longitudes[i]), Double.parseDouble(latitudes[i])));
                    i++;
                }
                int index = Integer.parseInt(line[0]);
                write(String.format("###################################################### 3.2.%d ###################################################### %n %n", index), OUTPUT);
                write(String.format("###################################################### 3.2.%d ###################################################### %n %n", index), VARIABLES);
                boolean charge = line[10].equalsIgnoreCase("y");
                double maxBat = Double.parseDouble(line[8]);
                double currentBat = battery(Double.parseDouble(line[7]), maxBat);
                double additionalWeight = Double.parseDouble(line[9]);
                double vx = Double.parseDouble(line[11]);
                double vy = Double.parseDouble(line[12]);
                double vz = Double.parseDouble(line[13]);
                Wind wind = new Wind(vx, vy, vz);

                // FINDING BEST ROUTES WITHOUT CHANGING PARAMTERS
                List<Route> routes = new ArrayList<>();
                routes = graph.kBestPaths(pois, origin, origin, 1, maxBat);
                Route route = null;
                if(routes != null && !routes.isEmpty()){
                    route = routes.get(0);
                }

                if(route == null){
                    write(String.format("There's no path between the points given. %n"),OUTPUT);
                }else{
                    changeDataDrone(route,additionalWeight, charge, pois, elevations, origin, currentBat, maxBat, wind);
                }




            }
            System.out.println("Results exported to output file.");

        } catch (SQLException | CloneNotSupportedException ex) {
            Logger.getLogger(TestScenarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void scenario04() {
        OUTPUT = "textFiles/Test_Scenarios/Scenario04/output.txt";
        VARIABLES = "textFiles/Test_Scenarios/Scenario04/variables.txt";
        RUNS = "textFiles/Test_Scenarios/Scenario04/runs.csv";
        try {
            deleteFile(OUTPUT);
            deleteFile(VARIABLES);
            LandGraph land = landGraph;
            AirGraph air = airGraph;
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
                
                List<Route> landTime = new ArrayList<>(lRoutes);
                landTime.sort(new TimeComparator());
                List<Route> airTime = new ArrayList<>(aRoutes);
                airTime.sort(new TimeComparator());
                
                List<Route> landEnergy = new ArrayList<>(lRoutes);
                landEnergy.sort(new EnergyComparator());
                List<Route> airEnergy = new ArrayList<>(aRoutes);
                airEnergy.sort(new EnergyComparator());
                
                write(String.format("###################################################### 4.1.%d ###################################################### %n %n", index), OUTPUT);
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
            write(String.format("###################################################### 4.1.%d ###################################################### %n %n", index), OUTPUT);
            for(GeographicalPoint p : land.getVertexes()){
                if(p.getDescription().contains("Pharmacy")){
                    List<Route> lands = land.kBestPaths(toVisit, p, p, 1, Constants.SCOOTER_MAX_BATTERY);
                    List<Route> airs = air.kBestPaths(toVisit, p, p, 1, Constants.DRONE_MAX_BATTERY);
                    if(lands != null && !lands.isEmpty()){
                        write(String.format("%s can deliver an order to %s by land. %n", p.getDescription(), point.getDescription()),OUTPUT);
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

    private static void changeData(Route route, double additionalWeight, double kinetic, boolean charge, List<GeographicalPoint> numPoints, String elevations[], GeographicalPoint origin, double currentBat, double maxBat, Wind wind) throws CloneNotSupportedException {
      
        int i = 0;
        double totalEnergy = 0;
        double totalDistance = 0;
        double elevation = 0;
        write(String.format("Kinetic Coef: %.2f%n", kinetic),VARIABLES);
        write(String.format("Total Weight: %.2f%n",((ScooterPath) route.getPaths().get(0)).getTotalWeight() + additionalWeight),VARIABLES);
        write(String.format("Point 0 Elevation: %.2f m %n", origin.getElevation()+ (elevation)),VARIABLES);
        for(Pathway p: route.getPaths()){
            ScooterPath sc = (ScooterPath) p;
            sc.setKineticCoef(kinetic);
            
            sc.setTotalWeight(sc.getTotalWeight() + additionalWeight);
            
            if(numPoints.contains(p.getDestinationPoint()) && i <= numPoints.size()){
                elevation = manageElevation(origin.getElevation(), Double.parseDouble(elevations[i]));
                i++;
                write(String.format("Point %d Elevation: %.2f m %n", i, origin.getElevation()+ (elevation)),VARIABLES);
                for(GeographicalPoint p1 : numPoints){
                    if(p1.equals(p.getDestinationPoint())){
                        p1.setElevation(origin.getElevation()+ (elevation));
                    }
                }
                p.getDestinationPoint().setElevation(origin.getElevation() + (elevation));
            }
            
            if(numPoints.contains(p.getOriginPoint()) && i <= numPoints.size()){
                for(GeographicalPoint p1 : numPoints){
                    if(p1.equals(p.getOriginPoint())){
                        p1.setElevation(origin.getElevation() + (elevation));
                    }
                }
                p.getOriginPoint().setElevation(origin.getElevation() + (elevation));
            }
            
            p.setWind(wind);
            p.setDistance(distance(p.getOriginPoint().getLatitude(), p.getDestinationPoint().getLatitude(), p.getOriginPoint().getLongitude(), p.getDestinationPoint().getLongitude(), p.getOriginPoint().getElevation(), p.getDestinationPoint().getElevation()));
            totalEnergy = totalEnergy + p.getCost();
            totalDistance = totalDistance + p.getDistance();
        }
            write(String.format("Wind Direction: %dº %n", wind.direction()),VARIABLES);
            write(String.format("Wind Speed: %.2f m/s %n", wind.speed()),VARIABLES);
            variablesUsed(VARIABLES);
        
        route.updateValues();
        write(String.format("Origin/Destination: %s %n", origin),OUTPUT);
        for(GeographicalPoint p : numPoints){
            write(String.format("Order: %s %n", p), OUTPUT);
        }
        
        if(!charge){
            if(currentBat < totalEnergy){
                write(String.format("%nThe route needs %.2f kWh and the scooter has only %.2f kwh,(cannot charge) so that the order can't be delivered.(Distance: %.2f m) %n %n", totalEnergy, currentBat, totalDistance),OUTPUT);
            }else{
                write(String.format("%nThe route needs %.2f kWh and the scooter has %.2f kWh so that the order can be delivered.(Distance: %.2f m) %n %n", totalEnergy, currentBat,totalDistance),OUTPUT);
            }
        }else{
            if(route.getMinimumEnergy() < maxBat){
                write(String.format("%nThe route needs %.2f kWh and the scooter has %.2f kWh, however it can stop to charge, so that the order can be delivered.(Distance: %.2f m) %n %n", totalEnergy, currentBat,totalDistance), OUTPUT);
            }else{
                write(String.format(("%nThe route needs %.2f kWh and the scooter has only %.2f, even if it can charge somewhere, the order can't be delivered.(Distance: %.2f m) %n %n"), totalEnergy,currentBat,totalDistance),OUTPUT);
              }
            
        }
    }

    private static void changeDataDrone(Route route,double additionalWeight, boolean charge, List<GeographicalPoint> numPoints, String elevations[], GeographicalPoint origin, double currentBat, double maxBat, Wind wind) throws CloneNotSupportedException {

        int i = 0;
        double totalEnergy = 0;
        double totalDistance = 0;
        double elevation = 0;
        write(String.format("Total Weight: %.2f%n",((DronePath) route.getPaths().get(0)).getTotalWeight()+additionalWeight),VARIABLES);
        write(String.format("Point 0 Elevation: %.2f m %n", origin.getElevation()+ (elevation)),VARIABLES);
        for(Pathway p: route.getPaths()){
            DronePath dc = (DronePath) p;
            dc.setTotalWeight(dc.getTotalWeight());

            if(numPoints.contains(p.getDestinationPoint()) && i <= numPoints.size()){
                elevation = Double.parseDouble(elevations[i]);
                i++;
                write(String.format("Point %d Elevation: %.2f m %n", i,  (elevation)),VARIABLES);
                for(GeographicalPoint p1 : numPoints){
                    if(p1.equals(p.getDestinationPoint())){
                        p1.setElevation(elevation);
                    }
                }
                p.getDestinationPoint().setElevation( (elevation));
            }

            if(numPoints.contains(p.getOriginPoint()) && i <= numPoints.size()){
                for(GeographicalPoint p1 : numPoints){
                    if(p1.equals(p.getOriginPoint())){
                        p1.setElevation( (elevation));
                    }
                }
                p.getOriginPoint().setElevation( (elevation));
            }

            p.setWind(wind);
            p.setDistance(distance(p.getOriginPoint().getLatitude(), p.getDestinationPoint().getLatitude(), p.getOriginPoint().getLongitude(), p.getDestinationPoint().getLongitude(), p.getOriginPoint().getElevation(), p.getDestinationPoint().getElevation()));
            totalEnergy = totalEnergy + p.getCost();
            totalDistance = totalDistance + p.getDistance();
        }
        write(String.format("Wind Direction: %dº %n", wind.direction()),VARIABLES);
        write(String.format("Wind Speed: %.2f m/s %n", wind.speed()),VARIABLES);
        variablesUsed(VARIABLES);

        route.updateValues();
        write(String.format("Origin/Destination: %s %n", origin),OUTPUT);
        for(GeographicalPoint p : numPoints){
            write(String.format("Order: %s %n", p), OUTPUT);
        }

        if(!charge){
            if(currentBat < totalEnergy){
                write(String.format("%nThe route needs %.2f mAh and the drone has only %.2f mAh,(cannot charge) so that the order can't be delivered.(Distance: %.2f m) %n %n", totalEnergy, currentBat, totalDistance),OUTPUT);
            }else{
                write(String.format("%nThe route needs %.2f mAh and the drone has %.2f mAh so that the order can be delivered.(Distance: %.2f m) %n %n", totalEnergy, currentBat,totalDistance),OUTPUT);
            }
        }else{
            if(route.getMinimumEnergy() < maxBat){
                write(String.format("%nThe route needs %.2f mAh and the drone has %.2f mAh, however it can stop to charge, so that the order can be delivered.(Distance: %.2f m) %n %n", totalEnergy, currentBat,totalDistance), OUTPUT);
            }else{
                write(String.format(("%nThe route needs %.2f mAh and the drone has only %.2f mAh, even if it can charge somewhere, the order can't be delivered.(Distance: %.2f m) %n %n"), totalEnergy,currentBat,totalDistance),OUTPUT);
            }

        }
    }

    private static double manageElevation(double elevation0, double elevation) {
        double elevationTotal = (elevation == 0) ? 0 : elevation0*(elevation/100);
        return elevationTotal;
    }


    
    
    

    
}
