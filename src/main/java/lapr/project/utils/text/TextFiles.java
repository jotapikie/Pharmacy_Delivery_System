/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lapr.project.controller.RegisterClientController;
import lapr.project.controller.RegisterPharmacyController;
import lapr.project.controller.RegisterProductController;
import lapr.project.data.DataHandler;

/**
 *
 * @author Diogo
 */
public class TextFiles extends DataHandler{
    private static Scanner read = new Scanner(System.in);
    
    private static final String USERS = "textFiles/users.csv";
    private static final String PHARMACIES = "textFiles/pharmacies.csv";
    private static final String PRODUCTS = "textFiles/products.csv";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // NOTA: ANTES DE EXCEUTAR ESTE CODIGO APAGAR OS DADOS DAS TABELAS PARA NAO ERROS DE CHAVES EXCLUSIVAS
        System.out.println("Adding products...");
        System.out.printf("%d products were added. %n", insertProducts());
        System.out.println("Adding pharmacies...");
        System.out.printf("%d pharmacies were added. %n", insertPharmacies());
        System.out.println("Adding users...");
        System.out.printf("%d users were added. %n", insertUsers());
        //menu();
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
                
    }

    private static void scenario01() {
        
    }

    private static int insertUsers() {
       RegisterClientController controller;
       String line[];
       int usersAdded = 0;
       for(String user : importFile(USERS)){
           line = user.split(";");
           controller = new RegisterClientController();
           controller.newAddress(line[5], Double.parseDouble(line[7]), Double.parseDouble(line[6]), Double.parseDouble(line[8]), line[9], Integer.parseInt(line[11]), line[10]);
           controller.newCreditCard(Long.parseLong(line[12]), line[13], Integer.parseInt(line[14]));
           controller.newClient(line[1], line[2],line[3],Integer.parseInt(line[16]) , Integer.parseInt(line[15]));
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






    
}
