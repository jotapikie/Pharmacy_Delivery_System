/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;
import lapr.project.utils.route.PathInterface;



/**
 *
 * @author Diogo
 */
public class Pathway implements PathInterface{
    
    private GeographicalPoint originPoint;
    private GeographicalPoint destinationPoint;
    private double kineticCoef;
    private double distance;
    private double wind;
    
    
     public Pathway(GeographicalPoint origLocation, GeographicalPoint destLocation, double kinetic_coef, double distance, double wind) {
        setOriginPoint(origLocation);
        setDestinationPoint(destLocation);
        setKineticCoef(kinetic_coef);
        setDistance(distance);
        setWind(wind);

    }

    /**
     * Returns origin vertex of the edge.
     *
     * @return origin vertex.
     */
    @Override
    public GeographicalPoint getOriginPoint() {
        return originPoint;
    }

    /**
     * Returns destination vertex of the edge.
     *
     * @return destination vertex.
     */
    @Override
    public GeographicalPoint getDestinationPoint() {
        return destinationPoint;
    }

    /**
     * Returns the distance of the edge, in meters.
     *
     * @return distance of the edge, in meters.
     */
    @Override
    public double getDistance() {
        return distance;
    }

    /**
     * Returns the cost of the edge.
     *
     * @return cost of the edge.
     */
    @Override
    public double getCost() {
        return distance;
    }

    /**
     * method to get the kinetic coefficient of the path
     *
     * @return kinetic coef
     */
    public double getKineticCoef() {
        return kineticCoef;
    }

    /**
     * method to get the wind factor in a path
     *
     * @return
     */
    public double getWind() {
        return wind;
    }

    /**
     * Returns the elevation difference of the edge, in meters.
     *
     * @return elevation difference of the edge, in meters.
     */
    @Override
    public double getElevationDif(){
        return destinationPoint.getElevation() - originPoint.getElevation();
    }

    /**
     * method to set the final location of a path
     *
     * @param origPoint
     */
    public final void setOriginPoint(GeographicalPoint origPoint) {
        if (origPoint == null) {
            throw new IllegalArgumentException("Invalid origin location");
        }
        this.originPoint = origPoint;
    }

    /**
     * method to set the final location of a path
     *
     * @param destPoint
     */
    public final void setDestinationPoint(GeographicalPoint destPoint) {
        if (destPoint == null) {
            throw new IllegalArgumentException("Invalid origin location");
        }
        this.destinationPoint = destPoint;
    }

    /**
     * method to set the distance between two locations in a path.
     *
     * @param distance
     */
    public final void setDistance(double distance) {
        if (distance <= 0) {
            throw new IllegalArgumentException("Invalid distance");
        }
        this.distance = distance;
    }

    /**
     * method to set the kinetic coefficient of the path
     *
     * @param kinetic_coef
     */
    public final void setKineticCoef(double kinetic_coef) {
        if (kinetic_coef < 0) {
            throw new IllegalArgumentException("Invalid kinetic coefficient");
        }
        this.kineticCoef = kinetic_coef;
    }

    /**
     * method to set the wind in a path
     *
     * @param wind
     */
    public final void setWind(double wind) {
        this.wind = wind;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.originPoint);
        hash = 47 * hash + Objects.hashCode(this.destinationPoint);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pathway other = (Pathway) obj;
        if (Double.doubleToLongBits(this.kineticCoef) != Double.doubleToLongBits(other.kineticCoef)) {
            return false;
        }
        if (Double.doubleToLongBits(this.wind) != Double.doubleToLongBits(other.wind)) {
            return false;
        }
        if (this.distance != other.distance) {
            return false;
        }
        if (!Objects.equals(this.originPoint, other.originPoint)) {
            return false;
        }
        return Objects.equals(this.destinationPoint, other.destinationPoint);
    }
    
    
    
    
    
    










    
}
