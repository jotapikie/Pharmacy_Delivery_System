/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.data.ProductDB;
import lapr.project.model.Client;
import lapr.project.model.Product;

/**
 *
 * @author Diogo
 */
public class AddToCartController {
    
    private String clientEmail;
    private ProductDB pdb;


    public AddToCartController(String clientEmail, ProductDB pdb) {
        this.clientEmail = clientEmail;
        this.pdb = pdb;
    }

    public AddToCartController(String clientEmail) {
        this.clientEmail = clientEmail;
        this.pdb = new ProductDB();
    }
    
    public List<String> getAvailableProducts(){
        return pdb.getProducts();
    }
    
    public String showProduct(int id){
        return Product.getProduct(id).toString();
    }
    
    public void addToCart(int id, int quantity){
        Client cli = Client.getClient(clientEmail);
        Product pro = Product.getProduct(id);
        cli.getCart().updateCart(pro, quantity);
    }
    
    
    
    
    
    
}
