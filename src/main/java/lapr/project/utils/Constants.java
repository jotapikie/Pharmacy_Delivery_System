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
    public static final double DEFAULT_PRICE = 1;
    public static final Timestamp DEFAULT_DATE = new Timestamp(1);
    public static final String DEFAULT_STATUS= "Processing";
    public static final double AVERAGE_COURIER_WEIGHT = 75;
    public static final int DEFAULT_SCOOTER_MAX_BAT = 10;
    
    public static final double SCOOTER_WEIGHT = 38.9;
    public static final int SCOOTER_MOTOR = 300;
    public static final double SCOOTER_AERO_COEF = 0.7;
    public static final double SCOOTER_FRONTAL_AREA = 0.65;
    

    public static final int INITIAL_POINTS = 0;
    public static final double MAX_DISTANCE = 3 ;
    public static final double RENDIMENTO_DRONE = 0.7;
    public static double GRAVITY = 9.8;


    public static final double KINETIC_COEF_ASFALTO= 0.2;
    public static final double KINETIC_COEF_PARALELO= 0.2;
    public static final double KINETIC_COEF_TERRA_BATIDA= 0.2;
    public static final double KINETIC_COEF_CALCADA= 0.3;

    public static final double RO_AR= 1.225;
    public static final double DRAG_COEF= 1.5;

    public static final int MAX_PAYLOAD = 10;


    
    public static String CHARGING_SPOT = "Pharmacy";
    
    public static double DRONE_AERO_COEF = 0.5;
    public static double DRONE_ELETRONICAL_CONSUME = 1;
    public static double DRONE_AVERAGE_SPEED = 15;
    public static double DRONE_POWER_TRANSFER = 2;
    public static double DRONE_LIFT_DRAG = 1;
    public static double DRONE_FRONTAL_AREA = 1;
    public static double DRONE_TOP_AREA = 1;
    public static double DRONE_WEIGHT =7;
    
    public static double DRONE_ALTITUDE = 150;

    /**
     * Test purposes
     */
    protected Constants() {
    }
    
    
    
}
