/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.utils.Constants;

/**
 *
 * @author Helder
 */
public class Drone extends Vehicle{
    private static final double aeroCoef = Constants.DRONE_AERO_COEF;
    private static final double eletronicalConsumer = Constants.DRONE_ELETRONICAL_CONSUME;
    private static final double frontalArea = Constants.DRONE_FRONTAL_AREA;
    private static final double topArea = Constants.DRONE_TOP_AREA;
    private static final double powerTransfer = Constants.DRONE_POWER_TRANSFER;
    private static final double liftDrag = Constants.DRONE_LIFT_DRAG;

    public Drone(int id, State state, double maxBat, double actualBat) {
        super(id, aeroCoef, state, maxBat, actualBat, Constants.DRONE_MOTOR, Constants.DRONE_MAX_WEIGHT);
    }

    public static double getEletronicalConsumer() {
        return eletronicalConsumer;
    }

    public static double getFrontalArea() {
        return frontalArea;
    }

    public static double getTopArea() {
        return topArea;
    }

    public static double getPowerTransfer() {
        return powerTransfer;
    }

    public static double getLiftDrag() {
        return liftDrag;
    }

    public static double getAeroCoef() {
        return aeroCoef;
    }
    
    
    
    
    
    
    
    
    
    




    
    
    
}
