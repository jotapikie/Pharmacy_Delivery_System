/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Diogo
 */
public class UtilsTest {
    
    
    Utils u = new Utils();


 

/**
     * Test of permutations method, of class Utils.
     */
    @Test
    public void testPermutations() {
        List<List<String>> result = Utils.permutations(null);
        assertNull(result);
    }

    /**
     * Test of permutations method, of class Utils.
     */
    @Test
    public void testPermutations2() {
        List<List<String>> expResult = new ArrayList<>();
        List<List<String>> result = Utils.permutations(new ArrayList<>());
        assertEquals(expResult, result);
    }

    /**
     * Test of permutations method, of class Utils.
     */
    @Test
    public void testPermutations3() {
        List<List<String>> expResult = new ArrayList<>();
        List<String> l1 = new ArrayList<>();
        l1.add("A");
        l1.add("B");
        l1.add("C");
        List<String> l2 = new ArrayList<>();
        l2.add("A");
        l2.add("C");
        l2.add("B");
        List<String> l3 = new ArrayList<>();
        l3.add("B");
        l3.add("A");
        l3.add("C");
        List<String> l4 = new ArrayList<>();
        l4.add("B");
        l4.add("C");
        l4.add("A");
        List<String> l5 = new ArrayList<>();
        l5.add("C");
        l5.add("A");
        l5.add("B");
        List<String> l6 = new ArrayList<>();
        l6.add("C");
        l6.add("B");
        l6.add("A");
        List<String> l = new ArrayList<>();
        l.add("A");
        l.add("B");
        l.add("C");
        expResult.add(l1);
        expResult.add(l2);
        expResult.add(l3);
        expResult.add(l4);
        expResult.add(l5);
        expResult.add(l6);

        List<List<String>> result = Utils.permutations(l);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDistance(){
        assertEquals(1531710.8, Utils.distance(41.2, 43.7, 42, 23.6, 200, 150), 0.1);
    }
    
    @Test
    public void testPathEnergyCost(){
        assertEquals(0.1,Utils.pathEnergyCost(2, 2, 2, 2, 4, 8), 0.1);
        boolean flag = false;
        try{
            Utils.pathEnergyCost(2, 2, 2, 2, 2, -2);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        
        flag = false;
        try{
            Utils.pathEnergyCost(2, 2, 2, 2, 2, 1);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        flag = false;
        try{
            Utils.pathEnergyCost(2, 2, -2, 2, 2, 3);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        flag = false;
        try{
            Utils.pathEnergyCost(2, -3, 2, 2, 2, 3);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        flag = false;
        try{
            Utils.pathEnergyCost(-1, 2, 2, 2, 2, 3);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        
        
    }
    
}
