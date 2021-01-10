/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author Diogo
 */
public class Platform {
    
    private static final double DELIVERY_PRICE = 3.5;
    
    

    public double getDeliveryPrice() {
        return DELIVERY_PRICE;
    }

    public double getDiscountedPrice(int cred, double totalPrice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void sendEmail(String email, String toString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
