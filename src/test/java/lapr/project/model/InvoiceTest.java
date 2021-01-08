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
public class InvoiceTest {
    private static Invoice invoiceTest;
    public InvoiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        invoiceTest=new Invoice("abc@gmail.com",2,123456);
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getEmail method, of class Invoice.
     */
    @Test
    public void testGetEmail() {
        String expResult = "abc@gmail.com";
        String result = invoiceTest.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Invoice.
     */
    @Test
    public void testGetId() {
        int expResult = 2;
        int result = invoiceTest.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNif method, of class Invoice.
     */
    @Test
    public void testGetNif() {
        int expResult = 123456;
        int result = invoiceTest.getNif();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class Invoice.
     */
    @Test
    public void testSetEmail() {
        String email = "abc@gmail.com";
        Invoice instance = new Invoice();
        instance.setEmail(email);
        String result = instance.getEmail().toString();
        String expResult = email;
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Invoice.
     */
    @Test
    public void testSetId() {
        int id = 1;
        Invoice instance = new Invoice();
        instance.setId(id);
        int result = instance.getId();
        int expResult = id;
        assertEquals(expResult, result);
    }

    /**
     * Test of setNif method, of class Invoice.
     */
    @Test
    public void testSetNif() {
        int nif = 233;
        Invoice instance = new Invoice();
        instance.setNif(nif);
        int result = instance.getNif();
        int expResult = nif;
        assertEquals(expResult, result);
    }

    
    
}
