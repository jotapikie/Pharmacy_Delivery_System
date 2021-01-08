/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.HashSet;
import lapr.project.data.ProductDB;
import lapr.project.data.VehicleDB;
import lapr.project.model.Product;
import lapr.project.model.Vehicle;
/**
 *
 * @author Helder
 */
public class AddProductController {
    private ProductDB ProductDB;
    private Product product;
    private HashSet<Product> products = new HashSet<>();

    public AddProductController(ProductDB productDB) {
        this.ProductDB=productDB;
    }

    public AddProductController() {
        this.ProductDB=new ProductDB();
    }
    
    public Product newProduct(int id, String name, double weight, double price) {
        product = ProductDB.newProduct(id,name,weight,price);
        return product;
    }

    public boolean registerProduct() throws SQLException {
        return ProductDB.save(product);
    }

    public void addProductToQueue() {
        products.add(product);
    }

    public HashSet<Product> getProduct() {
        return new HashSet<>(products);
    }

    public int insertProductBatchOp() throws SQLException {
        final int i = ProductDB.save(products);
        products = new HashSet<>();
        return i;
    }
    
    
    
    
    
}
