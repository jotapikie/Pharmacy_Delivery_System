package lapr.project.controller;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import lapr.project.data.VehicleDB;
import lapr.project.model.State;
import lapr.project.model.Vehicle;
import lapr.project.utils.Constants;

public class NewVehicleController {

    private final VehicleDB vehicleDB;
    private Vehicle vehicle;
    private final Set<Vehicle> vehicles;
    private final int pharmID;

    /**
     * Constructor of the controller class
     *
     * @param vehicleDB
     */
    public NewVehicleController(VehicleDB vehicleDB, int pharmID) {
        this.vehicleDB = vehicleDB;
        this.pharmID=pharmID;
        this.vehicles = new HashSet<>();
    }

    /**
     * Constructor of the controller class
     */
    public NewVehicleController(int pharmId) {
        this.vehicleDB = new VehicleDB();
        this.pharmID = pharmId;
        this.vehicles = new HashSet<>();
    }

    public String newEScooter(int maxBat) {
        vehicle = vehicleDB.newEScooter(Constants.DEFAULT_ID+vehicles.size(),State.LOCKED,maxBat,maxBat);
        return vehicle.toString();
    }

    public String newDrone(int maxBat) {
        vehicle = vehicleDB.newDrone(Constants.DEFAULT_ID+vehicles.size(),State.LOCKED,maxBat,maxBat);
        return vehicle.toString();
    }

    public boolean addVehicleToQueue() {
        if(vehicle != null){
            return vehicles.add(vehicle);
        }
        return false;
    }


    public int registerVehicles() throws SQLException {
        if(!vehicles.isEmpty()){
            return vehicleDB.save(vehicles, pharmID);
        }
        return 0;
    }
}
