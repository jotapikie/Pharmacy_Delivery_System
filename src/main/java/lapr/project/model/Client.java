/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diogo
 */
public class Client extends User {
    private String name;
    private int nif;
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
    public Client(String username,String name, String password, String email,int nif, CreditCard creditCard, Address address) {
        super(username, email, password);
        setName(name);
        setNif(nif);
        setPoints();
        setCard(creditCard);
        setAddress(address);
        cart = new ShoppingCart();
    }


    public CreditCard getCard() {
        return card;
    }

    public void setCard(CreditCard card) {
        this.card = card;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getNif() {
        return nif;
    } // returns the client's nif

    public int getPoints() {
        return points;
    } // returns the client's points

    public void setName(String name) {
        if (name==null||name.isEmpty()) throw new IllegalArgumentException("Invalid Client Name");
        this.name=name;
    }
    public String getName(){
        return name;
    }

    public ShoppingCart getCart() {
        return cart;
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
    void setPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("Client's points is invalid.");
        }
        this.points = points;
    }





}






    
    

