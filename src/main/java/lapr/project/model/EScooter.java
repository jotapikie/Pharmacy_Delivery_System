package lapr.project.model;

import lapr.project.utils.Constants;

/**
 *
 * @author jota
 */
public class EScooter extends Vehicle{
    
    private static final double weight = Constants.SCOOTER_WEIGHT;
    private static final int motor = Constants.SCOOTER_MOTOR;
    private static final double aeroCoef = Constants.SCOOTER_AERO_COEF;
    private static final double frontalArea = Constants.SCOOTER_FRONTAL_AREA;


    /**
     * Empty Constructor of the instance EScooter
     */
    public EScooter() {
    }

    /**
     * Constructor of the instance eScooter
     * @param id
     * @param state
     * @param maxBat
     * @param actualBat
     */
    public EScooter(int id, State state, double maxBat, double actualBat) {
        super(id, weight, state, maxBat, actualBat, motor, Constants.SCOOTER_MAX_WEIGHT);
        setState(state);
    }

    public static double getFrontalArea() {
        return frontalArea;
    }

    public static double getAeroCoef() {
        return aeroCoef;
    }
    
    public void updateScooterData(State state, int maxBat, int currentBat) {
        setState(state);
        setMaxBat(maxBat);
        setCurrentBat(currentBat);
    }
    
    
    @Override
    public String toString() {
        return String.format("Category: Scooter - %s", super.toString());
    }


}
