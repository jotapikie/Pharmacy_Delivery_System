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
    private static final double CREDITS_PER_EURO = 15;
    private static final double CREDITS_WON_PER_EURO_SPENT = 0.2;
    private static final double MAX_WEIGHT_PER_ORDER = 5;
    
    
    public static double getDeliveryPrice() {
        return DELIVERY_PRICE;
    }
    
    public static double getCreditsPerEuro(){
        return CREDITS_PER_EURO;
    }
    
    public static double getCreditsWonPerEuroSpent(){
        return CREDITS_WON_PER_EURO_SPENT;
    }

    public static double getMaxWeightPerOrder() {
        return MAX_WEIGHT_PER_ORDER;
    }
    
    
    
    
    



    
}
