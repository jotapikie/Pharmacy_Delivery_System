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
import java.util.Map;
import java.util.TreeMap;
import lapr.project.data.CartProductDB;
import lapr.project.data.ClientDB;
import lapr.project.data.InvoiceDB;
import lapr.project.data.OrderDB;
import lapr.project.data.PharmacyDB;
import lapr.project.data.PharmacyStockDB;
import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.GeographicalPoint;
import lapr.project.model.Invoice;
import lapr.project.model.Order;
import lapr.project.model.Pharmacy;
import lapr.project.model.Platform;
import lapr.project.model.Product;
import lapr.project.model.ShoppingCart;
import lapr.project.utils.Utils;


public class MakeOrderController {
    
    private final ClientDB cdb;
    private final CartProductDB cpdb;
    private final OrderDB odb;
    private final InvoiceDB idb;
    private final PharmacyStockDB ppdb;
    private final PharmacyDB pharDB;
    private final String email;
    
    private Client cli;
    private Map<Product, Integer> items;
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

    public MakeOrderController(ClientDB cdb,CartProductDB cpdb, OrderDB odb,InvoiceDB idb,PharmacyStockDB ppdb,PharmacyDB phardb, String email) {
        this.cdb = cdb;
        this.cpdb = cpdb;
        this.odb = odb;
        this.idb = idb;
        this.ppdb = ppdb;
        this.pharDB = phardb;
        this.email = email;
        items = new HashMap<>();
        pharmacies = new ArrayList<>();
        totalPrice = 0;
        discPrice = 0;
        priceToPay = 0;
        cred = 0;
        creditsSpent = 0;
        creditsWon= 0;
    }
   
    public String getCart() throws SQLException{
        cli = cdb.getClient(email);
        ShoppingCart cart = cpdb.getCart(email);
        items = cart.getItems();
        double cartPrice = 0;
        double cartWeight = 0;
        for(Map.Entry<Product, Integer > p : items.entrySet()){
            cartPrice = cartPrice + (p.getKey().getPrice()*p.getValue());
            cartWeight = cartWeight + (p.getKey().getWeight()*p.getValue());
        }
        if(cartWeight > Platform.getMaxWeightPerOrder()){
            return null;
        }
        totalPrice = Platform.getDeliveryPrice() + cartPrice;
        
       
        return cart.toString();
    }
    
    public String getAddress(){
        add = cli.getAddress();
        return add.toString();
    }
    
    public int getDefaultNif(){
        return cli.getNif();
    }
    
    public int getCredits(){
        cred = cli.getPoints();
        discPrice = totalPrice - (cred/Platform.getCreditsPerEuro());
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
        cli.setPoints(cred - creditsSpent);
        ord = odb.newOrder(totalPrice, items);
        creditsWon = (int) (totalPrice * Platform.getCreditsWonPerEuroSpent());
        cli.setPoints(cli.getPoints() + creditsWon);
        inv = idb.newInvoice(cli, add, items, totalPrice, priceToPay, creditsSpent, creditsWon, nif);
        pharmacies = pharDB.getPharmacies();
        if(pharmacies.isEmpty()){
            return false;
        }
        pha = nearestPharmacies(add).values().iterator().next();
        pharmacies.remove(pha);
        boolean success = checkPharmacyStock();
        if(success){
            sendEmail();
        }
        return success;
    }
    
    private TreeMap<Double, Pharmacy> nearestPharmacies(Address a){    
        TreeMap<Double, Pharmacy> distancesToPharmacies = new TreeMap<>();
        GeographicalPoint cp = a.getGeographicalPoint();
        GeographicalPoint pp;
        for(Pharmacy p : pharmacies){
            pp = p.getAddress().getGeographicalPoint();
            double dist = Utils.distance(cp.getLatitude(), pp.getLatitude(), cp.getLongitude(), pp.getLongitude(), cp.getElevation(), pp.getElevation());
            distancesToPharmacies.put(dist, p);
        }
        return distancesToPharmacies;
    }
    
    private boolean checkPharmacyStock() throws SQLException{
        
        HashMap<Product, Integer> missingProducts = new HashMap<>();
        HashMap<Product, Integer> existingProducts = new HashMap<>();
        for(Map.Entry<Product, Integer> p : items.entrySet()){
            int quantity = ppdb.getQuantity(pha.getId(), p.getKey().getId());
            int missing = items.get(p) - quantity;
            if(missing > 0){
                missingProducts.put(p.getKey(), missing);
                existingProducts.put(p.getKey(), items.get(p) - missing);
            }
        }
        
        if(missingProducts.isEmpty()){
            ord.setStatus("Processed");
            odb.saveOrderProcessed(ord, cli, pha.getId(), inv);
            return true;
        }else{
            ord.setProducts(existingProducts);
            Order orderToFulfill = odb.newOrder(ord, missingProducts);
            for(Pharmacy nearPha : nearestPharmacies(pha.getAddress()).values()){
                if(ppdb.hasProducts(missingProducts, nearPha.getId())){
                    odb.saveOrderProcessing(orderToFulfill, cli, nearPha.getId(), pha.getId(), inv);
                    return true;
                }
            }
            return false;
        }
        
    }
    
    private void sendEmail(){
       Utils.sendEmail(cli.getEmail(), "Order #" +ord.getId() , inv==null? "Your order is being processed.":inv.toString());
    }
    
 
    
    

    
    

    

}
    