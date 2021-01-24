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
import lapr.project.controller.NotifyReadyOrderController;
import lapr.project.controller.PrepareOrderController;
import lapr.project.controller.RegisterClientController;
import lapr.project.controller.RegisterCourierController;
import lapr.project.controller.RegisterPharmacyController;
import lapr.project.controller.RegisterProductController;
import lapr.project.controller.UpdateStockController;
import lapr.project.model.Wind;

/**
 *
 * @author Diogo
 */
public class TextFiles {
    private static Scanner read = new Scanner(System.in);
    
    private static final String CLIENTS = "textFiles/clients.csv";
    private static final String PHARMACIES = "textFiles/pharmacies.csv";
    private static final String PRODUCTS = "textFiles/products.csv";
    private static final String COURIERS = "textFiles/couriers.csv";

    private static String CARTS;
    private static String ORDERS;
    private static String PATHS;
    private static String STOCK;
    private static String PREPARED_ORDERS;
    private static String RUNS;
    
    private static String RESULT;
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
        // NOTA: ANTES DE EXCEUTAR ESTE CODIGO APAGAR OS DADOS DAS TABELAS PARA NAO ERROS DE CHAVES EXCLUSIVAS
        System.out.println("Adding products...");
        System.out.printf("%d products were added. %n", insertProducts());
        System.out.println("Adding pharmacies...");
        System.out.printf("%d pharmacies were added. %n", insertPharmacies());
        System.out.println("Adding clients...");
        System.out.printf("%d clients were added. %n", insertClients());
        System.out.println("Adding couriers...");
        System.out.printf("%d couriers were added. %n", insertCouriers());
        menu();
    }

    private static void menu() {
        System.out.println("Scenarios:");
        System.out.println();
        System.out.println("1. Scenario 01");
        System.out.println("2. Scenario 02");
        System.out.println("3. Scenario 03");
        System.out.println("4. Scenario 04");
        System.out.println();
        String ans = read.nextLine();
        switch(ans){
            case "1":
                scenario01();
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

        
                
    }

    private static void scenario01() {
        CARTS = "textFiles/Scenario01/carts.csv";
        ORDERS = "textFiles/Scenario01/orders.csv";
        PATHS = "textFiles/Scenario01/paths.csv";
        STOCK = "textFiles/Scenario01/stock.csv";
        PREPARED_ORDERS = "textFiles/Scenario01/prep_orders.csv";
        RUNS = "textFiles/Scenario01/runs.csv";
        RESULT = "textFiles/Scenario01/result.txt";
        
    }

    private static int insertClients() {
       RegisterClientController controller;
       String line[];
       int usersAdded = 0;
       for(String user : importFile(CLIENTS)){
           line = user.split(";");
           controller = new RegisterClientController();
           controller.newAddress(line[3], Double.parseDouble(line[5]), Double.parseDouble(line[4]), Double.parseDouble(line[6]), line[7], Integer.parseInt(line[9]), line[8]);
           controller.newCreditCard(Long.parseLong(line[10]), line[11], Integer.parseInt(line[12]));
           controller.newClient(line[0], line[1],line[2],Integer.parseInt(line[14]) , Integer.parseInt(line[13]));
           try {
               controller.registClient();
               usersAdded++;
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
       }
          return usersAdded;      

    }
    
    private static int insertPharmacies(){
        RegisterPharmacyController controller = new RegisterPharmacyController();
        String line[];
        int pharmaciesAdded = 0;
        for(String pharmacy : importFile(PHARMACIES)){
            line = pharmacy.split(";");
            controller.newAddress(line[2], Double.parseDouble(line[4]), Double.parseDouble(line[3]), Double.parseDouble(line[5]), line[6], line[7], Integer.parseInt(line[8]));
            controller.newAdministrator(line[9], line[10], line[11]);
            controller.newPark(line[12], Integer.parseInt(line[13]), Integer.parseInt(line[14]), Double.parseDouble(line[15]));
            controller.newPharmacy(line[0], Integer.parseInt(line[1]));
            controller.addToQueue();
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
    
    private static int insertPaths() {
        try {
            AddPathController controller = new AddPathController();
            String line[];
            controller.getAvailableGeographicalPoints();
            for(String path : importFile(PATHS)){
                
                line = path.split(";");
                controller.selectPoints(Double.parseDouble(line[1]), Double.parseDouble(line[0]), Double.parseDouble(line[3]), Double.parseDouble(line[2]), line[4], new Wind( Double.parseDouble(line[6]),Double.parseDouble(line[7]),Double.parseDouble(line[8])), line[9]);
                controller.addToQueue();
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
                    write(String.format("The order made by client whose cordinates are (%.5f,%.5f) was assigned to %s (nearest pharmacy).", controller.getClientAddress().getGeographicalPoint().getLatitude(), controller.getClientAddress().getGeographicalPoint().getLongitude(), controller.getPharmacyAssigned().getName()));
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
               write(String.format("Land route:%n%s%n", controller.getLandRoute()));
               write(String.format("Air route:%n%s%n", controller.getAirRoute()));
               write(String.format("Most efficient: %s %n", controller.getMostEfficient()));
               controller.newDeliveryRun(line[1]);
               controller.addToQueue();
               ordersAssigned = ordersAssigned + controller.saveDeliveryRuns();
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
           
           
       }
       return ordersAssigned;
    }
    
    
    private static List<String> importFile(String s) {
        boolean ignoreFirstLine = true;
        List<String> listToReturn = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(s))) {
            List<String> l = stream.collect(Collectors.toList());
            for (String str : l) {
                if (str.contains("#")) {
                    // Skip line
                } else if (ignoreFirstLine) {
                    ignoreFirstLine = false;
                } else {
                    listToReturn.add(str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listToReturn;
    }
    
    private static void write(String message){
        File log = new File(RESULT);
        BufferedWriter bufferedWriter = null;
        try{
            if(!log.exists()){
                log.createNewFile();
            }

        FileWriter fileWriter = new FileWriter(log, true);

        bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(message);


        } catch(IOException e) {
            e.printStackTrace();
        }finally{
            try {
                bufferedWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }











 









    
}
