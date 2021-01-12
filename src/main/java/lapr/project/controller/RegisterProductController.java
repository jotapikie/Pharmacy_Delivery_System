/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.HashSet;
import lapr.project.data.ProductDB;
import lapr.project.model.Product;
/**
 *
 * @author Helder
 */
public class RegisterProductController {
    private ProductDB ProductDB;
    private Product pro;
    private HashSet<Product> products = new HashSet<>();

    public RegisterProductController(ProductDB productDB) {
        this.ProductDB=productDB;
    }

    public RegisterProductController() {
        this.ProductDB=new ProductDB();
    }
    
    public String newProduct(int id, String name, double weight, double price) {
        pro = ProductDB.newProduct(id,name,weight,price);
        return pro.toString();
    }

    public boolean addToQueue(){
        return products.add(pro);
    }

    public int registProduct() throws SQLException {
        final int i = ProductDB.saveProducts(products);
        products.clear();
        return i;
    }
    
    
    
    
    
}
