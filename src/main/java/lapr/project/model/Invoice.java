/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.sql.Timestamp;
import java.text.DecimalFormat;
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

    public Invoice(Client cli, Address address, Map<Product, Integer> productsBought, double totalPrice, double pricePaid, int creditsWon, int creditsSpent, int nif) {
        setName(cli.getName());
        setAddress(address);
        setProductsBought(productsBought);
        setTotalPrice(totalPrice);
        setPricePaid(pricePaid);
        setCreditsWon(creditsWon);
        setCreditsSpent(creditsSpent);
        setCurrentCredits(cli.getPoints());
        setNif(nif);
        setDate(Timestamp.from(Instant.now()));
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

    public Map<Product, Integer> getProductsBought() {
        return productsBought;
    }
    
    

    public final void setName(String name) {
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("The name in invoice is invalid.");
        }
        this.name = name;
    }

    public final void setCreditsSpent(int creditsSpent) {
        if(creditsSpent < 0){
            throw new IllegalArgumentException("The number of credits spent is invalid.");
        }
        this.creditsSpent = creditsSpent;
    }

    public final void setCreditsWon(int creditsWon) {
        if(creditsWon<0){
            throw new IllegalArgumentException("The number of credits won is invalid.");
        }
        this.creditsWon = creditsWon;
    }

    public final void setCurrentCredits(int currentCredits) {
        if(currentCredits < 0){
            throw new IllegalArgumentException("The number of current credits is invalid");
        }
        this.currentCredits = currentCredits;
    }

    public final void setDate(Timestamp date) {
        if(date == null){
             throw new IllegalArgumentException("The invoice date is invalid.");
        }
        this.date = date;
    }

    public final void setNif(int nif) {
        if(String.valueOf(nif).length()!= 9){
            throw new IllegalArgumentException("The nif is invalid.");
        }
        this.nif = nif;
    }

    public final void setPricePaid(double pricePaid) {
        if(pricePaid < 0 || pricePaid > totalPrice){
            throw new IllegalArgumentException("The price paid is invalid");
        }
        this.pricePaid = pricePaid;
    }

    public final void setTotalPrice(double totalPrice) {
        if(totalPrice < 0){
            throw new IllegalArgumentException("The total price is invalid.");
        }
        this.totalPrice = totalPrice;
    }

    public final void setProductsBought(Map<Product, Integer> productsBought) {
        if(productsBought == null || productsBought.isEmpty() ){
            throw new IllegalArgumentException("The products bought are invalid.");
        }
        this.productsBought = productsBought;
    }

    public final void setAddress(Address address) {
        if(address == null){
            throw new IllegalArgumentException("The address is invalid");
        }
        this.address = address;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String s = String.format("1. PERSONAL INFORMATION%n%nName: %s%nNIF: %d%nAddress: %s, %s, %d, %s%n %n2. ORDER INFORMATION%n%nDate: %s%n%n",name, nif, address.getStreet(), address.getCity(), address.getPortNumber(), address.getZipCode(), date.toString().substring(0, 19));
        sb.append(s);
        String head = String.format("%-6s %-20s %-9s %-9s %-10s%n%s%n", "Id", "Product", "Quantity", "Price/Un", "Price", "--------------------------------------------------------");
        sb.append(head);
        int quantity = 0;
        DecimalFormat df = new DecimalFormat(".##");
        
        for(Map.Entry<Product, Integer> p : productsBought.entrySet()){
            quantity = p.getValue();
            head = String.format("%-6s %-20s %-9s %-9s %-10s%n", String.valueOf(p.getKey().getId()), p.getKey().getName(), String.valueOf(quantity), String.valueOf(df.format(p.getKey().getPrice())), String.valueOf(df.format(p.getKey().getPrice()*quantity) ));
            sb.append(head);
        }
        head = String.format("%-6s %-20s %-9s %-9s %-10s%n"," " , "Delivery tax", String.valueOf(1), Platform.getDeliveryPrice(), Platform.getDeliveryPrice());
        sb.append(head);
        head = String.format("%47s %.2f%n%nTotal Price: %.2f???%n", " ", totalPrice,totalPrice);
        sb.append(head);
        if(totalPrice != pricePaid){
            head = String.format("Price Paid: %.2f??? (%d credits used)%n", pricePaid, creditsSpent);
            sb.append(head);
        }
        head = String.format("Credits Won: %d%nCurrent Credits: %d%n %n",creditsWon,  currentCredits);
        sb.append(head);
        return sb.toString();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
     
    
    
    
    
    


    
    
    
}
