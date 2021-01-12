/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Platform;

/**
 *
 * @author Diogo
 */
public class Utils {
    private static final Platform platform = new Platform();
    
    public static double creditsToEuros(int credits){
        return credits/platform.getCreditsPerEuro();
    }
    
    public static int creditsWon(double price){
        return (int) (price*platform.getCreditsWonPerEuroSpent());
    }
    
     /**
     * Calculates the permutations of a given list.
     *
     * @param toPermute list to permute.
     * @return list of permutations.
     */
    public static <V> List<List<V>> permutations(List<V> toPermute) {
        if (toPermute == null) {
            return null;
        }
        List<List<V>> result = new ArrayList<>();
        if (!toPermute.isEmpty()) {
            permutations(toPermute, new ArrayList<>(), result);
        }
        return result;
    }

    /**
     * Calculates the permutations of a given list.
     *
     * @param toPermute list to permute.
     * @param temp temporary list.
     * @param permutations list of permutations.
     */
    private static <V> void permutations(List<V> toPermute, List<V> temp, List<List<V>> permutations) {
        if (temp.size() == toPermute.size()) {
            permutations.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < toPermute.size(); i++) {
            if (!temp.contains(toPermute.get(i))) {
                temp.add(toPermute.get(i));
                permutations(toPermute, temp, permutations);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
