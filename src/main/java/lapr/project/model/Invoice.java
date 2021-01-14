/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Map;

/**
 *
 * @author Diogo
 */
public class Invoice {
    
    private String name;
    private Address address;
    private Map<Product, Integer> productsBought;
    private double totalPrice;
    private double pricePaid;
    private int creditsWon;
    private int creditsSpent;
    private int currentCredits;
    private int nif;
    private Timestamp date;

    public Invoice(Client cli,  Address address, Map<Product, Integer> productsBought, double totalPrice, double pricePaid, int creditsWon, int creditsSpent, int nif) {
        this.name = cli.getName();
        this.address = address;
        this.productsBought = productsBought;
        this.totalPrice = totalPrice;
        this.pricePaid = pricePaid;
        this.creditsWon = creditsWon;
        this.creditsSpent = creditsSpent;
        this.currentCredits = cli.getPoints();
        this.nif = nif;
        this.date = Timestamp.from(Instant.now());
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public int getCreditsSpent() {
        return creditsSpent;
    }

    public int getCreditsWon() {
        return creditsWon;
    }

    public int getCurrentCredits() {
        return currentCredits;
    }
    
    

    public Timestamp getDate() {
        return date;
    }

    public int getNif() {
        return nif;
    }

    public double getPricePaid() {
        return pricePaid;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    
    
    
    
    
    


    
    
    
}
