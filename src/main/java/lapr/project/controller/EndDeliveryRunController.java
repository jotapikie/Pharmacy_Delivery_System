package lapr.project.controller;

import lapr.project.data.DeliveryRunDB;
import lapr.project.data.ParkDB;
import lapr.project.data.ParkSlotDB;
import lapr.project.data.VehicleDB;
import lapr.project.model.EScooter;
import lapr.project.model.ParkSlot;
import lapr.project.model.Vehicle;

import java.sql.SQLException;
import java.util.HashSet;

public class EndDeliveryRunController {

    private final DeliveryRunDB drdb;
    private final ParkSlotDB psdb;
    private final VehicleDB vdb;


    public EndDeliveryRunController(){
        this.drdb= new DeliveryRunDB();
        this.psdb= new ParkSlotDB();
        this.vdb = new VehicleDB();
    }


    public String setEndDate(int scooterID) throws SQLException{
        return drdb.endDeliveryRun(scooterID);
    }

    public boolean lockScooter(int scooterID, int parkID, int slotID) throws SQLException {
        HashSet<ParkSlot> slots= new HashSet<>();
        slots= (HashSet<ParkSlot>) psdb.getParkSlotsByPark(parkID);
        Vehicle scooter= vdb.getVehicle(scooterID);
        for (ParkSlot p: slots){
            if (p.getId()==slotID){
                p.setVehicle(scooter);
            }
        }
        psdb.saveParkSlots(slots, parkID);
        return true;
    }
}
