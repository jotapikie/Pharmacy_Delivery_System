/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import lapr.project.controller.RegisterCourierController;
import lapr.project.data.CourierDB;

/**
 *
 * @author Helder
 */
class RegisterCourierUI {
    
    private RegisterCourierController controller;

    public RegisterCourierUI(int pharmacyID) throws SQLException {
        controller = new RegisterCourierController(new CourierDB(), pharmacyID);
        controller.newCourier("Francisco","diogo@gmail.com","1234", 454654,32425,200);
        controller.addToQueue();
        controller.registCouriers();
    }
    
    
}
