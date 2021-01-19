/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import lapr.project.data.GeographicalPointDB;
import lapr.project.model.GeographicalPoint;
import lapr.project.model.LandGraph;
import lapr.project.model.Pathway;
import lapr.project.utils.route.Route;

/**
 *
 * @author Diogo
 */
public class TestUCs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
//        AddToCartUI addToCart = new AddToCartUI("clien1@lapr3.com"); // FUNCIONA
//       MakeOrderUI makeOrder = new MakeOrderUI("clien1@lapr3.com"); // FUNCIONA
       StartDeliveryRunUI startDelivery = new StartDeliveryRunUI(1, 67.5, "courier1@lapr3.com"); // FUNCIONA
//        UpdateStockUI updateStock = new UpdateStockUI(1); // FUNCIONA
//        PrepareOrderUI prepareOrder = new PrepareOrderUI(1); // FUNCIONA
//        NotifyReadyOrderUI preparedOrder = new NotifyReadyOrderUI(1); //FUNCIONA
//        RegisterCourierUI registCourier  = new RegisterCourierUI(1);//FUNCIONA
//        RemoveVehicleUI removeVehicle =new RemoveVehicleUI(); // FUNCIONA
//        RegisterClientUI registClient = new RegisterClientUI(); // FUNCIONA
//        RegisterProductUI registProduct = new RegisterProductUI(); // FUNCIONA



          

    }
    
}
