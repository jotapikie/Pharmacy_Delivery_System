package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.data.VehicleDB;

public class RemoveVehicleController {

    private final VehicleDB vehicleDB;

    public RemoveVehicleController(VehicleDB vehicleDB) {
        this.vehicleDB = vehicleDB;
    }

    public RemoveVehicleController() {
        this.vehicleDB = new VehicleDB();
    }

    public boolean remove(int id) throws SQLException {
        return vehicleDB.remove(id);
    }

}