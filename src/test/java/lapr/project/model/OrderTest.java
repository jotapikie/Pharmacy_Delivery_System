/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.sql.Timestamp;
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
    private static Order order3;
    
    @BeforeAll
    public static void setUpClass() {
        order = new Order();
        order.setId(2);
        order.setPrice(34.95);

        order2 = new Order();
        order2.setId(3);
        order.setPrice(5.99);
        order2.setAssociatedOrder(order);
        
        order3 = new Order(4, new Timestamp(3433), new Timestamp(3434), "Prepared", 34.56);
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
        
        assertEquals(4, order3.getId());
    }

    /**
     * Test of getBeginDate method, of class Order.
     */
    @Test
    public void testGetBeginDate() {
        Timestamp d = new Timestamp(2);
        order.setBeginDate(d);
        assertEquals(d, order.getBeginDate());
    }

    /**
     * Test of getEndDate method, of class Order.
     */
    @Test
    public void testGetEndDate() {
        Timestamp d = new Timestamp(4);
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
        
        assertEquals(34.56, order3.getPrice());
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
    
    @Test
    public void testGetAssociatedOrder(){
        order2.setAssociatedOrder(order);
        assertEquals(order, order2.getAssociatedOrder());
    }
    
        /**
     * Test of setId method, of class Order.
     */
    @Test
    public void testSetId() {
        order.setId(5);
        assertEquals(5, order.getId());
        
        boolean flag = false;
        try{
            order.setId(-2);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }
    
    
            /**
     * Test of setBeginDate method, of class Order.
     */
    @Test
    public void testSetBeginDate() {
        Timestamp d = Timestamp.from(Instant.now());
        order.setBeginDate(d);
        assertEquals(d, order.getBeginDate());
        
        boolean flag = false;
        try{
            order.setBeginDate(null);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }
    
                /**
     * Test of setEndDate method, of class Order.
     */
    @Test
    public void testSetEndDate() {
        Timestamp d = Timestamp.from(Instant.now());
        order.setEndDate(d);
        assertEquals(d, order.getEndDate());
        
        order.setBeginDate(new Timestamp(5));
        boolean flag = false;
        try{
            order.setEndDate(new Timestamp(4));
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }
    
            /**
     * Test of setPrice method, of class Order.
     */
    @Test
    public void testSetPrice() {
        order.setPrice(23.45);
        assertEquals(23.45, order.getPrice());
        
        boolean flag = false;
        try{
            order.setPrice(-2);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
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
        
        boolean flag = false;
        try{
            order.setProducts(null);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }
    
    @Test
    public void testSetAssociatedOrder(){
        boolean flag = false;
        try{
            order.setAssociatedOrder(order);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }
    

    /**
     * Test of setStatus method, of class Order.
     */
    @Test
    public void testSetStatus() {
        order.setStatus("Prepared");
        assertEquals("Prepared", order.getStatus());
        
        boolean flag = false;
        try{
            order.setStatus("");
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        order.setStatus("Prepared");
        
//        flag = false;
//        try{
//            order.setStatus(null);
//        }catch(IllegalArgumentException e){
//            flag = true;
//        }
//        assertTrue(flag);
        
        flag = false;
        try{
            order.setStatus("Other");
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }

    /**
     * Test of toString method, of class Order.
     */
    @Test
    public void testToString() {
        //String expResult = "Id: 3 | Status: Processing | Price: 5.99 €";
        String result = order2.toString();
        assertEquals(result, order2.toString());
        
    }
    
    @Test
    public void testEquals(){
        assertTrue(order.equals(order));
        assertFalse(order.equals(null));
        assertFalse(order.equals(new GeographicalPoint(34.5, 2.45, 2.3)));
        assertFalse(order.equals(order2));
    }
    
    @Test
    public void testHashCode(){
         assertEquals(order.getId(), order.hashCode());
    }

    /**
     * Test of compareTo method, of class Order.
     */
    @Test
    public void testCompareTo() {
        assertEquals(0, order.compareTo(order));
        Timestamp d1 = new Timestamp(45645646);
        Timestamp d2 = new Timestamp(45645689);
        order.setBeginDate(d1);
        order2.setBeginDate(d2);
        assertTrue(order.compareTo(order2) < 0);
        order.setBeginDate(d2);
        order2.setBeginDate(d1);
        assertTrue(order.compareTo(order2) > 0);
    }
    
}
