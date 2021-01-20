/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lapr.project.data.CartProductDB;
import lapr.project.data.ProductDB;
import lapr.project.model.Product;
import lapr.project.utils.Utils;

/**
 *
 * @author Diogo
 */
public class AddToCartController {
    
    private final String clientEmail;
    private final ProductDB pdb;
    private final CartProductDB cpdb;
    private Product pro;
    private final Map<Product, Integer> products;
    

    public AddToCartController(String clientEmail, ProductDB pdb, CartProductDB cpdb) {
        this.clientEmail = clientEmail;
        this.pdb = pdb;
        this.cpdb = cpdb;
        products = new HashMap<>();
    }


    
    public List<String> getAvailableProducts() throws SQLException{
       return Utils.listToString(pdb.getProducts());
    }
    
    public String getSelectedProduct(int id) throws SQLException{
        pro = pdb.getProduct(id);
        return pro== null ? null : pro.toString();
    }
    
    public boolean addToQueue(int quantity){
        if(pro != null){
            return products.putIfAbsent(pro, quantity)==null;
        }
        return false;
    }
    
    public boolean addToCart() throws SQLException{
        if(!products.isEmpty()){
            return cpdb.saveShoppingCart(products, clientEmail);
        }
        return false;
    }
    
    
    
    
    
    
}
