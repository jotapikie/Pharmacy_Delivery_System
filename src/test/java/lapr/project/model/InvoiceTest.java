/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
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
public class InvoiceTest {
    
    static Invoice inv;
    static Address add;
    static HashMap<Product, Integer> products;
    static Client cli;
    
    @BeforeAll
    public static void setUpClass() {
        add = new Address("Rua de Teste", new GeographicalPoint(45.7, 29.6, 23.6), "Cidade de Teste", 48, "4525-454");
        Product p1 = new Product(1, "Brufen", 5.6, 23);
        Product p2 = new Product(2, "Nasal", 2.7, 24.5);
        products = new HashMap<>();
        products.put(p1, 3);
        products.put(p2, 2);
        cli = new Client("Tiago", "123", "client1@lapr.com", 912542786, 32, 683903567, add);
        inv = new Invoice(cli, add, products, 40, 34.5, 2, 3,683903567);
    }
    
    @BeforeEach
    public void setUp(){
        inv = new Invoice(cli, add, products, 40, 34.5, 2, 3,683903567);
    }


    /**
     * Test of getName method, of class Invoice.
     */
    @Test
    public void testGetName() {
        assertEquals("Tiago", inv.getName());
    }

    /**
     * Test of getAddress method, of class Invoice.
     */
    @Test
    public void testGetAddress() {
        assertEquals(add, inv.getAddress());
    }

    /**
     * Test of getCreditsSpent method, of class Invoice.
     */
    @Test
    public void testGetCreditsSpent() {
        assertEquals(3, inv.getCreditsSpent());
    }

    /**
     * Test of getCreditsWon method, of class Invoice.
     */
    @Test
    public void testGetCreditsWon() {
        assertEquals(2, inv.getCreditsWon());
    }

    /**
     * Test of getCurrentCredits method, of class Invoice.
     */
    @Test
    public void testGetCurrentCredits() {
        assertEquals(32, inv.getCurrentCredits());
    }

    /**
     * Test of getDate method, of class Invoice.
     */
    @Test
    public void testGetDate() {
        assertTrue(inv.getDate() != null);
    }

    /**
     * Test of getNif method, of class Invoice.
     */
    @Test
    public void testGetNif() {
        assertEquals(683903567, inv.getNif());
    }

    /**
     * Test of getPricePaid method, of class Invoice.
     */
    @Test
    public void testGetPricePaid() {
        assertEquals(34.5, inv.getPricePaid());
    }

    /**
     * Test of getTotalPrice method, of class Invoice.
     */
    @Test
    public void testGetTotalPrice() {
        assertEquals(40, inv.getTotalPrice());
    }

    /**
     * Test of setName method, of class Invoice.
     */
    @Test
    public void testSetName() {
        inv.setName("Paulo");
        assertEquals("Paulo", inv.getName());
        
        boolean flag = false;
        try{
            inv.setName(null);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        flag = false;
        try{
            inv.setName("");
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }

    /**
     * Test of setCreditsSpent method, of class Invoice.
     */
    @Test
    public void testSetCreditsSpent() {
        inv.setCreditsSpent(4);
        assertEquals(4, inv.getCreditsSpent());
        boolean flag = false;
        try{
            inv.setCreditsSpent(-3);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }

    /**
     * Test of setCreditsWon method, of class Invoice.
     */
    @Test
    public void testSetCreditsWon() {
        inv.setCreditsWon(5);
        assertEquals(5, inv.getCreditsWon());
        boolean flag = false;
        try{
            inv.setCreditsWon(-3);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }

    /**
     * Test of setCurrentCredits method, of class Invoice.
     */
    @Test
    public void testSetCurrentCredits() {
        inv.setCurrentCredits(7);
        assertEquals(7, inv.getCurrentCredits());
        boolean flag = false;
        try{
            inv.setCurrentCredits(-3);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }

    /**
     * Test of setDate method, of class Invoice.
     */
    @Test
    public void testSetDate() {
        Timestamp t = new Timestamp(555555);
        inv.setDate(t);
        assertEquals(t, inv.getDate());
        boolean flag = false;
        try{
            inv.setDate(null);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }

    /**
     * Test of setNif method, of class Invoice.
     */
    @Test
    public void testSetNif() {
        inv.setNif(111111111);
        assertEquals(111111111, inv.getNif());
        boolean flag = false;
        try{
            inv.setNif(45845);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
   
    }

    /**
     * Test of setPricePaid method, of class Invoice.
     */
    @Test
    public void testSetPricePaid() {
        inv.setPricePaid(3);
        assertEquals(3, inv.getPricePaid());
        boolean flag = false;
        try{
            inv.setPricePaid(9999);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        flag = false;
        try{
            inv.setPricePaid(-3);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }

    /**
     * Test of setTotalPrice method, of class Invoice.
     */
    @Test
    public void testSetTotalPrice() {
        inv.setTotalPrice(6);
        assertEquals(6, inv.getTotalPrice());
        boolean flag = false;
        try{
            inv.setTotalPrice(-3);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }

    /**
     * Test of setProductsBought method, of class Invoice.
     */
    @Test
    public void testSetProductsBought() {
        HashMap<Product, Integer> pr = new HashMap<>();
        pr.put(new Product(3, "test", 6.5, 3.4), 4);
        inv.setProductsBought(pr);
        assertEquals(pr, inv.getProductsBought());
        boolean flag = false;
        try{
            inv.setProductsBought(null);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        flag = false;
        try{
            inv.setProductsBought(new HashMap<>());
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }

    /**
     * Test of setAddress method, of class Invoice.
     */
    @Test
    public void testSetAddress() {
        Address ad = new Address("sdfsd", new GeographicalPoint(34.23, 35.4, 34.5), "sadf", 7, "4538-343");
        inv.setAddress(ad);
        assertEquals(ad, inv.getAddress());
        boolean flag = false;
        try{
            inv.setAddress(null);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
    }
    
    @Test
    public void testToString(){
        assertTrue(inv.toString()!=null);
    }
}
