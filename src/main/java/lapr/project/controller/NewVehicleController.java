package lapr.project.controller;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import lapr.project.data.VehicleDB;
import lapr.project.model.Drone;
import lapr.project.model.EScooter;
import lapr.project.model.State;
import lapr.project.model.Vehicle;

public class NewVehicleController {

    private final VehicleDB vehicleDB;
    private Vehicle vehicle;
    private HashSet<Vehicle> vehicles = new HashSet<>();
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

    public EScooter newEScooter(int id,int maxBat, int actualBat) {
        vehicle = vehicleDB.newEScooter(id,State.INACTIVE,maxBat,actualBat);
        return (EScooter) vehicle;
    }

    public Drone newDrone(int id, int weight, int maxBat, int actualBat, int motor) {
        vehicle = vehicleDB.newDrone(id,weight,State.INACTIVE,maxBat,actualBat,motor);
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
