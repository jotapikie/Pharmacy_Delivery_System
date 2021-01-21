package lapr.project.controller;

import lapr.project.data.PharmacyDB;
import lapr.project.model.Address;
import lapr.project.model.Park;
import lapr.project.model.Pharmacy;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import lapr.project.data.AddressDB;
import lapr.project.data.AdministratorDB;
import lapr.project.data.ParkDB;

import lapr.project.model.Administrator;


public class NewPharmacyController {

    private final PharmacyDB pharmacyDB;
    private final AddressDB adb;
    private final AdministratorDB adminDB;
    private final ParkDB parkDB;
    private Address add;
    private Administrator admin;
    private Pharmacy pha;
    private Set<Park> parks;
    private Set<Pharmacy> pharmaciesList;

    public NewPharmacyController(PharmacyDB pharmacyDB, AddressDB adb, AdministratorDB adminDB, ParkDB parkDB) {
        this.pharmacyDB = pharmacyDB;
        this.adb = adb;
        this.adminDB = adminDB;
        this.parkDB = parkDB;
        parks = new HashSet<>();
        pharmaciesList = new HashSet<>();
    }
    
    public String newAddress(String street, double longitude, double latitude, double elevation, String city, String zip, int port){
        add = adb.newAdress(street, longitude, latitude, elevation, city, port, zip);
        return add.toString();
    }
    
    public String newAdministrator(String name, String email, String pwd){
        admin = adminDB.newAdministrator(name, email, pwd);
        return admin.toString();
    }
    
    public String newPark(String vehicleCategory,int maxVehicles,int ableToCharge, double doubleMaxEnergy){
        parks.add(parkDB.newPark(maxVehicles,ableToCharge, vehicleCategory, doubleMaxEnergy));
        return parks.toString();
    }
    
    public String newPharmacy(String name, int phoneNumber){
        pha = pharmacyDB.newPhamarcy(phoneNumber, phoneNumber, name, admin, add, parks);
        return pha.toString();
    }
    
    public boolean addToQueue(){
        if(pha != null){
            return pharmaciesList.add(pha);
        }
        return false;
    }
    
    public int registPharmacies() throws SQLException{
        if(!pharmaciesList.isEmpty()){
            return pharmacyDB.save(pharmaciesList);
        }
        return 0;
    }
    
 


}
