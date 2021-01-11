/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
package lapr.project.controller;

import java.util.HashMap;
import lapr.project.data.AddressDB;
import lapr.project.data.ClientDB;
import lapr.project.data.OrderDB;
import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.Invoice;
import lapr.project.model.Order;
import lapr.project.model.Phamarcy;
import lapr.project.model.Platform;
import lapr.project.model.Product;
import lapr.project.model.ShoppingCart;

*//**
 *
 * @author Diogo
 *//*
public class MakeOrderController {
    
    private ClientDB cdb;
    private AddressDB adb;
    private OrderDB odb;
    private String email;
    private Client cli;
    private ShoppingCart cart;
    private HashMap<Product, Integer> items;
    private double totalPrice = 0;
    private double discPrice = 0;
    private double priceToPay = 0;
    private int cred = 0;
    private Platform plat;
    private Address add;
    

    public MakeOrderController(ClientDB cdb, AddressDB adb,OrderDB odb, String email) {
        this.cdb = cdb;
        this.adb = adb;
        this.odb = odb;
        this.email = email;
    }
    
    public String getCart(){
        cli = cdb.getClient(email);
        cart = cli.getCart();
        items = cart.getItems();
        totalPrice = cart.getPrice() + plat.getDeliveryPrice();
        return cart.toString() + totalPrice;
    }
    
    public String getAddress(){
        add = adb.getAddressByClient(cli.getEmail());
        return add.toString();
    }
    
    public int getDefaultNif(){
        return cli.getNif();
    }
    
    public int getCredits(){
        cred = cli.getPoints();
        discPrice = plat.getDiscountedPrice(cred, totalPrice);
        priceToPay = totalPrice;
        return cred;
    }
    
    public void discount(){
        priceToPay = discPrice;
    }
    
    public double getFinalPrice(){
        return priceToPay;
    }
    
    public void makeOrder(int nif, int id){
        Order ord = cli.makeOrder(id, totalPrice, items);
        Invoice inv = ord.makeInvoice(cli, add, priceToPay, nif);
        Phamarcy pha = cli.getNeareastPhamarcy();
        pha.addOrder(ord);
        plat.sendEmail(cli.getEmail(), inv.toString());
        odb.saveOrder(ord, inv, pha.getId());
    }


    
    

    
    
    
}*/
