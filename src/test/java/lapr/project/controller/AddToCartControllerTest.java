/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lapr.project.data.ClientDB;
import lapr.project.data.ProductDB;
import lapr.project.model.Client;
import lapr.project.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Diogo
 */
public class AddToCartControllerTest {
    
    private static AddToCartController controller;
    private static ProductDB pdb;
    private static ClientDB cdb;
    private static List<Product> lst1 = new ArrayList<>();
    private static List<Product> lstEmpty = new ArrayList<>();
    private static Product p1;
    private static Product p2;
    private static Product p3;
    private static Client c;

    @BeforeAll
    public static void setUpClass() throws SQLException {
        pdb = mock(ProductDB.class);
        cdb = mock(ClientDB.class);
        controller = new AddToCartController("client1@lapr.com", pdb, cdb);
        
        c = new Client("u1", "Hugo", "123", "client1@lapr.com", 123456789, 0, null, null);
        when(cdb.getClient("client1@lapr.com")).thenReturn(c);
        controller = new AddToCartController("client1@lapr.com", pdb, cdb);
        
        p1 = new Product(1, "Brufen", 0.65, 5);
        p2 = new Product(2, "Benuron", 1.23, 3.5);
        p3 = new Product(3, "Nasla", 0.75, 8.3);
        
        when(pdb.getProduct(1)).thenReturn(p1);
        when(pdb.getProduct(2)).thenReturn(p2);
        when(pdb.getProduct(3)).thenReturn(p3);
        
        lst1.add(p1);
        lst1.add(p2);
        lst1.add(p3);
    }
    

/*
    *//**
     * Test of getAvailableProducts method, of class AddToCartController.
     *//*
    @Test
    public void testGetAvailableProducts() throws Exception {
        System.out.println("getAvailableProducts");

        when(pdb.getProducts()).thenReturn(lst1);
        List<String> res = controller.getAvailableProducts();
        Iterator<String> it = res.iterator();
        assertEquals(3, res.size());
        assertEquals(p1.toString(),it.next());
        assertEquals(p2.toString(), it.next());
        assertEquals(p3.toString(), it.next());
       when(pdb.getProducts()).thenReturn(lstEmpty);
        res = controller.getAvailableProducts();
        assertEquals(0, res.size());
    }*/

    /**
     * Test of getSelectedProduct method, of class AddToCartController.
     */
    @Test
    public void testGetSelectedProduct() throws Exception {
        System.out.println("getSelectedProduct");
        

        
        assertEquals(p1.toString(), controller.getSelectedProduct(1));
        assertEquals(p2.toString(), controller.getSelectedProduct(2));
        assertEquals(p3.toString(), controller.getSelectedProduct(3));
        assertEquals(null, controller.getSelectedProduct(4));
    }

    /**
     * Test of addToCart method, of class AddToCartController.
     */
    @Test
    public void testAddToCart() throws SQLException {
        System.out.println("addToCart");
        
        assertEquals(0, c.getCart().getItems().size());
        
        controller.getSelectedProduct(1);
        controller.addToCart(3);
        int nrProducts = c.getCart().getItems().size();
        int quantity = c.getCart().getItems().get(p1);
        double price = c.getCart().getPrice();
        assertEquals(1,nrProducts);
        assertEquals(3, quantity);
        assertEquals(15, price);
        assertEquals(true, c.getCart().getItems().keySet().contains(p1));
        
        controller.getSelectedProduct(2);
        controller.addToCart(1);
        nrProducts = c.getCart().getItems().size();
        quantity = c.getCart().getItems().get(p2);
        price = c.getCart().getPrice();
        assertEquals(2,nrProducts);
        assertEquals(1, quantity);
        assertEquals(18.5, price);
        assertEquals(true, c.getCart().getItems().keySet().contains(p2));
        
        controller.getSelectedProduct(1);
        controller.addToCart(5);
        nrProducts = c.getCart().getItems().size();
        quantity = c.getCart().getItems().get(p1);
        price = c.getCart().getPrice();
        assertEquals(2,nrProducts);
        assertEquals(8, quantity);
        assertEquals(43.5, price);
        assertEquals(true, c.getCart().getItems().keySet().contains(p1));
        
        
        

    }
    
}
