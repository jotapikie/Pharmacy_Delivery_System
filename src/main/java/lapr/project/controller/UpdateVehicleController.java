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


    public EScooter updateScooterData(int id,  State state, int maxBat, int currentBat) throws SQLException {
        EScooter eScooter = (EScooter) vehicleDB.getVehicle(id);
        eScooter.updateScooterData(state,maxBat,currentBat);
        vehicle = eScooter;
        return eScooter;
    }

    public EScooter updateScooterState(int id, State state) throws SQLException {
        EScooter eScooter = (EScooter) vehicleDB.getVehicle(id);
        eScooter.setState(state);
        vehicle = eScooter;
        return eScooter;
    }

    public boolean update() throws SQLException {
        return vehicleDB.update(vehicle);
    }

}
