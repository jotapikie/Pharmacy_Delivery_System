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
 * @author Helder
 */
public class PhamarcyTest {
    private static Phamarcy phamarcyTest;
    
    public PhamarcyTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        phamarcyTest= new Phamarcy(1);
            
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Phamarcy.
     */
    @Test
    public void testGetId() {
        int expResult = 1;
        int result = phamarcyTest.getId();
        assertEquals(expResult, result);
    }
    
}
