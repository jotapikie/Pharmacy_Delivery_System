/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import lapr.project.controller.RemoveVehicleController;
import lapr.project.data.VehicleDB;

/**
 *
 * @author Helder
 */
public class RemoveVehicleUI {

    private RemoveVehicleController controller;
    
    public RemoveVehicleUI() throws SQLException {
        controller=new RemoveVehicleController(new VehicleDB());
        controller.remove(1);
    }
    
    
}
