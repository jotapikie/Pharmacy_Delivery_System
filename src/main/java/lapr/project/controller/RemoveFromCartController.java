/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.ClientDB;
import lapr.project.data.ProductDB;
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
    private ClientDB cdb;
    private ProductDB pdb;

    public RemoveFromCartController(String clientEmail) {
        this.clientEmail = clientEmail;
        cdb = new ClientDB();
        pdb = new ProductDB();
    }
    
    public String getProductsInCart(){
           cart = cdb.getClient(clientEmail).getCart();
           return cart.toString();
    }
    
    public String getSelectedProduct(int id){
        pro = pdb.getProduct(id);
        return pro == null ? null : pro.toString();
    }
    
    public void removeFromCart(){
        if(pro != null){
            cart.removeProduct(pro);
        }
    }
    
    
    
}
