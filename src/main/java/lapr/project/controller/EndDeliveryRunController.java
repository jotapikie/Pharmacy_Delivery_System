package lapr.project.controller;

import lapr.project.data.DeliveryRunDB;

import java.sql.SQLException;

public class EndDeliveryRunController {

    private final DeliveryRunDB drdb;

    public EndDeliveryRunController(){
        this.drdb= new DeliveryRunDB();
    }

    public String getScooterCourierByDeliveryRun(int scooterID) throws SQLException {
        return drdb.getCourierEmail(scooterID);
    }

    public boolean setEndDate(int scooterID) throws SQLException{
        return drdb.endDeliveryRun(scooterID);
    }
}
