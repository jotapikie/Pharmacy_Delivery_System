/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author Diogo
 */
public class Client extends User {

    private String email;
    private String name;
    private int nif;
    private int points;
    private ShoppingCart cart;
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
     * @param points     - client's points
     * @param creditCard - client´s credit card
     * @param address    - client's address
     *
     */
    public Client(String username,String name, String password, String email,int nif,int points, CreditCard creditCard, Address address) {
        super(username, password);
        setName(name);
        setEmail(email);
        setNif(nif);
        setPoints(points);
        setCard(creditCard);
        setAddress(address);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name=name;
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



    public String getEmail() {
        return email;
    } // returns the client's email

    public int getNif() {
        return nif;
    } // returns the client's nif

    public int getPoints() {
        return points;
    } // returns the client's points


    /**
     * Modifies the client's email to the value passed by parameter.
     *
     * @param email - client's new email
     */
    void setEmail(String email) {
        if (email == null || email.isEmpty() || !email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
            throw new IllegalArgumentException("Client's email is invalid.");
        }
        this.email = email;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    /**
     * Modifies the client's nif to the value passed by parameter.
     *
     * @param nif - client's new height
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
     * @param points - client's new weight
     */
    void setPoints(int points) {
        if (points <= 0) {
            throw new IllegalArgumentException("Client's points is invalid.");
        }
        this.points = points;
    }



}






    
    

