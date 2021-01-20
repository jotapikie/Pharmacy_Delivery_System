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
     * Test of setId method, of class Product.
     */
    @Test
    public void testSetId() {
        productTest.setId(3);
        double expResult = 3;
        double result = productTest.getId();
        assertEquals(expResult, result);
        boolean exp = false;
        try{
        productTest.setId(-1);
        }catch(IllegalArgumentException e){
            exp = true;
        }
        assertTrue(exp);
        assertEquals(expResult, productTest.getId());
        
    }
    
    
        /**
     * Test of setName method, of class Product.
     */
    @Test
    public void testSetName() {
        productTest.setName("Brufen");
        String expResult = "Brufen";
        String result = productTest.getName();
        assertEquals(expResult, result);
        
        boolean exp = false;
        try{
        productTest.setName(null);
        }catch(IllegalArgumentException e){
            exp = true;
        }
        assertTrue(exp);
        
        exp = false;
        try{
        productTest.setName("");
        }catch(IllegalArgumentException e){
            exp = true;
        }
        assertTrue(exp);
        
        
    }
    
        /**
     * Test of setWeight method, of class Product.
     */
    @Test
    public void testSetWeight() {
        productTest.setWeight(5.9);
        double expResult = 5.9;
        double result = productTest.getWeight();
        assertEquals(expResult, result, 0.0);
        
        boolean exp = false;
        try{
        productTest.setWeight(-2);
        }catch(IllegalArgumentException e){
            exp = true;
        }
        assertTrue(exp);
        assertEquals(5.9, productTest.getWeight());
    }
    
        /**
     * Test of setPrice method, of class Product.
     */
    @Test
    public void testSetPrice() {
        productTest.setPrice(25.99);
        double expResult = 25.99;
        double result = productTest.getPrice();
        assertEquals(expResult, result, 0.0);
        
        boolean exp = false;
        try{
        productTest.setPrice(-3);
        }catch(IllegalArgumentException e){
            exp = true;
        }
        assertTrue(exp);
        assertEquals(expResult, productTest.getPrice());
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
        
        expResult = true;
        result = productTest.equals(productTest);
        assertEquals(expResult, result);
        
        expResult = false;
        Address add = new Address("Corga", new GeographicalPoint(34.2323, 23.545, 6.7), "Smf", 45, "3456-343");
        result = productTest.equals(add);
        assertEquals(expResult, result);
        
        Product p = new Product(1,"brufen",0.2,5);
        assertEquals(true, productTest.equals(p));
        
        p = new Product(2, "Other", 0.3, 4.6);
        assertFalse(productTest.equals(p));
        

    }
    
}
