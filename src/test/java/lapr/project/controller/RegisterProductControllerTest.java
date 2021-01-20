/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import lapr.project.data.ProductDB;
import lapr.project.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Helder
 */
public class RegisterProductControllerTest {
    private static ProductDB pdb;
    private static RegisterProductController rcp;
    private static Product p1;
    private static Product p2;
    
    private static Set<Product> products;

    
    @BeforeAll
    public static void setUpClass() {
        p1 = new Product (1,"brufen",0.2,5);
        p2 = new Product (2,"benerun",0.2,5);
        products = new HashSet<>();
    }
    
    @BeforeEach
    public void setUp() throws SQLException {
        pdb = mock(ProductDB.class);
        rcp=new RegisterProductController(pdb);
        
        when(pdb.newProduct("brufen",0.2,5)).thenReturn(p1);
        when(pdb.newProduct("benerun",0.2,5)).thenReturn(p2);
   }


    /**
     * Test of newProduct method, of class RegisterProductController.
     */
    @Test
    public void testNewProduct() {
         assertEquals(p1.toString(), rcp.newProduct("brufen",0.2,5));
         assertEquals(p2.toString(), rcp.newProduct("benerun",0.2,5));
    }

    /**
     * Test of addToQueue method, of class RegisterProductController.
     */
    @Test
    public void testAddToQueue() {
        assertFalse(rcp.addToQueue());
        
        rcp.newProduct("brufen",0.2,5);
        assertTrue(rcp.addToQueue());
        
        rcp.newProduct("brufen",0.2,5);
        assertFalse(rcp.addToQueue());
        
    }

    /**
     * Test of registProduct method, of class RegisterProductController.
     */
    @Test
    public void testRegistProduct() throws Exception {
        assertEquals(0, rcp.registProduct());
        
        rcp.newProduct("brufen",0.2,5);
        rcp.addToQueue();
        products.add(p1);
        when(pdb.saveProducts(products)).thenReturn(1);
        assertEquals(1, rcp.registProduct());
        
        rcp.newProduct("benerun",0.2,5);
        rcp.addToQueue();
        products.add(p2);
        when(pdb.saveProducts(products)).thenReturn(2);
        assertEquals(2, rcp.registProduct());
        
    }
    
}
