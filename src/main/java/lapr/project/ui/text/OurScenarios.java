/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui.text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lapr.project.controller.AddPathController;
import lapr.project.controller.AddToCartController;
import lapr.project.controller.AssignOrderController;
import lapr.project.controller.MakeOrderController;
import lapr.project.controller.NewVehicleController;
import lapr.project.controller.NotifyReadyOrderController;
import lapr.project.controller.PrepareOrderController;
import lapr.project.controller.RegisterClientController;
import lapr.project.controller.RegisterCourierController;
import lapr.project.controller.RegisterPharmacyController;
import lapr.project.controller.RegisterProductController;
import lapr.project.controller.StartDeliveryRunController;
import lapr.project.controller.UpdateStockController;
import static lapr.project.ui.text.Utils.deleteFile;
import static lapr.project.ui.text.Utils.write;
import static lapr.project.ui.text.Utils.importFile;
import static lapr.project.ui.text.Utils.executeScript;
import static lapr.project.ui.text.Utils.variablesUsed;


/**
 *
 * @author Diogo
 */
public class OurScenarios {
    private static Scanner read = new Scanner(System.in);

    
    private static final String CLIENTS = "textFiles/clients.csv";
    private static final String PHARMACIES = "textFiles/pharmacies.csv";
    private static final String PRODUCTS = "textFiles/products.csv";
    private static final String COURIERS = "textFiles/couriers.csv";
    private static final String VEHICLES = "textFiles/vehicles.csv";

    private static String CARTS;
    private static String ORDERS;
    private static String PATHS;
    private static String STOCK;
    private static String PREPARED_ORDERS;
    private static String RUNS;
    private static String DELIVERIES;
    
    private static String RESULT;
    private static String VARIABLES;
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

 
 
        System.out.println("Clearing data...");
        executeScript("textFiles/clear.sql");
        System.out.println("Old data cleared.");
        System.out.println("Adding products...");
        System.out.printf("%d products were added. %n", insertProducts());
        System.out.println("Adding pharmacies...");
        System.out.printf("%d pharmacies were added. %n", insertPharmacies(PHARMACIES,null));
        System.out.println("Adding clients...");
        System.out.printf("%d clients were added. %n", insertClients(CLIENTS,null));
        System.out.println("Adding couriers...");
        System.out.printf("%d couriers were added. %n", insertCouriers());
        System.out.println("Adding vehicles...");
        System.out.printf("%d vehicles were added. %n", insertVehicles());
        menu();
    }

    private static void menu() {
        System.out.println("Scenarios:");
        System.out.println();
        System.out.println("1. Scenario 01 (Pharmacy - Order - Pharmacy)");
        System.out.println("2. Scenario 02 (Pharmacy - ... - Order - ... - Pharmacy");
        System.out.println("3. Scenario 03 (Pharmacy [No stock] - Other Pharmacy - Pharmacy)");
        System.out.println("4. Scenario 04 (Pharmacy - ... - 2 Orders - ... - Pharmacy)");
        System.out.println("5. Scenario 05 (Pharmacy - ... - Order - ... - Charge - Pharmacy");
        System.out.println();
        String ans = read.nextLine();
        switch(ans){
            case "1":
                scenario01();
                break;
            case "2":
                scenario02();
                break;
            case "3":
                scenario03();
                break;
            case "4":
                scenario04();
                break;
            case "5":
                scenario05();
                break;
            default:
                System.out.println();
                System.out.println("Invalid option");
                menu();
        }
        
        
        System.out.println("Adding paths...");
        System.out.printf("%d paths were added. %n", insertPaths());
        System.out.println("Adding products to carts...");
        System.out.printf("%d carts were updated. %n", updateCarts());
        System.out.println("Adding products to pharmacies stocks...");
        System.out.printf("%d products were added to pharmacies stock. %n", updateStock());
        System.out.println("Making orders...");
        System.out.printf("%d orders were made. %n", makeOrders());
        System.out.println("Preparing orders...");
        System.out.printf("%d orders were prepared. %n", prepareOrders());
        System.out.println("Creating delivery runs...");
        System.out.printf("%d delivery runs were created. %n", assignOrders());
        System.out.println("Starting delivery runs...");
        System.out.printf("%d delivery runs were started. %n", startRuns());
        variablesUsed(VARIABLES);
 
        

        
                
    }

    private static void scenario01() {
        CARTS = "textFiles/Scenario01/carts.csv";
        ORDERS = "textFiles/Scenario01/orders.csv";
        PATHS = "textFiles/Scenario01/paths.csv";
        STOCK = "textFiles/Scenario01/stock.csv";
        PREPARED_ORDERS = "textFiles/Scenario01/prep_orders.csv";
        RUNS = "textFiles/Scenario01/runs.csv";
        DELIVERIES = "textFiles/Scenario01/deliveries.csv";
        RESULT = "textFiles/Scenario01/result.txt";
        VARIABLES = "textFiles/Scenario01/variables.txt";
        deleteFile(RESULT);
        deleteFile(VARIABLES);
    }
    
    private static void scenario02(){
        CARTS = "textFiles/Scenario02/carts.csv";
        ORDERS = "textFiles/Scenario02/orders.csv";
        PATHS = "textFiles/Scenario02/paths.csv";
        STOCK = "textFiles/Scenario02/stock.csv";
        PREPARED_ORDERS = "textFiles/Scenario02/prep_orders.csv";
        RUNS = "textFiles/Scenario02/runs.csv";
        DELIVERIES = "textFiles/Scenario02/deliveries.csv";
        RESULT = "textFiles/Scenario02/result.txt";
        VARIABLES = "textFiles/Scenario02/variables.txt";
        deleteFile(RESULT);
        deleteFile(VARIABLES);
    }
    
    private static void scenario03(){
        CARTS = "textFiles/Scenario03/carts.csv";
        ORDERS = "textFiles/Scenario03/orders.csv";
        PATHS = "textFiles/Scenario03/paths.csv";
        STOCK = "textFiles/Scenario03/stock.csv";
        PREPARED_ORDERS = "textFiles/Scenario03/prep_orders.csv";
        RUNS = "textFiles/Scenario03/runs.csv";
        DELIVERIES = "textFiles/Scenario03/deliveries.csv";
        RESULT = "textFiles/Scenario03/result.txt";
        VARIABLES = "textFiles/Scenario03/variables.txt";
        deleteFile(RESULT);
        deleteFile(VARIABLES);
    }
    
    private static void scenario04(){
        CARTS = "textFiles/Scenario04/carts.csv";
        ORDERS = "textFiles/Scenario04/orders.csv";
        PATHS = "textFiles/Scenario04/paths.csv";
        STOCK = "textFiles/Scenario04/stock.csv";
        PREPARED_ORDERS = "textFiles/Scenario04/prep_orders.csv";
        RUNS = "textFiles/Scenario04/runs.csv";
        DELIVERIES = "textFiles/Scenario04/deliveries.csv";
        RESULT = "textFiles/Scenario04/result.txt";
        VARIABLES = "textFiles/Scenario04/variables.txt";
        deleteFile(RESULT);
        deleteFile(VARIABLES);
    }
    
    private static void scenario05() {
        CARTS = "textFiles/Scenario05/carts.csv";
        ORDERS = "textFiles/Scenario05/orders.csv";
        PATHS = "textFiles/Scenario05/paths.csv";
        STOCK = "textFiles/Scenario05/stock.csv";
        PREPARED_ORDERS = "textFiles/Scenario05/prep_orders.csv";
        RUNS = "textFiles/Scenario05/runs.csv";
        DELIVERIES = "textFiles/Scenario05/deliveries.csv";
        RESULT = "textFiles/Scenario05/result.txt";
        VARIABLES = "textFiles/Scenario05/variables.txt";
        deleteFile(RESULT);
        deleteFile(VARIABLES);
    }


    public static int insertClients(String file, List<String> points) {
       RegisterClientController controller;
       String line[];
       int usersAdded = 0;
       boolean addToList = points != null;
       for(String user : importFile(file)){
           line = user.split(";");
           controller = new RegisterClientController();
           controller.newAddress(line[3], Double.parseDouble(line[5]), Double.parseDouble(line[4]), Double.parseDouble(line[6]), line[7], Integer.parseInt(line[9]), line[8], line[15]);
           controller.newCreditCard(Long.parseLong(line[10]), line[11], Integer.parseInt(line[12]));
           controller.newClient(line[0], line[1],line[2],Integer.parseInt(line[14]) , Integer.parseInt(line[13]));
           try {
               controller.registClient();
               if(addToList){
                   points.add(line[4]+";"+line[5]);
               }
               usersAdded++;
           } catch (SQLException ex) {
               System.out.println(usersAdded);
               ex.printStackTrace();
           }
       }
          return usersAdded;      

    }
    
    public static int insertPharmacies(String file,List<String> points){
        RegisterPharmacyController controller = new RegisterPharmacyController();
        String line[];
        int pharmaciesAdded = 0;
        boolean addToList = points != null;
        for(String pharmacy : importFile(file)){
            line = pharmacy.split(";");
            controller.newAddress(line[2], Double.parseDouble(line[4]), Double.parseDouble(line[3]), Double.parseDouble(line[5]), line[6], line[7], Integer.parseInt(line[8]), line[16]);
            controller.newAdministrator(line[9], line[10], line[11]);
            controller.newPark(line[12], Integer.parseInt(line[13]), Integer.parseInt(line[14]), Double.parseDouble(line[15]));
            controller.newPharmacy(line[0], Integer.parseInt(line[1]));
            controller.addToQueue();
            if(addToList){
                points.add(line[3]+";"+line[4]);
            }
        }
        try {
            pharmaciesAdded = controller.registPharmacies();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pharmaciesAdded;
    }
    
    private static int insertProducts() {
        RegisterProductController controller = new RegisterProductController();
        String line[];
        int productsAdded = 0;
        for(String product : importFile(PRODUCTS)){
            line = product.split(";");
            controller.newProduct(line[0], Double.parseDouble(line[1]), Double.parseDouble(line[2]));
            controller.addToQueue();
        }
        try {
            productsAdded = controller.registProduct();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return productsAdded;
    }
    
    private static int insertCouriers() {
        RegisterCourierController controller;
        String line[];
        int couriersAdded = 0;
        for(String courier: importFile(COURIERS)){
            line = courier.split(";");
            controller = new RegisterCourierController(Integer.parseInt(line[0]));
            controller.newCourier(line[1], line[2], line[3], Integer.parseInt(line[4]), Integer.parseInt(line[5]), Double.parseDouble(line[6]));
            controller.addToQueue();
            try {
                couriersAdded = couriersAdded + controller.registCouriers();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return couriersAdded;
    }
    
    private static int insertVehicles() {
        NewVehicleController controller;
        String line[];
        int vehiclesAdded = 0;
        for(String vehicle : importFile(VEHICLES)){
            try{
            line = vehicle.split(";");
            controller = new NewVehicleController(Integer.parseInt(line[0]));
            if(line[1].equalsIgnoreCase("Scooter")){
                controller.newEScooter(Double.parseDouble(line[2]), Double.parseDouble(line[3]));
                controller.addVehicleToQueue();
                vehiclesAdded = vehiclesAdded + controller.registerVehicles();
            }else if(line[1].equalsIgnoreCase("Drone")){
                System.out.println("drone");
                controller.newDrone(Double.parseDouble(line[2]), Double.parseDouble(line[3]));
                controller.addVehicleToQueue();
                vehiclesAdded = vehiclesAdded + controller.registerVehicles();
                System.out.println("Drone added.");
            }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return vehiclesAdded;
    }

    
    private static int insertPaths() {
        try {
            AddPathController controller = new AddPathController();
            String line[];
            controller.getAvailableGeographicalPoints();
            for(String path : importFile(PATHS)){
                
                line = path.split(";");
                controller.selectPoints(Double.parseDouble(line[1]), Double.parseDouble(line[0]), Double.parseDouble(line[3]), Double.parseDouble(line[2]), line[4].equals("-")?null:line[4], Double.parseDouble(line[5]),Double.parseDouble(line[6]),Double.parseDouble(line[7]),line[8], line[9].equalsIgnoreCase("-")?null:line[9]);
                boolean isBidirectional = false;
                if(line[10].equalsIgnoreCase("y")){
                    isBidirectional = true;
                }
                controller.addToQueue(isBidirectional);
            }
            return controller.savePaths();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
    private static int updateCarts() {
        AddToCartController controller;
        String line[];
        String products[];
        String product[];
        int cartsUpdated = 0;
        int i;
        for(String cart : importFile(CARTS)){
            try {
                line = cart.split(";");
                products = line[1].split("%");
                controller = new AddToCartController(line[0]);
                i = 0;
                controller.getAvailableProducts();
                
                while(i < products.length){
                    product = products[i].split("!");
                    controller.getSelectedProduct(Integer.parseInt(product[0]));
                    controller.addToQueue(Integer.parseInt(product[1]));
                    i++;
                }
                controller.addToCart();
                cartsUpdated++;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return cartsUpdated;
    }
    
    private static int updateStock(){
        UpdateStockController controller;
        String line[];
        int productsAdded = 0;
        for(String stock : importFile(STOCK)){
            try {
                line = stock.split(";");
                controller = new UpdateStockController(Integer.parseInt(line[0]));
                controller.getSelectedProduct(Integer.parseInt(line[1]));
                controller.addToQueue(Integer.parseInt(line[2]));
                controller.updateStock();
                productsAdded++;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return productsAdded;
    }
    
    private static int makeOrders() {
        MakeOrderController controller;
        int ordersMade = 0;
        String line[];
        for(String order : importFile(ORDERS)){
            try {
                line = order.split(";");
                controller = new MakeOrderController(line[0]);
                controller.getCart();
                controller.getAddress();
                controller.getCredits();
                if(line[2].equals("y")){
                    controller.discount();
                }
                if(controller.makeOrder(Integer.parseInt(line[1]))){
                    ordersMade++;
                    write(String.format("The order made by client who lives in %s was assigned to %s (nearest pharmacy).%n%n", controller.getClientAddress().getGeographicalPoint().getDescription().replace("Client - ", ""), controller.getPharmacyAssigned().getName()), RESULT);
                    if(controller.otherPharmacy() != null){
                        write(String.format("Delivery Note: The pharmacy assigned has no stock for the order and so that a new order to get that missing stock was made and assigned to %s.%n %n", controller.otherPharmacy().getName()),RESULT);
                    }
                }else{
                    write(String.format("No pharmacy has enough stock for this order. %n %n"),RESULT);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return ordersMade;
    }
    
    private static int prepareOrders() {
        PrepareOrderController prepare;
        NotifyReadyOrderController prepared;
        int ordersPrepared = 0;
        String line[];
        for(String order: importFile(PREPARED_ORDERS)){
            try {
                line = order.split(";");
                prepare = new PrepareOrderController(Integer.parseInt(line[0]));
                prepare.getReadyToPrepareOrders();
                prepare.getSelectedOrder(Integer.parseInt(line[1]));
                if(prepare.prepareOrder()){
                prepared = new NotifyReadyOrderController(Integer.parseInt(line[0]));
                prepared.getPreparingOrders();
                prepared.getSelectedOrder(Integer.parseInt(line[1]));
                prepared.setOrderToReady();
                ordersPrepared++;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return ordersPrepared;
    }
    
    private static int assignOrders() {
       AssignOrderController controller;
       int ordersAssigned = 0;
       String line[];
       String orders[];
       int i;
       for(String order : importFile(RUNS)){
           try {
               line = order.split(";");
               controller = new AssignOrderController(Integer.parseInt(line[0]));
               i = 0;
               orders = line[2].split("!");
               while(i < orders.length){
                   controller.getAvailableOrders();
                   controller.selectOrder(Integer.parseInt(orders[i]));
                   controller.addOrder();
                   i++;
               }
               String land = controller.getLandRoute();
               String air = controller.getAirRoute();
               String tLand = controller.getLessTimeLand();
               String tAir = controller.getLessTimeAir();
               controller.newDeliveryRun(line[1]);
               
               controller.addToQueue();
               ordersAssigned = ordersAssigned + controller.saveDeliveryRuns();
               write(String.format("Land route (Less time):%n%s%n", tLand==null?"Not found":tLand),RESULT);
               write(String.format("Air route (Less time):%n%s%n", tAir==null?"Not found":tAir),RESULT);
               write(String.format("Land route (Less energy):%n%s%n", land==null?"Not found":land),RESULT);
               write(String.format("Air route (Less energy):%n%s%n", air==null?"Not found":air),RESULT);
               write(String.format("Most efficient: %s %n %n", controller.getMostEfficient()),RESULT);
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
           
           
       }
       return ordersAssigned;
    }
    
    private static int startRuns() {
       StartDeliveryRunController controller;
       String line[];
       int startedRuns = 0;
       for(String delivery : importFile(DELIVERIES)){
           try {
               line = delivery.split(";");
               String email = line[1];
               if(email.equalsIgnoreCase("-")){
                   email = null;
               }
               controller = new StartDeliveryRunController(Integer.parseInt(line[0]), email, Double.parseDouble(line[2]));
               controller.getDeliveryRuns();
               controller.selectDeliveryRun(Integer.parseInt(line[3]));
               controller.getAvailableVehicles();
               controller.selectVehicle(Integer.parseInt(line[4]));
               if(controller.startDeliveryRun()){
                   if(email != null){
                        write(String.format("The courier with email %s started the delivery run #%d and took the scooter #%d. %n %n", line[1],Integer.parseInt(line[3]), Integer.parseInt(line[4])),RESULT);
                        startedRuns++;
                   }else{
                       write(String.format("The drone #%d started the delivery run #%d. %n %n",Integer.parseInt(line[4]), Integer.parseInt(line[3])),RESULT);
                       startedRuns++;
                   }
               }
               String route = controller.getRoute();
               write(String.format("Route for the delivery #%d: %n%s %n", Integer.parseInt(line[3]),route==null?"Not found":route),RESULT);
               double ene = controller.getEnergyToStart();
               if(ene > 0){
                   if(email != null){
                        write(String.format("The scooter must have at least %.2f kWh to start the delivery. %n", ene),RESULT);
                   }else{
                       write(String.format("The drone must have at least %.2f kWh to start the delivery. %n", ene),RESULT);
                   }
               }
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
       }
       return startedRuns;
    }
    
    

    

















 









    
}
