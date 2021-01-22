/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import lapr.project.data.CourierDB;
import lapr.project.model.Courier;

/**
 *
 * @author Helder
 */
public class RegisterCourierController {
    
    private Courier courier; 
    private final CourierDB courierDB; 
    private final Set<Courier> couriersList;
    private final int idPharmacy;

    public RegisterCourierController( CourierDB courierDB, int idPharmacy) {
        this.courierDB = courierDB;
        this.couriersList = new HashSet<>();
        this.idPharmacy = idPharmacy;
    }
    
    public String newCourier(String name, String email, String password, int nif, int nss, double weight) {
        courier = courierDB.newCourier(name,email,password, nif, nss, weight);
        return courier.toString();
    }
    
    public boolean addToQueue(){
        if(courier != null){
            return couriersList.add(courier);
        }
        return false;
    }

    public int registCouriers() throws SQLException {
        if(!couriersList.isEmpty()){
            return courierDB.saveCouriers(couriersList, idPharmacy);
        }
        return 0;
    }


    
    
    
}
