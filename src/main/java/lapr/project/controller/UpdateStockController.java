/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.PhamarcyProductDB;
import lapr.project.data.ProductDB;
import lapr.project.model.Product;

/**
 *
 * @author Diogo
 */
public class UpdateStockController {
    
    private ProductDB pdb;
    private PhamarcyProductDB ppdb;
    private String administratorEmail;
    private Product pro;

    public UpdateStockController( String administratorEmail) {
        this.pdb = new ProductDB();
        this.ppdb = new PhamarcyProductDB();
        this.administratorEmail = administratorEmail;
    }
    
    public List<String> getProducts() throws SQLException{
        List<String> prds = new ArrayList<>();
        for(Product p : pdb.getProducts()){
            prds.add(p.toString());
        }
        return prds;
    }
    
    public String getSelectedProduct(int id) throws SQLException{
        pro = pdb.getProduct(id);
        return pdb== null ? null : pro.toString();
    }
    
    public boolean updateStock(int quantity){
        if(pro == null){
            return false;
        }
        
        ppdb.updateStock(administratorEmail, pro.getId(), quantity);
        return true;
    }
    
    
    
    
}
