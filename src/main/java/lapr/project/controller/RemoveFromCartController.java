/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Client;
import lapr.project.model.Product;
import lapr.project.model.ShoppingCart;

/**
 *
 * @author Diogo
 */
public class RemoveFromCartController {
    
    private String clientEmail;
    private ShoppingCart cart;
    private Product pro;

    public RemoveFromCartController(String clientEmail) {
        this.clientEmail = clientEmail;
    }
    
    public String getProductsInCart(){
           cart = Client.getClient(clientEmail).getCart();
           return cart.toString();
    }
    
    public String getSelectedProduct(int id){
        pro = Product.getProduct(id);
        return pro.toString();
    }
    
    public void removeFromCart(){
        if(pro != null){
            cart.removeProduct(pro);
        }
    }
    
    
    
}
