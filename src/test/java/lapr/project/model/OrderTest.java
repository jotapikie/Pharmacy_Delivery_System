/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
public class OrderTest {
    
    private static Order order;
    private static Order order2;
    
    @BeforeAll
    public static void setUpClass() {
        order = new Order(2, 34.95, null);
        order2 = new Order(3, 5.99, null);
    }
    

    
    @BeforeEach
    public void setUp() {
    }
    


    /**
     * Test of getId method, of class Order.
     */
    @Test
    public void testGetId() {
        order.setId(5);
        assertEquals(5, order.getId());
    }

    /**
     * Test of getBeginDate method, of class Order.
     */
    @Test
    public void testGetBeginDate() {
        Date d = Date.from(Instant.now());
        order.setBeginDate(d);
        assertEquals(d, order.getBeginDate());
    }

    /**
     * Test of getEndDate method, of class Order.
     */
    @Test
    public void testGetEndDate() {
        Date d = Date.from(Instant.now());
        order.setEndDate(d);
        assertEquals(d, order.getEndDate());
    }

    /**
     * Test of getPrice method, of class Order.
     */
    @Test
    public void testGetPrice() {
        order.setPrice(23.56);
        assertEquals(23.56, order.getPrice());
    }

    /**
     * Test of getStatus method, of class Order.
     */
    @Test
    public void testGetStatus() {
        order.setStatus("Preparing");
        assertEquals("Preparing", order.getStatus());
    }

    /**
     * Test of getProducts method, of class Order.
     */
    @Test
    public void testGetProducts() {
        Product p = new Product(1, "Brufen", 23.56, 23.99);
        HashMap<Product, Integer> pr = new HashMap<>();
        pr.put(p, 2);
        order.setProducts(pr);
        assertEquals(pr, order.getProducts());
        
    }
    
        /**
     * Test of setId method, of class Order.
     */
    @Test
    public void testSetId() {
        order.setId(5);
        assertEquals(5, order.getId());
    }
    
    
            /**
     * Test of setBeginDate method, of class Order.
     */
    @Test
    public void testSetBeginDate() {
        Date d = Date.from(Instant.now());
        order.setBeginDate(d);
        assertEquals(d, order.getBeginDate());
    }
    
                /**
     * Test of setEndDate method, of class Order.
     */
    @Test
    public void testSetEndDate() {
        Date d = Date.from(Instant.now());
        order.setEndDate(d);
        assertEquals(d, order.getEndDate());
    }
    
            /**
     * Test of setPrice method, of class Order.
     */
    @Test
    public void testSetPrice() {
        order.setPrice(23.45);
        assertEquals(23.45, order.getPrice());
    }
    
            /**
     * Test of setProducts method, of class Order.
     */
    @Test
    public void testSetProducts() {
        Product p = new Product(2, "Brufen", 23.56, 23.99);
        HashMap<Product, Integer> pr = new HashMap<>();
        pr.put(p, 2);
        order.setProducts(pr);
        assertEquals(pr, order.getProducts());
    }
    

    /**
     * Test of setStatus method, of class Order.
     */
    @Test
    public void testSetStatus() {
        order.setStatus("Prepared");
        assertEquals("Prepared", order.getStatus());
    }

    /**
     * Test of toString method, of class Order.
     */
    @Test
    public void testToString() {
        String expResult = "Id: 3 | Status: Processing | Price: 5,99 €";
        String result = order2.toString();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of compareTo method, of class Order.
     */
    @Test
    public void testCompareTo() {
        assertEquals(0, order.compareTo(order));
        Date d1 = new Date(45645646);
        Date d2 = new Date(45645689);
        order.setBeginDate(d1);
        order2.setBeginDate(d2);
        assertTrue(order.compareTo(order2) < 0);
        order.setBeginDate(d2);
        order2.setBeginDate(d1);
        assertTrue(order.compareTo(order2) > 0);
    }
    
}
