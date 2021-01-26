/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lapr.project.controller.AddPathController;
import lapr.project.model.GeographicalPoint;
import lapr.project.model.LandGraph;
import lapr.project.model.Pathway;
import lapr.project.model.ScooterPath;
import lapr.project.model.StreetType;
import lapr.project.model.VehicleCategory;
import lapr.project.model.Wind;
import static lapr.project.ui.UtilsUI.header;
import lapr.project.utils.graph.Edge;
import lapr.project.utils.graph.Graph;
import lapr.project.utils.route.Route;


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
//        System.out.println(Utils.distance(41.16875, 41.14582, -8.68995, -8.61398, 4, 87));
//        AddPathController c = new AddPathController();
//        String a = c.selectPoints(-8.60929,41.15227,-8.61398,41.14582,"Asphalt", 1,1,1,"Scooter", "Street1");
        

        LandGraph graph = new LandGraph(123);
        GeographicalPoint p1 = new GeographicalPoint(-8.60929,41.15227,104, "Pharmacy");
        GeographicalPoint p2 = new GeographicalPoint(-8.61398,41.14582,87, "Clerigos");
        GeographicalPoint p3 = new GeographicalPoint(-8.60746,41.14871,87, "Bolhao");
        GeographicalPoint p4 = new GeographicalPoint(-8.61118,41.14063,25, "Cais");
        GeographicalPoint p5 = new GeographicalPoint(-8.60657,41.14723,91, "Majestic");
        Graph<GeographicalPoint, ScooterPath> g = graph.getRouteGraph();
//        
//        List<GeographicalPoint> toVisit = new ArrayList<>();
//        toVisit.add(p3);
//        List <Route> route = graph.kBestPaths(toVisit,p1, p4, 1);
//        System.out.println("Pharmacy to Cais:" +route.get(0));
//        
//        route = graph.kBestPaths(p4, p3, 1);
//        System.out.println("Cais to Bolhao:" +route.get(0));
//        
//        route = graph.kBestPaths(p3, p2, 1);
//        System.out.println("Bolhao to Clerigos:" +route.get(0));
//        
//        route = graph.kBestPaths(p2, p1, 1);
//        System.out.println("Clerigos to Pharmacy: " +route.get(0));
        
//        List<Route>route = graph.kBestPaths(p4, p5, 1);
//        System.out.println("Cais to Majestic: " +route.get(0));
//        
//        List<Route> route2 = graph.kBestPaths(p4, p1, 1);
//        System.out.println("Cais to Clerigos: " +route2.get(0));
//        
//        List<Route> route = graph.kBestPaths(p1, p4, 1);
//        System.out.println("Clerigos to Cais: " +route.get(0));
        
        
        for(GeographicalPoint p : g.vertices()){
            for(GeographicalPoint adj : g.adjVertices(p)){
                System.out.println(g.getEdge(p, adj));
            }
        }
        


 
        


        //showOptions();
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

    private static void asPharmacyAdministrator(){
        header("x Login x");
        System.out.println();
        System.out.println("Id Pharmacy:");
        int id = Integer.parseInt(read.nextLine());
        showPharmacyMenu(id);
        
    }

    private static void asCourier() {
        header("x Login x");
        System.out.println();
        System.out.println("Id Pharmacy:");
        int id = Integer.parseInt(read.nextLine());
        System.out.println();
        System.out.println("Email:");
        String email = read.nextLine();
        System.out.println();
        System.out.println("Weight:");
        double weight = Double.parseDouble(read.nextLine());
        showCourierMenu(id, email, weight);
    }

    private static void asClient() {
        header("x Login x");
        System.out.println();
        System.out.println("Email:");
        String email = read.nextLine();
        showClientMenu(email);
    }
    
    private static void showClientMenu(String email){
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
                showClientMenu(email);
                break;
            case "2":
                new RemoveFromCartUI(email);
                showClientMenu(email);
                break;
            case "3":
                new MakeOrderUI(email);
                showClientMenu(email);
                break;
            case "0":
                break;
            default:
                System.out.println();
                System.out.println("Error: Invalid oprion, try again.");
                showClientMenu(email);
        }
    }

    private static void showPharmacyMenu(int id) {
        header("x Menu Pharmacy x");
        System.out.println();
        System.out.println("1. Add vehicle");
        System.out.println("2. Remove vehicle");
        System.out.println("3. Update vehicle (Not supported yet)");
        System.out.println("4. Update stock");
        System.out.println("5. Prepare order");
        System.out.println("6. Notify prepared order");
        System.out.println("7. Add courier");
        System.out.println("8. Add park");
        System.out.println("9. Add delivery run (Not supported yet)");
        System.out.println();
        System.out.println("0. Logout");
        System.out.println();
        String ans = read.nextLine();
        switch(ans){
            case "1":
                new NewVehicleUI(id);
                showPharmacyMenu(id);
                break;
            case "2":
                new RemoveVehicleUI(id);
                showPharmacyMenu(id);
                break;
            case "3":
                System.out.println("Not supported yet.");
                showPharmacyMenu(id);
                break;
            case "4":
                new UpdateStockUI(id);
                showPharmacyMenu(id);
                break;
            case "5":
                new PrepareOrderUI(id);
                showPharmacyMenu(id);
                break;
            case "6":
                new NotifyReadyOrderUI(id);
                showPharmacyMenu(id);
                break;  
            case "7":
                new RegisterCourierUI(id);
                showPharmacyMenu(id);
                break;
            case "8":
                new AddParkUI(id);
                showPharmacyMenu(id);
                break;
            case "9":
                System.out.println("Not supported yet");
                showPharmacyMenu(id);
                break;
                
            case "0":
                break;
            default:
                System.out.println();
                System.out.println("Error: Invalid option, try again");
                showPharmacyMenu(id);
        }
    }

    private static void showCourierMenu(int id, String email, double weight) {
        header("x Menu Courier x");
        System.out.println();
        System.out.println("1. Start delivery run");
        System.out.println();
        System.out.println("0. Logout");
        System.out.println();
        String option = read.nextLine();
        switch(option){
            case "1":
                new StartDeliveryRunUI(id, weight, email);
                showCourierMenu(id, email, weight);
                break;
            case "0":
                break;
            default:
                System.out.println();
                System.out.println("Error: Invalid option, try again.");
                showCourierMenu(id, email, weight);
                
        }
    }
}
