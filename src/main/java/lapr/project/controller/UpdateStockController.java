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
import lapr.project.data.PharmacyStockDB;
import lapr.project.data.ProductDB;
import lapr.project.model.Product;
import lapr.project.utils.Utils;

/**
 *
 * @author Diogo
 */
public class UpdateStockController {
    
    private final ProductDB pdb;
    private final PharmacyStockDB ppdb;
    private final int idPharmacy;
    private Product pro;
    private final Map<Product, Integer> products;

    public UpdateStockController(ProductDB pdb, PharmacyStockDB ppdb, int idPharmacy) {
        this.pdb = pdb;
        this.ppdb = ppdb;
        this.idPharmacy = idPharmacy;
        products = new HashMap<>();
    }

    public UpdateStockController(int idPharmacy) {
        this.pdb = new ProductDB();
        this.ppdb = new PharmacyStockDB();
        this.idPharmacy = idPharmacy;
        this.products = new HashMap<>();
    }
    
    


    
    public List<String> getProducts() throws SQLException{
        return Utils.listToString(pdb.getProducts());
   }
    
    public String getSelectedProduct(int id) throws SQLException{
        pro = pdb.getProduct(id);
        return pro == null ? null : pro.toString();
    }
    
    public boolean addToQueue(int quantity){
        if(pro != null){
            return products.putIfAbsent(pro, quantity)==null;
        }
        return false;
    }
    
    
    public boolean updateStock(){
        if(!products.isEmpty()){
            return ppdb.updateStock(idPharmacy, products);
        }
        return false;
    }
    
    
    
    
}
