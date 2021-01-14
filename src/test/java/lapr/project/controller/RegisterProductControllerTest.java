/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

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
    private static RegisterProductController rcp1;
    private static Product p1;
    private static Product p2;
    
    public RegisterProductControllerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        pdb = mock(ProductDB.class);
        rcp=new RegisterProductController(pdb);
        rcp1=new RegisterProductController(pdb);
        p1 = new Product (1,"brufen",0.2,5);
        p2 = new Product (2,"benerun",0.2,5);
        
        when(pdb.newProduct(1,"brufen",0.2,5)).thenReturn(p1);
        when(pdb.newProduct(2,"benerun",0.2,5)).thenReturn(p2);
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
     * Test of newProduct method, of class RegisterProductController.
     */
    @Test
    public void testNewProduct() {
         assertEquals(p1.toString(), rcp.newProduct(1,"brufen",0.2,5));
         assertEquals(p2.toString(), rcp.newProduct(2,"benerun",0.2,5));
    }

    /**
     * Test of addToQueue method, of class RegisterProductController.
     */
    @Test
    public void testAddToQueue() {
        rcp.newProduct(1,"brufen",0.2,5);
        
        assertEquals(0, rcp.getProductsList().size());
        
        rcp.addToQueue();
        assertEquals(1, rcp.getProductsList().size());
        assertEquals(true, rcp.getProductsList().contains(p1));
        
        rcp.newProduct(2,"benerun",0.2,5);
        rcp.addToQueue();
        assertEquals(2, rcp.getProductsList().size());
        assertEquals(true, rcp.getProductsList().contains(p2));
    }

    /**
     * Test of registProduct method, of class RegisterProductController.
     */
    @Test
    public void testRegistProduct() throws Exception {
        assertEquals(0, rcp.registProduct());          
        rcp1.newProduct(1,"brufen",0.2,5);
        rcp1.addToQueue();
        when(pdb.saveProducts(rcp1.getProductsList())).thenReturn(rcp1.getProductsList().size());
        assertEquals(1, rcp1.registProduct());
    }
    
}
