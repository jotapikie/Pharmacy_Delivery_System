/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lapr.project.data.CartProductDB;
import lapr.project.data.ClientDB;
import lapr.project.data.ProductDB;
import lapr.project.model.Product;

/**
 *
 * @author Diogo
 */
public class AddToCartController {
    
    private final String clientEmail;
    private final ProductDB pdb;
    private final ClientDB cdb;
    private final CartProductDB cpdb;
    private Product pro;
    private final Map<Product, Integer> products;

    public AddToCartController(String clientEmail, ProductDB pdb, ClientDB cdb, CartProductDB cpdb) {
        this.clientEmail = clientEmail;
        this.pdb = pdb;
        this.cdb = cdb;
        this.cpdb = cpdb;
        products = new HashMap<>();
    }


    
    public List<String> getAvailableProducts() throws SQLException{
        List<String> lst = new ArrayList<>();
        for(Product p : pdb.getProducts()){
            lst.add(p.toString());
        }
        if (lst.isEmpty()){
            return new ArrayList<>();
        }else{
        return lst;}
    }
    
    public String getSelectedProduct(int id) throws SQLException{
        pro = pdb.getProduct(id);
        return pro== null ? null : pro.toString();
    }
    
    public void addToQueue(int quantity){
        products.put(pro, quantity);
    }
    
    public void addToCart() throws SQLException{
        if(!products.isEmpty()){
            cpdb.saveShoppingCart(products, clientEmail);
        }
    }
    
    
    
    
    
    
}
