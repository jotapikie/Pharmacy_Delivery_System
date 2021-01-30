/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.comparator;

import java.util.Comparator;
import lapr.project.utils.route.Route;

/**
 *
 * @author Diogo
 */
public class EnergyComparator implements Comparator<Route>{
    
    @Override
    public int compare(Route o1, Route o2) {
        int energy = Double.compare(o1.getTotalEnergy(), o2.getTotalEnergy());
        if(energy != 0){
            return energy;
        }else{
            int distance = Double.compare(o1.getTotalDistance(), o2.getTotalDistance());
            if(distance != 0){
                    return distance;
            }else{
                int stops = o1.getChargingPoints().size() - o2.getChargingPoints().size();
                if(stops != 0){
                        return stops;
                }else{
                    return (o1.getNumGeographicalPoints() - o2.getNumGeographicalPoints());
                }
            }
        }
    }
}
