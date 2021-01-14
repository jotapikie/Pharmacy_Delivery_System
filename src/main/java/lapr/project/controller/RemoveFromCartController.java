/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.data.ClientDB;
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
    private final ClientDB cdb;
    private final ProductDB pdb;

    public RemoveFromCartController(String clientEmail, ClientDB cdb, ProductDB pdb) {
        this.clientEmail = clientEmail;
        this.cdb = cdb;
        this.pdb = pdb;
    }
    
    public String getProductsInCart() throws SQLException{
           cart = cdb.getClient(clientEmail).getCart();
           return cart == null ? null : cart.toString();
    }
    
    public String getSelectedProduct(int id) throws SQLException{
        pro = pdb.getProduct(id);
        return pro == null ? null : (cart.getItems().keySet().contains(pro) ? pro.toString() : null);
    }
    
    public void removeFromCart(){
        if(pro != null){
            //cart.removeProduct(pro);
        }
    }
    
    
    
}
