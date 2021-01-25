package lapr.project.controller;

import lapr.project.data.OrderDB;
import lapr.project.model.Order;
import lapr.project.utils.Constants;
import lapr.project.utils.Utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lapr.project.data.DeliveryRunDB;
import lapr.project.data.GeographicalPointDB;
import lapr.project.model.AirGraph;
import lapr.project.model.DeliveryRun;
import lapr.project.model.GeographicalPoint;
import lapr.project.model.LandGraph;
import lapr.project.model.Product;
import lapr.project.utils.route.Route;

public class AssignOrderController {

    private final OrderDB orderDB;
    private final DeliveryRunDB runDB;
    private final GeographicalPointDB pointDB;
    private int idPharmacy;
    private List<Order> orders;
    
    private final List<Order> ordersSelected;
    private Order ord;
    private DeliveryRun run;
    private Set<DeliveryRun> runs;
    private double totalWeight;
    private GeographicalPoint pharmacyCor;
    private List<GeographicalPoint> clients;
    
    private Route land;
    private Route air;

    public AssignOrderController(OrderDB orderDB, DeliveryRunDB runDB,GeographicalPointDB pointDB, int idPharmacy) {
        this.orderDB = orderDB;
        this.runDB = runDB;
        this.pointDB = pointDB;
        this.idPharmacy = idPharmacy;
        this.ordersSelected = new ArrayList<>();
        this.runs = new HashSet<>();
        this.clients = new ArrayList<>();
        this.totalWeight = 0;
        
    }
    
   public AssignOrderController(int idPharmacy){
        this.orderDB=new OrderDB();
        this.runDB = new DeliveryRunDB();
        this.pointDB = new GeographicalPointDB();
        this.idPharmacy = idPharmacy;
        ordersSelected = new ArrayList<>();
        runs = new HashSet<>();
        clients = new ArrayList<>();
        totalWeight = 0;
    }
    
    public List<String> getAvailableOrders() throws SQLException{
        orders = orderDB.getOrdersByStatus(idPharmacy, "Prepared");
        orders.removeAll(ordersSelected);
        ordersSelected.clear();
        return Utils.listToString(orders);
    }
    
    public String selectOrder(int id) throws SQLException{
        ord = orderDB.getOrder(id);
        return (ord == null || !orders.contains(ord)) ? null : ord.toString();
    }
    
    public boolean addOrder(){
        if(ord != null){
        double oldWeight = totalWeight;
        for(Map.Entry<Product, Integer> p : ord.getProducts().entrySet()){
            totalWeight = totalWeight + (p.getKey().getWeight()*p.getValue());
        }
        if(totalWeight > Constants.MAX_PAYLOAD){
            totalWeight = oldWeight;
            return false;
        }
        return ordersSelected.add(ord);
        }
        return false;
    }
    
 
    
    public String getLandRoute() throws SQLException{
        LandGraph landGraph = new LandGraph(totalWeight + Constants.SCOOTER_WEIGHT + Constants.AVERAGE_COURIER_WEIGHT);
        pharmacyCor = pointDB.getGeographicalPointByPharmacy(idPharmacy);
        for(Order o : ordersSelected){
            clients.add(o.getAddress().getGeographicalPoint());
        }
        
        List<Route> routes = landGraph.kBestPaths(clients, pharmacyCor, pharmacyCor, 1);
        if(routes.isEmpty()){
            return null;
        }
        land = routes.get(0);
        return land.toString();
    }
    
      public String getAirRoute() throws SQLException{
        AirGraph airGraph = new AirGraph(totalWeight + Constants.DRONE_WEIGHT);
        List<Route> routes = airGraph.kBestPaths(clients, pharmacyCor, pharmacyCor, 1);
        if(routes.isEmpty()){
            return null;
        }
        air = routes.get(0);
        return air.toString();
        
    }
    
    public String getMostEfficient(){
        if(land != null && air != null){
           if(land.compareTo(air)>0){
               return "Drone";
           }else{
               return "Scooter";
           }
        }
        return null;
    }
    
    public String newDeliveryRun(String category){
        if(!ordersSelected.isEmpty()){
            run = runDB.newDeliveryRun(Constants.DEFAULT_ID+runs.size(), category, ordersSelected);
            return run==null ? null : run.toString();
        }
        return null;
    }
    
    public boolean addToQueue(){
        if(run != null){
            return runs.add(run);
        }
        return false;
    }
    
    public int saveDeliveryRuns() throws SQLException{
        if(!runs.isEmpty()){
            return runDB.saveRuns(runs);
        }
        return 0;
    }

}
