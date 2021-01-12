/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Diogo
 */
public class ShoppingCartTest {
    
    Product p1, p2, p3, p4;
    
    @BeforeEach
    public void setUp() {
        p1 = new Product(1,"a",3,10);
        p2 = new Product(2,"d",3,2);
        p3 = new Product(3,"sdsf",2,1);
        p4 = new Product(2,"d",3,2);
    }
    


   

    /**
     * Test of getPrice method, of class ShoppingCart.
     */
    @Test
    public void testGetPrice() {
 
        ShoppingCart car = new ShoppingCart();
        
        assertEquals(0, car.getPrice());
        
        car.updateCart(p1, 1);
        assertEquals(10, car.getPrice());
        
        car.updateCart(p2, 2);
        assertEquals(14, car.getPrice());
        
        car.updateCart(p3, 0);
        assertEquals(14, car.getPrice());
        
        car.updateCart(p4, 1);
        assertEquals(16, car.getPrice());
        
    }

    /**
     * Test of setItems method, of class ShoppingCart.
     */
    @Test
    public void testSetItems() {
        
        HashMap<Product, Integer> items = new HashMap<>();
        ShoppingCart car = new ShoppingCart();
        items.put(p1, 5);
        items.put(p2, 3);
        car.setItems(items);
        assertEquals(56, car.getPrice());
        assertEquals(2, car.getItems().size());

    }


    /**
     * Test of updateCart method, of class ShoppingCart.
     */
    @Test
    public void testUpdateCart() {
        
        ShoppingCart car = new ShoppingCart();
        
        car.updateCart(p1, 2);
        assertEquals(1, car.getItems().size());
        assertEquals(true, car.getItems().keySet().contains(p1));
        assertEquals(2, car.getItems().get(p1));
        assertEquals(20, car.getPrice());
        
        car.updateCart(p2, 4);
        assertEquals(2, car.getItems().size());
        assertEquals(true, car.getItems().keySet().contains(p2));
        assertEquals(4, car.getItems().get(p2));
        assertEquals(28, car.getPrice());
        
        car.updateCart(p1, 3);
        assertEquals(2, car.getItems().size());
        assertEquals(true, car.getItems().keySet().contains(p2));
        assertEquals(5, car.getItems().get(p1));
        assertEquals(58, car.getPrice());
    }

    /**
     * Test of removeProduct method, of class ShoppingCart.
     */
    @Test
    public void testRemoveProduct() {
        
        ShoppingCart car = new ShoppingCart();
        HashMap<Product, Integer> items = new HashMap<>();
        items.put(p1, 2);
        items.put(p2, 3);
        items.put(p3, 4);
        car.setItems(items);
        
        assertEquals(3, car.getItems().size());
        assertEquals(true, car.getItems().keySet().contains(p1));
        assertEquals(true, car.getItems().keySet().contains(p2));
        assertEquals(true, car.getItems().keySet().contains(p3));
        assertEquals(30, car.getPrice());
        
        car.removeProduct(p1);
        assertEquals(2, car.getItems().size());
        assertEquals(false, car.getItems().keySet().contains(p1));
        assertEquals(true, car.getItems().keySet().contains(p2));
        assertEquals(true, car.getItems().keySet().contains(p3));
        assertEquals(10, car.getPrice());
        
        car.removeProduct(p2);
        assertEquals(1, car.getItems().size());
        assertEquals(false, car.getItems().keySet().contains(p1));
        assertEquals(false, car.getItems().keySet().contains(p2));
        assertEquals(true, car.getItems().keySet().contains(p3));
        assertEquals(4, car.getPrice());
        
        car.removeProduct(p3);
        assertEquals(0, car.getItems().size());
        assertEquals(false, car.getItems().keySet().contains(p1));
        assertEquals(false, car.getItems().keySet().contains(p2));
        assertEquals(false, car.getItems().keySet().contains(p3));
        assertEquals(0, car.getPrice());
        
        
    }
    
}
