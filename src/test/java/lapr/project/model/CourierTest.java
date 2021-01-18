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
public class CourierTest {
    
    private static Courier courierTest;
    
    public CourierTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        courierTest=new Courier("Francisco","abc@gmail.com","1234", 454654567,32425,200);
    }
    
    @AfterEach
    public void tearDown() {
    }
    
            /**
     * Test of getNss method, of class Courier.
     */
    @Test
    public void testGetNss() {
        double expResult = 32425 ;
        double result = courierTest.getNss();
        assertEquals(expResult, result, 0.0);
    }
   
    
        /**
     * Test of getNif method, of class Courier.
     */
    @Test
    public void testGetNif() {
        double expResult = 454654567;
        double result = courierTest.getNif();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getWeight method, of class Courier.
     */
    @Test
    public void testGetMaxWeight() {
        double expResult = 200;
        double result = courierTest.getWeight();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testSetNif(){
        courierTest.setNif(123456789);
        assertEquals(123456789, courierTest.getNif());
        
        boolean flag = false;
        try{
            courierTest.setNif(124);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }
    
    @Test
    public void testSetNss(){
        courierTest.setNss(123456789);
        assertEquals(123456789, courierTest.getNss());
    }
    
        @Test
    public void testSetWeight(){
        courierTest.setWeight(67.8);
        assertEquals(67.8, courierTest.getWeight());
        
        boolean flag = false;
        try{
            courierTest.setWeight(-12);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }
}
