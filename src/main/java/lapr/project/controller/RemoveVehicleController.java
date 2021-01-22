package lapr.project.controller;

import java.sql.SQLException;

import lapr.project.data.VehicleDB;


public class RemoveVehicleController {

    private final VehicleDB vehicleDB;
    private final int idPharmacy;

    public RemoveVehicleController(VehicleDB vehicleDB, int idPharmacy) {
        this.vehicleDB = vehicleDB;
        this.idPharmacy = idPharmacy;
    }

    public RemoveVehicleController(int idPharmacy) {
        this.vehicleDB = new VehicleDB();
        this.idPharmacy = idPharmacy;
    }
    
 

    public boolean remove(int id) throws SQLException {
        return vehicleDB.remove(id, idPharmacy);
    }

}