package lapr.project.controller;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import lapr.project.data.VehicleDB;
import lapr.project.model.Drone;
import lapr.project.model.EScooter;
import lapr.project.model.State;
import lapr.project.model.Vehicle;
import lapr.project.utils.Constants;

public class NewVehicleController {

    private final VehicleDB vehicleDB;
    private Vehicle vehicle;
    private Set<Vehicle> vehicles = new HashSet<>();
    private int pharmID;

    /**
     * Constructor of the controller class
     *
     * @param vehicleDB
     */
    public NewVehicleController(VehicleDB vehicleDB, int pharmID) {
        this.vehicleDB = vehicleDB;
        this.pharmID=pharmID;
    }

    /**
     * Constructor of the controller class
     */
    public NewVehicleController() {
        this.vehicleDB = new VehicleDB();
    }

    public EScooter newEScooter(int id,int maxBat) {
        vehicle = vehicleDB.newEScooter(Constants.DEFAULT_ID+vehicles.size(),State.LOCKED,maxBat,maxBat);
        return (EScooter) vehicle;
    }

    public Drone newDrone(int id,int maxBat) {
        vehicle = vehicleDB.newDrone(Constants.DEFAULT_ID+vehicles.size(),State.LOCKED,maxBat,maxBat);
        return (Drone) vehicle;
    }
    public boolean registerVehicle() throws SQLException {
        return vehicleDB.save(vehicle, pharmID);
    }

    public void addVehicleToQueue() {
        vehicles.add(vehicle);
    }

    public Set<Vehicle> getVehicles() {
        return new HashSet<>(vehicles);
    }

    public int insertVehiclesBatchOp() throws SQLException {
        if(vehicles.isEmpty()){
            return vehicleDB.save(vehicles, pharmID);
        }
        return 0;
    }
}
