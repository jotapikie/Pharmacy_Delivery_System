/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

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
public class PlatformTest {
    
    
    
    @BeforeAll
    public static void setUpClass() {
       
    }
    

    
    @BeforeEach
    public void setUp() {
    }


    /**
     * Test of getDeliveryPrice method, of class Platform.
     */
    @Test
    public void testGetDeliveryPrice() {
        double expResult = 3.5;
        double result = Platform.getDeliveryPrice();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCreditsPerEuro method, of class Platform.
     */
    @Test
    public void testGetCreditsPerEuro() {
        double expResult = 15;
        double result = Platform.getCreditsPerEuro();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCreditsWonPerEuroSpent method, of class Platform.
     */
    @Test
    public void testGetCreditsWonPerEuroSpent() {
        double expResult = 0.2;
        double result = Platform.getCreditsWonPerEuroSpent();
        assertEquals(expResult, result);
    }
    
}
