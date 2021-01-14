/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
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
    private final static CreditCard creditCard= new CreditCard(1231231231231231L, new Date(1673109541000L), 554);
    private static Address address1 = new Address("Reta do Pereiro 710", 40.738312, -7.765318, "porto", 114, "4250-527");
    private static Client client1 = new Client("username1", "name1", "password1", "email1@email.com", 123456789, creditCard, address1);
    private static Map<Product, Integer> productsBought=null;
    
    public InvoiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        invoiceTest= new Invoice(client1,address1,productsBought,50.0,50.0,2,1,12345);
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getName method, of class Invoice.
     */
    @Test
    public void testGetName() {
        String expResult = "name1";
        String result = invoiceTest.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAddress method, of class Invoice.
     */
    @Test
    public void testGetAddress() {
        Address expResult = address1;
        Address result = invoiceTest.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCreditsSpent method, of class Invoice.
     */
    @Test
    public void testGetCreditsSpent() {
        int expResult = 1;
        int result = invoiceTest.getCreditsSpent();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCreditsWon method, of class Invoice.
     */
    @Test
    public void testGetCreditsWon() {
        int expResult = 2;
        int result = invoiceTest.getCreditsWon();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCurrentCredits method, of class Invoice.
     */
    @Test
    public void testGetCurrentCredits() {
        int expResult = 0;
        int result = invoiceTest.getCurrentCredits();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNif method, of class Invoice.
     */
    @Test
    public void testGetNif() {
        int expResult = 12345;
        int result = invoiceTest.getNif();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPricePaid method, of class Invoice.
     */
    @Test
    public void testGetPricePaid() {
        double expResult = 50.0;
        double result = invoiceTest.getPricePaid();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getTotalPrice method, of class Invoice.
     */
    @Test
    public void testGetTotalPrice() {
        double expResult = 50.0;
        double result = invoiceTest.getTotalPrice();
        assertEquals(expResult, result, 0.0);
    }
    
}
