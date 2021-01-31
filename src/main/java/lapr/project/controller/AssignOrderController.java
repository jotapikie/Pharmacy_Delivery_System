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
import java.util.TreeMap;
import lapr.project.data.DeliveryRunDB;
import lapr.project.data.GeographicalPointDB;
import lapr.project.model.AirGraph;
import lapr.project.model.DeliveryRun;
import lapr.project.model.GeographicalPoint;
import lapr.project.model.LandGraph;
import lapr.project.model.Product;
import lapr.project.model.comparator.EnergyComparator;
import lapr.project.model.comparator.TimeComparator;
import lapr.project.utils.route.Route;

public class AssignOrderController {

    private final OrderDB orderDB;
    private final DeliveryRunDB runDB;
    private final GeographicalPointDB pointDB;
    private final int idPharmacy;
    private List<Order> orders;
    
    private final List<Order> ordersSelected;
    private final List<Order> allSelected;
    private Order ord;
    private DeliveryRun run;
    private final Set<DeliveryRun> runs;
    private double totalWeight;
    private GeographicalPoint pharmacyCor;
    private final List<GeographicalPoint> clients;
    
    private Route landEnergy;
    private Route airEnergy;
    
    private List<Route> land;
    private List<Route> air;
    

    public AssignOrderController(OrderDB orderDB, DeliveryRunDB runDB,GeographicalPointDB pointDB, int idPharmacy) {
        this.orderDB = orderDB;
        this.runDB = runDB;
        this.pointDB = pointDB;
        this.idPharmacy = idPharmacy;
        this.ordersSelected = new ArrayList<>();
        this.allSelected = new ArrayList<>();
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
        this.allSelected = new ArrayList<>();
        runs = new HashSet<>();
        clients = new ArrayList<>();
        totalWeight = 0;
    }
    
    public List<String> getAvailableOrders() throws SQLException{
        orders = orderDB.getOrdersByStatus(idPharmacy, "Prepared");
        orders.removeAll(allSelected);
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
        
        allSelected.add(ord);
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
        
   
        
        try{
            
            land = landGraph.kBestPaths(clients, pharmacyCor, pharmacyCor, 10, Constants.SCOOTER_MAX_BATTERY);
            List<Route> energyOrder = new ArrayList<>(land);
            energyOrder.sort(new EnergyComparator());
            landEnergy = energyOrder.get(0);
            return landEnergy.toString();
        }catch(NullPointerException e){
            return null;
        }catch(IllegalArgumentException e){
            return null;
        }

    }
    
    public String getLessTimeLand(){
        List<Route> timeOrder = new ArrayList<>(land);
        timeOrder.sort(new TimeComparator());
        return timeOrder.get(0).toString();
    }
    
      public String getAirRoute() throws SQLException{
        AirGraph airGraph = new AirGraph(totalWeight + Constants.DRONE_WEIGHT);
        try{
            air = airGraph.kBestPaths(clients, pharmacyCor, pharmacyCor, 10, Constants.DRONE_MAX_BATTERY);
            List<Route> energyOrder = new ArrayList<>(air);
            energyOrder.sort(new EnergyComparator());
            airEnergy = energyOrder.get(0);
            return airEnergy.toString();
        }catch(NullPointerException e){
            return null;
        }catch(IllegalArgumentException e){
            return null;
        }
   
        
    }
      
      public String getLessTimeAir(){
          List<Route> timeOrder = new ArrayList<>(air);
          timeOrder.sort(new TimeComparator());
          return timeOrder.get(0).toString();
      }
    
    public String getMostEfficient(){
        double airEnergyInKWH = (Constants.DRONE_BATTERY_VOLTAGE * airEnergy.getTotalEnergy()) / 1000000;
        double landEnergyInKWH = landEnergy.getTotalEnergy();
        if(landEnergy != null && airEnergy != null){
           if(airEnergyInKWH < landEnergyInKWH){
               return "Drone";
           }else{
               if(airEnergyInKWH > landEnergyInKWH){
                   return "Scooter";
               }else{
                   if(landEnergy.getTotalDistance() > airEnergy.getTotalDistance()){
                       return "Drone";
                   }else{
                       if(landEnergy.getTotalDistance() < airEnergy.getTotalDistance()){
                           return "Scooter";
                       }
                   }
               }
               
           }
        }else{
            if(landEnergy != null && airEnergy == null){
                return "Scooter";
            }else if(landEnergy == null && airEnergy != null){
                return "Drone";
            }
        }
        return null;
    }
    
    public String newDeliveryRun(String category){
        if(!ordersSelected.isEmpty()){
            run = runDB.newDeliveryRun(Constants.DEFAULT_ID+runs.size(), category, new ArrayList<>(ordersSelected));
            return run==null ? null : run.toString();
        }
        return null;
    }
    
    public boolean addToQueue(){
        if(run != null){
            ordersSelected.clear();
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
