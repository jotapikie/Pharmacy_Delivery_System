/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

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
public class ShoppingCartTest {
    
    private ShoppingCart cart;
    private static HashMap<Product, Integer> products;
    
    @BeforeAll
    public static void setUpClass() {
        
    }
    

    
    @BeforeEach
    public void setUp() {
        products = new HashMap<>();
        products.put(new Product(1, "Product1", 0.56, 2.3), 2);
        products.put(new Product(2,"Product2", 3.4, 5.6), 1);
        cart = new ShoppingCart(products);
    }
    


    /**
     * Test of getItems method, of class ShoppingCart.
     */
    @Test
    public void testGetItems() {
        assertEquals(products, cart.getItems());
    }

    /**
     * Test of setItems method, of class ShoppingCart.
     */
    @Test
    public void testSetItems() {
       
        HashMap<Product, Integer> temp = new HashMap<>();
        temp.put(new Product(3, "Product3", 0.22, 4.5), 1);
        cart.setItems(temp);
        assertEquals(temp, cart.getItems());
        
        boolean flag = false;
        try{
            cart.setItems(null);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        ShoppingCart cart = new ShoppingCart();
        assertNotNull(cart.getItems());
    }

    /**
     * Test of toString method, of class ShoppingCart.
     */
    @Test
    public void testToString() {
        assertTrue(cart.toString()!= null);
    }
    
}
