/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import lapr.project.model.Platform;

/**
 *
 * @author Diogo
 */
public class Utils {
    private static Platform platform;
    
    public static double creditsToEuros(int credits){
        return credits/platform.getCreditsPerEuro();
    }
    
    public static int creditsWon(double price){
        return (int) (price*platform.getCreditsWonPerEuroSpent());
    }
}
