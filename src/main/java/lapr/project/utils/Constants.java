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
    public static final double AVERAGE_COURIER_WEIGHT = 80;
    
    public static final double SCOOTER_WEIGHT = 50;
    public static final int SCOOTER_MOTOR = 3;
    public static final double SCOOTER_AERO_COEF = 1.1;
    public static final double SCOOTER_FRONTAL_AREA = 0.3;
    public static final double SCOOTER_MAX_WEIGHT = 20;
    public static final double SCOOTER_MAX_BATTERY = 2.8;
    public static final double DRONE_BATTERY_VOLTAGE = 12;
    public static double SCOOTER_SPEED = 30;
    

    public static final int INITIAL_POINTS = 0;
    public static final double RENDIMENTO_DRONE = 0.7;
    public static double GRAVITY = 9.8;


    public static final double KINETIC_COEF_ASPHALT= 0.5;
    public static final double KINETIC_COEF_PARALELO= 0.2;
    public static final double KINETIC_COEF_OFF_ROAD= 0.2;
    public static final double KINETIC_COEF_SIDEWALK= 0.3;

    public static final double RO_AR= 1.225;
    public static final double DRAG_COEF= 1.5;

    public static final int MAX_PAYLOAD = 10;


    
    public static String CHARGING_SPOT = "Pharmacy";
    
    public static double DRONE_AERO_COEF = 0.5;
    public static double DRONE_ELETRONICAL_CONSUME = 2000;
    public static double DRONE_AVERAGE_SPEED = 15;
    public static double DRONE_POWER_TRANSFER = 2;
    public static double DRONE_LIFT_DRAG = 1;
    public static double DRONE_FRONTAL_AREA = 1;
    public static double DRONE_TOP_AREA = 1;
    public static double DRONE_WEIGHT =3.3;
    public static double DRONE_MAX_WEIGHT = 7;
    public static int    DRONE_MOTOR = 12;
    public static double DRONE_MAX_BATTERY = 15000;
    public static double DRONE_HORIZONTAL_SPEED = 16;
    public static double DRONE_VERTICAL_SPEED = 6;
    
    public static double DRONE_ALTITUDE = 100;
    public static double MAX_DISTANCE = 30000;
    

    /**
     * Test purposes
     */
    protected Constants() {
    }
    
    
    
}
