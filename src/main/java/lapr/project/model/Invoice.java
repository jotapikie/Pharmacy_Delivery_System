/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashMap;

/**
 *
 * @author ivoal
 */
public class Invoice {
    
    private Client client;
    private Address address;
    private double pricePaid;
    private double orderPrice;
    private HashMap<Product, Integer> items;
    private int nif;

    public Invoice(Client client, Address address, double pricePaid, double orderPrice, HashMap<Product, Integer> items, int nif) {
        this.client = client;
        this.address = address;
        this.pricePaid = pricePaid;
        this.orderPrice = orderPrice;
        this.items = items;
        this.nif = nif;
    }
    
    
    
    
}
