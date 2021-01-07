/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
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
    private Product pro;


    public AddToCartController(String clientEmail, ProductDB pdb) {
        this.clientEmail = clientEmail;
        this.pdb = pdb;
    }

    public AddToCartController(String clientEmail) {
        this.clientEmail = clientEmail;
        this.pdb = new ProductDB();
    }
    
    public List<String> getAvailableProducts() throws SQLException{
       List<String> products = new ArrayList<>();
       for(Product p : pdb.getProducts()){
           products.add(p.toString());
       }
       return products;
    }
    
    public String getSelectedProduct(int id) throws SQLException{
        pro = Product.getProduct(id);
        return pro == null ? null : pro.toString();
    }
    
    public void addToCart(int quantity){
        if(pro!=null){
            Client cli = Client.getClient(clientEmail);
            cli.getCart().updateCart(pro, quantity);
        }
    }
    
    
    
    
    
    
}
