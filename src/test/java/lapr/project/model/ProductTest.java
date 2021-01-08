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
public class ProductTest {
    
    private static Product productTest;
    public ProductTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        productTest = new Product (1,"brufen",0.2,5);
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getPrice method, of class Product.
     */
    @Test
    public void testGetPrice() {
        double expResult = 5;
        double result = productTest.getPrice();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getId method, of class Product.
     */
    @Test
    public void testGetId() {
        int expResult = 1;
        int result = productTest.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Product.
     */
    @Test
    public void testGetName() {
        String expResult = "brufen";
        String result = productTest.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWeight method, of class Product.
     */
    @Test
    public void testGetWeight() {
        double expResult = 0.2;
        double result = productTest.getWeight();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of equals method, of class Product.
     */
    @Test
    public void testEquals() {
        Object obj = null;
        boolean expResult = false;
        boolean result = productTest.equals(obj);
        assertEquals(expResult, result);
    }
    
}
