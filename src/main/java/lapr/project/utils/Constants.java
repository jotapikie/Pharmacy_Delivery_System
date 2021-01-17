/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.sql.Timestamp;

/**
 *
 * @author Diogo
 */
public class Constants {
    
    public static final int DEFAULT_ID = 1;
    public static final double DEFAULT_PRICE = 0;
    public static final Timestamp DEFAULT_DATE = new Timestamp(1);
    public static final String DEFAULT_STATUS= "Processing";
    
    public static final double SCOOTER_WEIGHT = 38.9;
    public static final int SCOOTER_MOTOR = 300;
    public static final double SCOOTER_AERO_COEF = 0.7;
    public static final double SCOOTER_FRONTAL_AREA = 0.65;
    

    public static final int INITIAL_POINTS = 0;
    public static double GRAVITY = 9.8;

    /**
     * Test purposes
     */
    protected Constants() {
    }
    
    
    
}
