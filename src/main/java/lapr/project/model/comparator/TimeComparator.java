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
public class TimeComparator implements Comparator<Route>{

    @Override
    public int compare(Route o1, Route o2) {
        return (int) (o1.getTotalTime() - o2.getTotalTime());
    }


    
}
