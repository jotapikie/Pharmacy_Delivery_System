/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.DeliveryRunDB;
import lapr.project.data.GeographicalPointDB;
import lapr.project.model.DeliveryRun;
import lapr.project.model.EScooter;
import lapr.project.model.GeographicalPoint;
import lapr.project.model.LandGraph;
import lapr.project.model.Order;
import lapr.project.model.Product;
import lapr.project.utils.Utils;
import lapr.project.utils.route.Route;

/**
 *
 * @author Diogo
 */
public class StartDeliveryRunController {
    

    private final DeliveryRunDB drdb;
    private final GeographicalPointDB gpdb;
    private final int idPharmacy;
    private final double courierWeight;
    private final String email;
    private List<DeliveryRun> dels;
    private double deliveryWeight;
    private DeliveryRun dr;

    public StartDeliveryRunController(DeliveryRunDB drdb, GeographicalPointDB gpdb, int idPharmacy, double courierWeight, String email) {
        this.drdb = drdb;
        this.gpdb = gpdb;
        this.idPharmacy = idPharmacy;
        this.courierWeight = courierWeight;
        this.email = email;
        this.dels = new ArrayList<>();
        this.deliveryWeight = 0;
    }
    
    
    
    public List<String> getDeliveryRuns() throws SQLException{
        dels = drdb.getDeliveryRuns(idPharmacy);
        return Utils.listToString(dels);
    }
    
    public String selectDeliveryRun(int id){
        for(DeliveryRun dt : dels){
            if(dt.getId() == id){
                dr = dt;
                deliveryWeight = 0;
                for(Order ord : dt.getOrders()){
                    for(Product p : ord.getProducts().keySet()){
                        deliveryWeight = deliveryWeight + (p.getWeight()*ord.getProducts().get(p));
                    }
                }
            }
        }
        return dr == null ? null : dr.toString();
    }
    
    public int startDeliveryRun() throws SQLException{
        double totalWeight = deliveryWeight + courierWeight + EScooter.getGenericWeight();
        LandGraph graph = new LandGraph(totalWeight, EScooter.getGenericAeroCoef());
        GeographicalPoint orDest = gpdb.getGeographicalPointByPharmacy(idPharmacy);
        List<GeographicalPoint> points = gpdb.getPointsByDeliveryRun(dr.getId());
        List<Route> routes = new ArrayList<>();
        int nrVehicle = -1;
        try{
            routes = graph.kBestPaths(points, orDest, orDest, 1);
            Route r = routes.isEmpty()? null : routes.get(0);
            nrVehicle = drdb.startDelivery(dr.getId(),email,r);
        }catch(IllegalArgumentException e){
            nrVehicle = drdb.startDelivery(dr.getId(),email,null);
        }
        return nrVehicle;
    }
    

    
    
    
    
}
