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
import lapr.project.model.DeliveryRun;
import lapr.project.model.Product;

public class AssignOrderController {

    private final OrderDB orderDB;
    private final DeliveryRunDB runDB;
    private int idPharmacy;
    private List<Order> orders;
    
    private final List<Order> ordersSelected;
    private Order ord;
    private DeliveryRun run;
    private Set<DeliveryRun> runs;
    private double totalWeight;

    public AssignOrderController(OrderDB orderDB, DeliveryRunDB runDB, int idPharmacy) {
        this.orderDB = orderDB;
        this.runDB = runDB;
        this.idPharmacy = idPharmacy;
        this.ordersSelected = new ArrayList<>();
        this.runs = new HashSet<>();
        this.totalWeight = 0;
        
    }
    
   public AssignOrderController(int idPharmacy){
        this.orderDB=new OrderDB();
        this.runDB = new DeliveryRunDB();
        this.idPharmacy = idPharmacy;
        ordersSelected = new ArrayList<>();
        runs = new HashSet<>();
        totalWeight = 0;
    }
    
    public List<String> getAvailableOrders() throws SQLException{
        orders = orderDB.getOrdersByStatus(idPharmacy, "Prepared");
        orders.removeAll(ordersSelected);
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
    
    public String getAirRoute(){
        return null;
    }
    
    public String getLandRoute(){
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
