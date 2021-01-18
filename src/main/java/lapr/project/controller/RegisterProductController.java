/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import lapr.project.data.ProductDB;
import lapr.project.model.Product;
/**
 *
 * @author Helder
 */
public class RegisterProductController {
    private final ProductDB pdb;
    private Product pro;
    private final HashSet<Product> products = new HashSet<>();

    public RegisterProductController(ProductDB productDB) {
        this.pdb=productDB;
    }

    
    public String newProduct(String name, double weight, double price) {
        pro = pdb.newProduct(name,weight,price);
        return pro.toString();
    }

    public boolean addToQueue(){
        return products.add(pro);
    }

    public int registProduct() throws SQLException {
        final int i = pdb.saveProducts(products);
        products.clear();
        return i;
    }
    public Set<Product> getProductsList() {
        return products;
    }
    
    
    
    
}
