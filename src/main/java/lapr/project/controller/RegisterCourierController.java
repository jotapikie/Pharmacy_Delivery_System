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
import lapr.project.data.PhamarcyDB;
import lapr.project.model.Courier;
import lapr.project.model.Phamarcy;

/**
 *
 * @author Helder
 */
public class RegisterCourierController {
    private Courier courier; 

    private final CourierDB courierDB; 
    
    
    private final List<Courier> couriersList;
    
    private String administratorEmail;
    private PhamarcyDB pdb;

    public RegisterCourierController(String administratorEmail) { 
        courierDB = new CourierDB(); 
        couriersList = new ArrayList<>();
        this.administratorEmail = administratorEmail;
        this.pdb = new PhamarcyDB();
    }

    public RegisterCourierController(CourierDB courierDB, String administratorEmail) { 
        this.courierDB = courierDB; 
        couriersList = new ArrayList<>();
        this.administratorEmail = administratorEmail;
        this.pdb = new PhamarcyDB();
    }

   
    public String newCourier(String name, String email, String password, int nif, int nss, double maxWeight) {
        courier = courierDB.newCourier(name,email,password, nif, nss, maxWeight);
        addCourierToQueue();
        return courier.toString();
    }


    private boolean addCourierToQueue(){
        return couriersList.add(courier);
    }
    
    
    public int registCouriers() throws SQLException {
        Phamarcy pha = pdb.getPharmacyByAdministrator(administratorEmail);
        final int numRows = courierDB.saveCouriers(couriersList, pha.getId());
        couriersList.clear();
        return numRows;
    }
    
}
