/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.SQLException;
import lapr.project.controller.MakeOrderController;

/**
 *
 * @author Diogo
 */
public class teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        MakeOrderController controller = new MakeOrderController(new ClientDB(), new CartProductDB(), new OrderDB(), new InvoiceDB(), new PharmacyStockDB(), new PharmacyDB(), "clien1@lapr3.com");
        System.out.println(controller.getCart());
        System.out.println(controller.getAddress());
        System.out.println(controller.getDefaultNif());
        System.out.println(controller.getCredits());
        System.out.println(controller.getFinalPrice());
        controller.makeOrder(123456789);
        

    }
    
}
