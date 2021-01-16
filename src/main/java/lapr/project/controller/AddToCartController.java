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
    

    public AddToCartController(String clientEmail, ProductDB pdb, ClientDB cdb, CartProductDB cpdb) {
        this.clientEmail = clientEmail;
        this.pdb = pdb;
        this.cpdb = cpdb;
        products = new HashMap<>();
    }


    
    public List<String> getAvailableProducts() throws SQLException{
        List<String> lst = Utils.listToString(pdb.getProducts());
        return lst;
    }
    
    public String getSelectedProduct(int id) throws SQLException{
        pro = pdb.getProduct(id);
        return pro== null ? null : pro.toString();
    }
    
    public boolean addToQueue(int quantity){
        if(pro != null){
            products.put(pro, quantity);
            return true;
        }
        return false;
    }
    
    public boolean addToCart() throws SQLException{
        if(!products.isEmpty()){
            cpdb.saveShoppingCart(products, clientEmail);
            return true;
        }
        return false;
    }
    
    
    
    
    
    
}
