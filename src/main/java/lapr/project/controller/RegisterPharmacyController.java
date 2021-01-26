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
import lapr.project.utils.Constants;


public class RegisterPharmacyController {

    private final PharmacyDB pharmacyDB;
    private final AddressDB adb;
    private final AdministratorDB adminDB;
    private final ParkDB parkDB;
    private Address add;
    private Administrator admin;
    private Pharmacy pha;
    private final Set<Park> parks;
    private final Set<Pharmacy> pharmaciesList;

    public RegisterPharmacyController(PharmacyDB pharmacyDB, AddressDB adb, AdministratorDB adminDB, ParkDB parkDB) {
        this.pharmacyDB = pharmacyDB;
        this.adb = adb;
        this.adminDB = adminDB;
        this.parkDB = parkDB;
        this.parks = new HashSet<>();
        this.pharmaciesList = new HashSet<>();
    }

    public RegisterPharmacyController() {
        this.pharmacyDB = new PharmacyDB();
        this.adb = new AddressDB();
        this.adminDB = new AdministratorDB();
        this.parkDB = new ParkDB();
        this.parks = new HashSet<>();
        this.pharmaciesList = new HashSet<>();
    }
    
    
    
    public String newAddress(String street, double longitude, double latitude, double elevation, String city, String zip, int port, String desc){
        add = adb.newAdress(street, longitude, latitude, elevation, city, port, zip, desc);
        return add.toString();
    }
    
    public String newAdministrator(String name, String email, String pwd){
        admin = adminDB.newAdministrator(name, email, pwd);
        return admin.toString();
    }
    
    public String newPark(String vehicleCategory,int maxVehicles,int ableToCharge, double doubleMaxEnergy){
        parks.add(parkDB.newPark(Constants.DEFAULT_ID,maxVehicles,ableToCharge, vehicleCategory, doubleMaxEnergy));
        return parks.iterator().next().toString();
    }
    
    public String newPharmacy(String name, int phoneNumber){
        pha = pharmacyDB.newPhamarcy(Constants.DEFAULT_ID+pharmaciesList.size(), phoneNumber, name, admin, add, parks);
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
