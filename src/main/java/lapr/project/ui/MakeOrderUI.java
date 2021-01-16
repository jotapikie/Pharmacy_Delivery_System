/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.controller.MakeOrderController;
import lapr.project.data.CartProductDB;
import lapr.project.data.ClientDB;
import lapr.project.data.InvoiceDB;
import lapr.project.data.OrderDB;
import lapr.project.data.PharmacyDB;
import lapr.project.data.PharmacyStockDB;

/**
 *
 * @author Diogo
 */
public class MakeOrderUI {
    private MakeOrderController controller;

    public MakeOrderUI(String email) {
        try {
            controller = new MakeOrderController(new ClientDB(), new CartProductDB(), new OrderDB(), new InvoiceDB(), new PharmacyStockDB(), new PharmacyDB(), email);
            showCart();
            showAddress();
            showNif();
            showCredits();
            showFinalPrice();
            makeOrder();
        } catch (SQLException ex) {
            Logger.getLogger(MakeOrderUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void showCart() throws SQLException{
        System.out.println("Products in cart:");
        System.out.println(controller.getCart());
        System.out.println();
    }
    
    private void showAddress(){
        System.out.println("Address:");
        System.out.println(controller.getAddress());
        System.out.println();
    }
    
    private void showNif(){
        System.out.println("Default Nif:");
        System.out.println(controller.getDefaultNif());
        System.out.println();
    }
    
    private void showCredits(){
        System.out.println("Credits:");
        System.out.println(controller.getCredits());
        System.out.println();
    }
    
    private void showFinalPrice(){
        System.out.println("Final Price:");
        System.out.println(controller.getFinalPrice());
        System.out.println();
    }
    
    private void makeOrder() throws SQLException{
        System.out.println("Making Order...");
        boolean res = controller.makeOrder(123456789);
        if(res){
            System.out.println("Order made succesfully");
        }else{
            System.out.println("Your couldn't be made.");
        }
        System.out.println();
    }
    
    
}
