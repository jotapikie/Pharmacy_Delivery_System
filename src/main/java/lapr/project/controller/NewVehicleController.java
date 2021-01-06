package lapr.project.controller;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import lapr.project.data.VehicleDB;
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

    public EScooter newEScooter(int id, int weight, State state, int maxBat, int actualBat, int motor, double aeroCoef, double frontalArea) {
        vehicle = vehicleDB.newEScooter(id,weight,state,maxBat,actualBat,motor,aeroCoef,frontalArea);
        return (EScooter) vehicle;
    }

    public boolean registerVehicle() throws SQLException {
        return vehicleDB.save(vehicle, pharmID);
    }

    public void addVehicleToQueue() {
        vehicles.add(vehicle);
    }

    public HashSet<Vehicle> getVehicles() {
        return new HashSet<>(vehicles);
    }

    public int insertVehiclesBatchOp() throws SQLException {
        final int i = vehicleDB.save(vehicles, pharmID);
        vehicles = new HashSet<>();
        return i;
    }
}
