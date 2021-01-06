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
    
    private HashMap<Product, Integer> items;
    private double price;

    public ShoppingCart() {
        items = new HashMap<>();
        price = 0;
    }

    public HashMap<Product, Integer> getItems() {
        return items;
    }

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
    
    public void removeProduct(Product pro){
        items.remove(pro);
        updateTotalPrice();
    }
    
    private void updateTotalPrice(){
        price = 0;
        for(Product p : items.keySet()){
            price = price + (p.getPrice()*items.get(p));
        }
    }
    
    

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Product p : items.keySet()){
            s.append(String.format("%s | Quantity: %d %n", p.toString(), items.get(p)));
        }
        s.append(String.format("Total Price: %.2f € %n", price));
        return s.toString();
    }
    
    
    
    
    
    
}
