package lapr.project.model;

import lapr.project.utils.Constants;

/**
 *
 * @author jota
 */
public class EScooter extends Vehicle{
    
    private static double weight = Constants.SCOOTER_WEIGHT;
    private static int motor = Constants.SCOOTER_MOTOR;
    private static double aeroCoef = Constants.SCOOTER_AERO_COEF;
    private static double frontalArea = Constants.SCOOTER_FRONTAL_AREA;


    /**
     * Empty Constructor of the instance EScooter
     */
    public EScooter() {
    }

    /**
     * Constructor of the instance eScooter
     * @param id
     * @param weight
     * @param aeroCoef
     * @param frontalArea
     * @param state
     * @param maxBat
     * @param actualBat
     * @param motor 
     */
    public EScooter(int id, State state, int maxBat, int actualBat) {
        super(id, weight, state, maxBat, actualBat, motor);
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
        setActualBat(currentBat);
    }
    
    
    @Override
    public String toString() {
        return "Scooter{"
                + super.toString() +
                ", aerodinamicCoef=" + aeroCoef +
                ", frontal Area=" + frontalArea +
                "} " ;
    }


}
