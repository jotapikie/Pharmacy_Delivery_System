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
public class RegisterCourierController {
    private Courier courier; 

    private final CourierDB courierDB; 
    
    private final List<Courier> couriersList;

    public RegisterCourierController() { 
        courierDB = new CourierDB(); 
        couriersList = new ArrayList<>(); }

    public RegisterCourierController(CourierDB courierDB) { 
        this.courierDB = courierDB; 
        couriersList = new ArrayList<>(); }

   
    public Courier newCourier(double maxWeight, String name, String email, String password) {
        courier = courierDB.newCourier(maxWeight,name,email,password);
        return courier;
    }

    /*
     * Saves the client known by the controller in our database.
     */
    public boolean registerCourier() throws SQLException {
        return courierDB.saveCourier(courier);
    }

    public boolean addCourierToQueue(){
        return couriersList.add(courier);
    }
    
    public List<Courier> getCouriersList(){
        return new ArrayList<>(couriersList);
    }
    
    public int insertCouriersBatchOp() throws SQLException {
        final int numRows = courierDB.saveCouriers(couriersList);
        couriersList.clear();
        return numRows;
    }
    
}
