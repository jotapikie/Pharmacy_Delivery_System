/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.data.CartProductDB;
import lapr.project.data.ProductDB;
import lapr.project.model.Product;
import lapr.project.model.ShoppingCart;

/**
 *
 * @author Diogo
 */
public class RemoveFromCartController {
    
    private final String clientEmail;
    private ShoppingCart cart;
    private Product pro;
    private final CartProductDB cdb;
    private final ProductDB pdb;

    public RemoveFromCartController(String clientEmail, CartProductDB cdb, ProductDB pdb) {
        this.clientEmail = clientEmail;
        this.cdb = cdb;
        this.pdb = pdb;
    }
    
    public String getProductsInCart() throws SQLException{
           cart = cdb.getCart(clientEmail);
           return (cart.getItems().isEmpty()) ? null : cart.toString();
    }
    
    public String getSelectedProduct(int id) throws SQLException{
        pro = pdb.getProduct(id);
        return (pro == null || !cart.getItems().containsKey(pro)) ? null : pro.toString();
    }
    
    public boolean removeFromCart() throws SQLException{
        if(pro != null && cart.getItems().containsKey(pro)){
             cdb.removeProduct(pro, clientEmail);
            return true;
        }
        return false;
    }
    
    
    
}
