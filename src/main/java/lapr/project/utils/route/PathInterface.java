/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.route;

import lapr.project.model.GeographicalPoint;

/**
 *
 * @author Diogo
 */
public interface PathInterface {

    public GeographicalPoint getOriginPoint();
    public GeographicalPoint getDestinationPoint();
    public double getDistance();
    public double getCost();
    public double getElevationDif();
    
    
}
