package lapr.project.controller;

import lapr.project.data.OrderDB;
import lapr.project.model.Order;
import lapr.project.utils.Constants;
import lapr.project.utils.Utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AssignOrderController {

    private final OrderDB orderDB;
    private ArrayList<Order> ordersReadyForAssign;


    public AssignOrderController(){
        this.orderDB=new OrderDB();
        this.ordersReadyForAssign= new ArrayList<>();
    }

    public void assignOrders(int pharmID) throws SQLException {
        int i=1;
        int j= 1;
        this.ordersReadyForAssign= (ArrayList<Order>) orderDB.getOrdersByStatus(pharmID, "Prepared");
        ArrayList<Order> route= new ArrayList<>();
        if (ordersReadyForAssign!=null){
            Collections.sort(ordersReadyForAssign);
            route.add(ordersReadyForAssign.get(0));
            String zip1=ordersReadyForAssign.get(0).getAddress().getZipCode();
            while ( i<Constants.MAX_ORDERS_RUN || j<ordersReadyForAssign.size()){

                Order iterate = ordersReadyForAssign.get(j);
                String zip2=iterate.getAddress().getZipCode();
                if (zip1.substring(0, 3).equals(zip2.substring(0,3))){
                    route.add(iterate);
                    i++;
                    j++;
                }else{
                   for (Order o: route){
                       if (Utils.distance(o.getAddress().getGeographicalPoint().getLatitude(), iterate.getAddress().getGeographicalPoint().getLatitude(),
                               o.getAddress().getGeographicalPoint().getLongitude(), iterate.getAddress().getGeographicalPoint().getLongitude(),
                               o.getAddress().getGeographicalPoint().getElevation(), iterate.getAddress().getGeographicalPoint().getElevation())> Constants.MAX_DISTANCE){
                           route.add(iterate);
                           i++;
                       }
                       j++;
                   }
        }

    }
}}}
