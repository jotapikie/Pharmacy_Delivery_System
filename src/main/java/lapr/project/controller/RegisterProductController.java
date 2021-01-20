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
    private final Set<Product> products;

    public RegisterProductController(ProductDB productDB) {
        this.pdb=productDB;
        products = new HashSet<>();
    }

    
    public String newProduct(String name, double weight, double price) {
        pro = pdb.newProduct(name,weight,price);
        return pro.toString();
    }

    public boolean addToQueue(){
        if(pro != null){
            return products.add(pro);
        }
        return false;
    }

    public int registProduct() throws SQLException {
        if(!products.isEmpty()){
            return pdb.saveProducts(products);
        }
        return 0;

    }

    
    
    
    
}
