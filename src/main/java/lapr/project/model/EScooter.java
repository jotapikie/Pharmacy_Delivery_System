package lapr.project.model;

import lapr.project.utils.Constants;

/**
 *
 * @author jota
 */
public class EScooter extends Vehicle{
    
    private double aeroCoef;
    private double frontalArea;
    
    private static double sweight = Constants.SCOOTER_WEIGHT;
    private static double saeroCoef = Constants.SCOOTER_AERO_COEF;

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
    public EScooter(int id, double weight, State state, int maxBat, int actualBat, int motor, double aeroCoef, double frontalArea) {
        super(id, weight, state, maxBat, actualBat, motor);
        setAeroCoef(aeroCoef);
        setFrontalArea(frontalArea);
    }

    public static double getSweight() {
        return sweight;
    }

    public static double getSaeroCoef() {
        return saeroCoef;
    }

    



    /**
     * returns the aerodinamic coeficient of a scooter
     *
     * @return
     */
    public double getAeroCoef() {
        return aeroCoef;
    }

    /**
     * sets the aeroCoef of a scooter
     *
     * @param aeroCoef
     */
    public void setAeroCoef(double aeroCoef) {
        if (aeroCoef <= 0) throw new IllegalArgumentException("Illegal aero coeficent");
        this.aeroCoef = aeroCoef;
    }

    /**
     * returns the frontal area of a scooter
     *
     * @return
     */
    public double getFrontalArea() {
        return frontalArea;
    }

    /**
     * sets the frontal area of a scooter
     *
     * @param frontalArea
     */
    public void setFrontalArea(double frontalArea) {
        if (frontalArea <= 0) {

            throw new IllegalArgumentException("Frontal area cannot be negative");
        }
        this.frontalArea = frontalArea;
    }

    public void updateScooterData(double weight, double aeroCoef, double frontalArea, State state, int maxBat, int motor) {
        setAeroCoef(aeroCoef);
        setFrontalArea(frontalArea);
        super.setWeight(weight);
        super.setState(state);
        super.setMaxBat(maxBat);
        super.setMotor(motor);
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
