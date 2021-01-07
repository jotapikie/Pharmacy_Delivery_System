/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.CourierDB;
import lapr.project.model.Courier;

/**
 *
 * @author Helder
 */
public class RegistCourierController {
    
    private Courier courier; // client known by the controller

    private final CourierDB courierDB; // client database, used to add a new client to our database
    
    private final List<Courier> courierList;

    public RegistCourierController() { 
        courierDB = new CourierDB(); courierList = new ArrayList<>(); 
    }

    public RegistCourierController(CourierDB courierDB) {
        this.courierDB = courierDB;
        courierList = new ArrayList<>(); 
    }

    public Courier newCourier(String email, int nif, int nss, double maxWeight){
        courier = courierDB.newCourier(email,nif,nss,maxWeight);
        return courier;
    }

    public boolean registerCourier() throws SQLException {
        return courierDB.saveCourier(courier);
    }

    public boolean addCourierToQueue(){
        return courierList.add(courier);
    }
    
    public List<Courier> getCourierList(){
        return new ArrayList<>(courierList);
    }
    
    public int insertCourierBatchOp() throws SQLException {
        final int numRows = courierDB.saveCourier(courierList);
        courierList.clear();
        return numRows;
    }
}
