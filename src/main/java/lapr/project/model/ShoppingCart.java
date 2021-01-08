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
     * The total price
     */
    private double price;

    /**
     * Class Constructor
     */
    public ShoppingCart() {
        items = new HashMap<>();
        price = 0;
    }

    /**
     * Returns all the products and its quantities.
     * @return - products and quantities.
     */
    public HashMap<Product, Integer> getItems() {
        return items;
    }

    /**
     * Returns the total price of the products in the shopping cart
     * @return double total price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set the items in the shopping cart.
     * @param items - new items
     */
    public void setItems(HashMap<Product, Integer> items) {
        this.items = items;
        updateTotalPrice();
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
        s.append(String.format("Total Price: %.2f € %n", price));
        return s.toString();
    }
    
    /**
     * Adds a product to the cart.
     * If the product already exists in the cart, its quantity is incremented.
     * Else the product is added to the cart with the quantity passed as parameter.
     * @param pro - Product that will be added to the cart
     * @param quantity - quantity of that product.
     */
    public void updateCart(Product pro, int quantity){
        if(items.containsKey(pro)){
            int oldQuantity = items.get(pro);
            int newQuantity = oldQuantity + quantity;
            items.put(pro, newQuantity);
        }else{
            items.put(pro, quantity);
        }
        updateTotalPrice();
    }
    
    /**
     * Remove a product from the cart.
     * @param pro - Product that will be removed.
     */
    public void removeProduct(Product pro){
        items.remove(pro);
        updateTotalPrice();
    }
    
    /**
     * Allows the shopping cart to keep always its price updated when some change is made.
     */
    private void updateTotalPrice(){
        price = 0;
        for(Product p : items.keySet()){
            price = price + (p.getPrice()*items.get(p));
        }
    }
    
    


    
    
    
    
    
    
}
