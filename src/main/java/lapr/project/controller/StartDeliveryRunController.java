/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.ClientDB;
import lapr.project.data.DeliveryRunDB;
import lapr.project.data.GeographicalPointDB;
import lapr.project.data.VehicleDB;
import lapr.project.model.AirGraph;
import lapr.project.model.Client;
import lapr.project.model.DeliveryRun;
import lapr.project.model.EScooter;
import lapr.project.model.GeographicalPoint;
import lapr.project.model.LandGraph;
import lapr.project.model.MainGraph;
import lapr.project.model.Order;
import lapr.project.model.Pathway;
import lapr.project.model.Product;
import lapr.project.model.Vehicle;
import lapr.project.model.VehicleCategory;
import lapr.project.model.comparator.EnergyComparator;
import lapr.project.utils.Constants;
import lapr.project.utils.Utils;
import lapr.project.utils.route.Route;

/**
 *
 * @author Diogo
 */
public class StartDeliveryRunController {
    

    private final DeliveryRunDB drdb;
    private final GeographicalPointDB gpdb;
    private final ClientDB cdb;
    private final VehicleDB vdb;
    private final int idPharmacy;
    private final double courierWeight;
    private final String email;
    private List<DeliveryRun> runs;
    private List<Vehicle> vehicles;
    private double deliveryWeight;
    private double vehicleWeight;
    private DeliveryRun dr;
    private Vehicle vehicle;
    
    private Route r;

    public StartDeliveryRunController(DeliveryRunDB drdb, GeographicalPointDB gpdb,ClientDB cdb, VehicleDB vdb, int idPharmacy, double courierWeight, String email) {
        this.drdb = drdb;
        this.gpdb = gpdb;
        this.cdb = cdb;
        this.idPharmacy = idPharmacy;
        this.courierWeight = courierWeight;
        this.email = email;
        this.runs = new ArrayList<>();
        this.vehicles = new ArrayList<>();
        this.deliveryWeight = 0;
        this.vdb = vdb;
        if(email!=null){
            vehicleWeight = Constants.SCOOTER_WEIGHT;
        }else{
            vehicleWeight = Constants.DRONE_WEIGHT;
        }
    }

    public StartDeliveryRunController(int idPharmacy, String email, double weight) {
        this.drdb = new DeliveryRunDB();
        this.gpdb = new GeographicalPointDB();
        this.cdb = new ClientDB();
        this.vdb = new VehicleDB();
        this.idPharmacy = idPharmacy;
        this.courierWeight = weight;
        this.email = email;
        this.deliveryWeight = 0;
        this.runs = new ArrayList<>();
        this.vehicles = new ArrayList<>();
        if(email!=null){
            vehicleWeight = Constants.SCOOTER_WEIGHT;
        }else{
            vehicleWeight = Constants.DRONE_WEIGHT;
        }
    }
    
    
    
    
    
    public List<String> getDeliveryRuns() throws SQLException{
        if(email == null){
            runs = drdb.getDeliveryRuns(idPharmacy, VehicleCategory.DRONE);
        }else{
            runs = drdb.getDeliveryRuns(idPharmacy, VehicleCategory.SCOOTER);
        }
        
        return Utils.listToString(runs);
    }
    
    public String selectDeliveryRun(int id){
        for(DeliveryRun dt : runs){
            if(dt.getId() == id){
                dr = dt;
                deliveryWeight = 0;
                for(Order ord : dt.getOrders()){
                    for(Product p : ord.getProducts().keySet()){
                        deliveryWeight = deliveryWeight + (p.getWeight()*ord.getProducts().get(p));
                    }
                }
                return dr.toString();
            }
        }
        return null;
    }
    
    public List<String> getAvailableVehicles() throws SQLException{
        
        if(email != null){
            vehicles = vdb.getAvailableScooters(idPharmacy);
        }else{
            vehicles = vdb.getAvailableDrones(idPharmacy);
        }
        return Utils.listToString(vehicles);
    }
    
    public String selectVehicle(int id){

        for(Vehicle v : vehicles){
            if(v.getId()== id){
                vehicle = v;
                return v.toString();
            }
        }
        return null;
   }
    
    public boolean startDeliveryRun() throws SQLException{
        double totalWeight = deliveryWeight + courierWeight + vehicleWeight;
        MainGraph graph;
        if(email != null){
            graph = new LandGraph(totalWeight);
        }else{
            graph = new AirGraph(totalWeight);
        }
        GeographicalPoint pharmacyAdd = gpdb.getGeographicalPointByPharmacy(idPharmacy);
        List<GeographicalPoint> points = new ArrayList<>();
        for(Order o : dr.getOrders()){
            points.add(o.getAddress().getGeographicalPoint());
        }
        List<Route> routes = new ArrayList<>();
        try{
            
            routes = graph.kBestPaths(points, pharmacyAdd, pharmacyAdd, 1, vehicle.getMaxBat());
            if(!routes.isEmpty()){
               List<Route> energyOrder = new ArrayList<>(routes);
               energyOrder.sort(new EnergyComparator());
               r = energyOrder.get(0); 
            }
            
        }catch(IllegalArgumentException e){
            r = null;
        }catch(NullPointerException e){
            r = null;
        }
        
        boolean res = drdb.startDelivery(dr.getId(), email, r, vehicle.getId());
        
        
        if(res){
            List<Client> clients = cdb.getClientsByDeliveryRun(dr.getId());
            for(Client c : clients){
                Utils.sendEmail(c.getEmail(), "Delivering", "Your order is being delivered.");
            }
        }
        return res;
        
    }
    
    public String getRoute(){
        return r==null?null:r.toString();
    }
    
    public double getEnergyToStart(){
        if(r!= null && !r.getChargingPoints().isEmpty()){
            GeographicalPoint chargingStation = r.getChargingPoints().get(0);
            double energy = 0;
            for(Pathway p : r.getPaths()){
                energy = energy + p.getCost();
                if(p.getDestinationPoint().equals(chargingStation)){
                    return energy;
                }
            }
        }
        return 0;
    }
    
    
    
    
    
   

    
    
    
    
}
