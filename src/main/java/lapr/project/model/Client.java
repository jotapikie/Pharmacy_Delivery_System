/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.utils.Constants;


/**
 *
 * @author Diogo
 */
public class Client extends User {
    private int nif;
    private int phoneNumber;
    private int points;
    private final ShoppingCart cart;
    private CreditCard card;
    private Address address;




    /**
     * Client's constructor to create instances of this class.
     *
     * @param username   - client's username
     * @param name   - client's name
     * @param password   - client's password
     * @param email      - client's email
     * @param nif        - client's nif
     * @param creditCard - client´s credit card
     * @param address    - client's address
     *
     */
    public Client(String name, String password, String email,int nif,int phoneNumber, CreditCard creditCard, Address address) {
        super(name, email, password);
        setNif(nif);
        setPoints();
        setCard(creditCard);
        setPhoneNumber(phoneNumber);
        setAddress(address);
        cart = new ShoppingCart();
    }
    
    public Client(String name, String pwd, String email, int phoneNumber, int credits, int nif, Address addres){
        super(name, email, pwd);
        this.phoneNumber = phoneNumber;
        this.nif = nif;
        this.points = credits;
        this.address = addres;
        cart = new ShoppingCart();
    }
    
    public Client(String name, String pwd, String email, ShoppingCart cart){
        super(name, email, pwd);
        this.cart = cart;
    }
    
    


    public CreditCard getCard() {
        return card;
    }

    public Address getAddress() {
        return address;
    }


    public int getNif() {
        return nif;
    } // returns the client's nif

    public int getPoints() {
        return points;
    } // returns the client's points



    public ShoppingCart getCart() {
        return cart;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }
    
    

    /**
     * Modifies the client's nif to the value passed by parameter.
     *
     * @param nif - client's new nif
     */
    void setNif(int nif) {
        if (String.valueOf(nif).length()!=9) {
            throw new IllegalArgumentException("Client's nif is invalid.");
        }
        this.nif = nif;
    }

    /**
     * Modifies the client's points to the value passed by parameter.
     *
     */
    void setPoints() {
        this.points = Constants.INITIAL_POINTS;
    }

    /**
     * Modifies the client's points to the value passed by parameter.
     *
     */
    public void setPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("Client's points is invalid.");
        }
        this.points = points;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    
    
    public void setCard(CreditCard card) {
        this.card = card;
    }

    public void setPhoneNumber(int phoneNumber) {
        if (String.valueOf(phoneNumber).length()!=9){
            throw new IllegalArgumentException("Phone number must have 9 digits");
        }
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
              // If the object is compared with itself then return true   
        if (o == this) { 
            return true; 
        } 
  
        /* Check if o is an instance of Complex or not 
          "null instanceof [type]" also returns false */
        if (!(o instanceof Client)) { 
            return false; 
        } 
          
        // typecast o to Complex so that we can compare data members  
        Client c = (Client) o; 
          
        // Compare the data members and return accordingly  
        return nif == c.nif;
    
    }

    @Override
    public int hashCode() {
        return nif;
    }
    
    
    
    
    

    



}






    
    

