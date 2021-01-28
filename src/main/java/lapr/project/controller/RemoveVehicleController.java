package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lapr.project.data.VehicleDB;
import lapr.project.model.Vehicle;
import lapr.project.utils.Utils;


public class RemoveVehicleController {

    private final VehicleDB vehicleDB;
    private final int idPharmacy;
    private List<Vehicle> vehicles;
    private Vehicle vehicle;

    public RemoveVehicleController(VehicleDB vehicleDB, int idPharmacy) {
        this.vehicleDB = vehicleDB;
        this.idPharmacy = idPharmacy;
        this.vehicles = new ArrayList<>();
    }

    public RemoveVehicleController(int idPharmacy) {
        this.vehicleDB = new VehicleDB();
        this.idPharmacy = idPharmacy;
        this.vehicles = new ArrayList<>();
    }
    
    public List<String> getVehicles() throws SQLException{
        vehicles = vehicleDB.getVehicles(idPharmacy);
        return Utils.listToString(vehicles);
    }
    
    public String selectVehicle(int id){
        for(Vehicle v : vehicles){
            if(v.getId() == id){
                vehicle = v;
                return vehicle.toString();
            }
        }
        return null;
    }
    
    public boolean remove() throws SQLException {
        if(vehicle != null){
            return vehicleDB.remove(vehicle.getId(), idPharmacy);
        }
        return false;
    }

}