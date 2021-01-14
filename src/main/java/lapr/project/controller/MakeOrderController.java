/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import lapr.project.data.AddressDB;
import lapr.project.data.ClientDB;
import lapr.project.data.InvoiceDB;
import lapr.project.data.OrderDB;
import lapr.project.data.PharmacyDB;
import lapr.project.data.PharmacyStockDB;
import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.Invoice;
import lapr.project.model.Order;
import lapr.project.model.Pharmacy;
import lapr.project.model.Platform;
import lapr.project.model.Product;
import lapr.project.model.ShoppingCart;
import lapr.project.utils.Utils;


public class MakeOrderController {
    
    private final ClientDB cdb;
    private final AddressDB adb;
    private final OrderDB odb;
    private final InvoiceDB idb;
    private final PharmacyStockDB ppdb;
    private final PharmacyDB pharDB;
    private final String email;
    
    private Client cli;
    private ShoppingCart cart;
    private HashMap<Product, Integer> items;
    private final Platform plat;
    private Address add;
    private Pharmacy pha;
    private Order ord;
    private List<Pharmacy> pharmacies;
    private Invoice inv;
    
    private double totalPrice;
    private double discPrice;
    private double priceToPay;
    
    private int cred;
    private int creditsSpent;
    private int creditsWon;

    public MakeOrderController(ClientDB cdb, AddressDB adb, OrderDB odb,InvoiceDB idb,PharmacyStockDB ppdb,PharmacyDB phardb, String email) {
        this.cdb = cdb;
        this.adb = adb;
        this.odb = odb;
        this.idb = idb;
        this.ppdb = ppdb;
        this.pharDB = phardb;
        this.email = email;
        items = new HashMap<>();
        pharmacies = new ArrayList<>();
        plat = new Platform();
        totalPrice = 0;
        discPrice = 0;
        priceToPay = 0;
        cred = 0;
        creditsSpent = 0;
        creditsWon= 0;
    }
   
    public String getCart(){
        cli = cdb.getClient(email);
        cart = cli.getCart();
        items = cart.getItems();
        totalPrice = plat.getDeliveryPrice() + cart.getPrice();
        return cart.toString();
    }
    
    public String getAddress(){
        add = adb.getAddressByClient(email);
        return add.toString();
    }
    
    public int getDefaultNif(){
        return cli.getNif();
    }
    
    public int getCredits(){
        cred = cli.getPoints();
        discPrice = totalPrice - (cred/plat.getCreditsPerEuro());
        priceToPay = totalPrice;
        return cred;
    }
    
    public void discount(){
        priceToPay = discPrice;
        creditsSpent = cred;
    }
    
    public double getFinalPrice(){
        return priceToPay;
    }
    
    public boolean makeOrder(int nif) throws SQLException{
        cli.setPoints(cli.getPoints() - creditsSpent);
        ord = odb.newOrder(totalPrice, items);
        creditsWon = (int) (totalPrice * plat.getCreditsWonPerEuroSpent());
        cli.setPoints(cli.getPoints() + creditsWon);
        inv = idb.newInvoice(cli, add, items, totalPrice, priceToPay, creditsSpent, creditsWon, nif);
        pharmacies = pharDB.getPharmaciesWithAdresses();
        pha = nearestPharmacies(add).get(0);
        boolean success = checkPharmacyStock();
        if(success){
            sendEmail();
        }
        return success;
    }
    
    private TreeMap<Double, Pharmacy> nearestPharmacies(Address a){    
        TreeMap<Double, Pharmacy> distancesToPharmacies = new TreeMap<>();
        for(Pharmacy p : pharmacies){
            //double dist = Utils.distance(totalPrice, totalPrice, totalPrice, totalPrice, totalPrice, totalPrice)
            //distancesToPharmacies.put(dist, p);
        
        
        }
        return distancesToPharmacies;
    }
    
    private boolean checkPharmacyStock() throws SQLException{
        
        HashMap<Product, Integer> missingProducts = new HashMap<>();
        HashMap<Product, Integer> existingProducts = new HashMap<>();
        for(Product p : items.keySet()){
            int quantity = ppdb.getQuantity(pha.getId(), p.getId());
            int missing = items.get(p) - quantity;
            if(missing > 0){
                missingProducts.put(p, missing);
                existingProducts.put(p, items.get(p) - missing);
            }
        }
        
        if(missingProducts.isEmpty()){
            ord.setStatus("Processed");
            odb.saveOrderProcessed(ord, email, pha.getId(), inv);
            return true;
        }else{
            ord.setProducts(existingProducts);
            Order orderToFulfill = odb.newOrder(ord, missingProducts);
            List<Pharmacy> nearbyPharmacies = (List<Pharmacy>) nearestPharmacies(pha.getAddress()).values();
            for(Pharmacy nearPha : nearbyPharmacies){
                if(ppdb.hasProducts(missingProducts, nearPha.getId())){
                    odb.saveOrderProcessing(orderToFulfill, email, nearPha.getId(), pha.getId(), inv);
                    return true;
                }
            }
            return false;
        }
        
    }
    
    private void sendEmail(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
 
    
    

    
    

    

}
    