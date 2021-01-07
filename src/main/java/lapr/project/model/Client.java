/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.List;
import lapr.project.data.ClientDB;

/**
 *
 * @author Diogo
 */
public class Client {

    private int phoneNumber;
    private int credits;
    private Address adress;
    private ShoppingCart cart;
    private List<Order> orders;
    
    
     public static Client getClient(String email) {
         return new ClientDB().getClient(email);
    }

    public ShoppingCart getCart() {
        return cart;
    }


    
    
}
