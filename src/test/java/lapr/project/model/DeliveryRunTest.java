/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

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
public class DeliveryRunTest {
    
    private static DeliveryRun rd;
    private static List<Order> orders;
    private static Order o1;
    private static Order o2;
    
    
    @BeforeAll
    public static void setUpClass() {
        o1 = new Order();
        o1.setId(1);
        o1.setPrice(12.67);
        
        o2 = new Order();
        o2.setId(2);
        o2.setPrice(2.80);
        
        orders = new ArrayList<>();
        orders.add(o1);
        orders.add(o2);
    }
    

    
    @BeforeEach
    public void setUp() {
        rd = new DeliveryRun(1, orders);
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getId method, of class DeliveryRun.
     */
    @Test
    public void testGetId() {
        assertEquals(1, rd.getId());
    }

    /**
     * Test of getOrders method, of class DeliveryRun.
     */
    @Test
    public void testGetOrders() {
        assertEquals(orders,rd.getOrders());
    }

    /**
     * Test of setId method, of class DeliveryRun.
     */
    @Test
    public void testSetId() {
        rd.setId(7);
        assertEquals(7, rd.getId());
        boolean flag = false;
        try{
            rd.setId(-3);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        assertEquals(7, rd.getId());
    }

    /**
     * Test of setOrders method, of class DeliveryRun.
     */
    @Test
    public void testSetOrders() {
        boolean flag = false;
        try{
            rd.setOrders(null);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }

    /**
     * Test of toString method, of class DeliveryRun.
     */
    @Test
    public void testToString() {
        assertTrue(rd.toString()!= null);
        assertFalse(rd.toString().isEmpty());
    }
    
}
