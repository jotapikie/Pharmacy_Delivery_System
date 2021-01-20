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
    public EScooter(int id, State state, int maxBat, int actualBat) {
        setId(id);
        setState(state);
        setMaxBat(maxBat);
        setActualBat(actualBat);
    }

    public double getFrontalArea() {
        return frontalArea;
    }

    public  double getAeroCoef() {
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
