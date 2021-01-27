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
import lapr.project.model.Client;
import lapr.project.model.DeliveryRun;
import lapr.project.model.EScooter;
import lapr.project.model.GeographicalPoint;
import lapr.project.model.LandGraph;
import lapr.project.model.Order;
import lapr.project.model.Pathway;
import lapr.project.model.Product;
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
    private List<EScooter> scooters;
    private double deliveryWeight;
    private DeliveryRun dr;
    private EScooter scooter;
    
    private Route r;

    public StartDeliveryRunController(DeliveryRunDB drdb, GeographicalPointDB gpdb,ClientDB cdb, VehicleDB vdb, int idPharmacy, double courierWeight, String email) {
        this.drdb = drdb;
        this.gpdb = gpdb;
        this.cdb = cdb;
        this.idPharmacy = idPharmacy;
        this.courierWeight = courierWeight;
        this.email = email;
        this.runs = new ArrayList<>();
        this.scooters = new ArrayList<>();
        this.deliveryWeight = 0;
        this.vdb = vdb;
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
        this.scooters = new ArrayList<>();
    }
    
    
    
    
    
    public List<String> getDeliveryRuns() throws SQLException{
        runs = drdb.getDeliveryRuns(idPharmacy);
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
    
    public List<String> getAvailableScooters() throws SQLException{
        scooters = vdb.getAvailableScooters(idPharmacy);
        return Utils.listToString(scooters);
    }
    
    public String selectScooter(int id){
        for(EScooter e : scooters){
            if(e.getId()== id){
                scooter = e;
                return e.toString();
            }
        }
        return null;
    }
    
    public boolean startDeliveryRun() throws SQLException{
        double totalWeight = deliveryWeight + courierWeight + Constants.SCOOTER_WEIGHT;
        LandGraph graph = new LandGraph(totalWeight);
        GeographicalPoint pharmacyAdd = gpdb.getGeographicalPointByPharmacy(idPharmacy);
        List<GeographicalPoint> points = new ArrayList<>();
        for(Order o : dr.getOrders()){
            points.add(o.getAddress().getGeographicalPoint());
        }
        List<Route> routes = new ArrayList<>();
        try{
            routes = graph.kBestPaths(points, pharmacyAdd, pharmacyAdd, 1, scooter.getMaxBat());
            r = routes.get(0);
        }catch(IllegalArgumentException e){
            r = null;
        }catch(NullPointerException e){
            r = null;
        }
        
        boolean res = drdb.startDelivery(dr.getId(), email, r, scooter.getId());
        
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
        if(r!= null && !r.getStopPoints().isEmpty()){
            GeographicalPoint chargingStation = r.getStopPoints().get(0);
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
