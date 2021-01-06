package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.data.VehicleDB;
import lapr.project.model.EScooter;
import lapr.project.model.State;
import lapr.project.model.Vehicle;

public class UpdateVehicleController {

    private final VehicleDB vehicleDB;
    private Vehicle vehicle;

    public UpdateVehicleController(VehicleDB vehicleDB) {
        this.vehicleDB = vehicleDB;
    }

    public UpdateVehicleController() {
        this.vehicleDB = new VehicleDB();
    }


    public EScooter updateScooterData(int id, int weight, State state, int maxBat, int motor, double aeroCoef, double frontalArea) throws SQLException {
        EScooter eScooter = (EScooter) vehicleDB.getVehicle(id);
        eScooter.updateScooterData(weight,aeroCoef,frontalArea,state,maxBat,motor);
        vehicle = eScooter;
        return eScooter;
    }

    public boolean update() throws SQLException {
        return vehicleDB.update(vehicle);
    }

}
