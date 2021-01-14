/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashMap;

/**
 *
 * @author Diogo
 */
public class ShoppingCart {
    
    /**
     * The items in the cart
     */
    private HashMap<Product, Integer> items;
    


    /**
     * Class Constructor
     */
    public ShoppingCart() {
        items = new HashMap<>();
    }
    
    public ShoppingCart(HashMap<Product, Integer> items) {
        this.items = items;
    }

    /**
     * Returns all the products and its quantities.
     * @return - products and quantities.
     */
    public HashMap<Product, Integer> getItems() {
        return items;
    }

 

    /**
     * Set the items in the shopping cart.
     * @param items - new items
     */
    public void setItems(HashMap<Product, Integer> items) {
        this.items = items;
    }
    

    
    /**
     * Returns the products in the cart and the total price.
     * @return String - shopping cart details
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Product p : items.keySet()){
            s.append(String.format("%s | Quantity: %d %n", p.toString(), items.get(p)));
        }
        
        return s.toString();
    }
    

    


    
    


    
    
    
    
    
    
}
