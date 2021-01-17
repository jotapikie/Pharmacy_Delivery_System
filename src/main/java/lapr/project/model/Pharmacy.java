/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Diogo
 */
public class Pharmacy {
    private int id;
    private int phoneNumber;
    private String name;
    private Administrator administrator;
    private Address address;
    private HashSet<Park> parks= new HashSet<Park>();


    public Pharmacy(int id, int phoneNumber, String name, Administrator administrator, Address address, HashSet<Park> parks) {
        setId(id);
        setPhoneNumber(phoneNumber);
        setName(name);
        setAdministrator(administrator);
        setAddress( address);
        setParks(parks);
  
    }
    
    public Pharmacy(int id, int phoneNumber, String name, Administrator ad, Address add){
        setId(id);
        setPhoneNumber(phoneNumber);
        setName(name);
        setAdministrator(ad);
        setAddress(add);
    }


    /**
     * method to get the Id of the pharmacy
     *
     * @return
     */
    public int getId() {
        return id;
    }
    
    public int getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getName() {
        return name;
    }

    public Administrator getAdministrator() {
        return administrator;
    }
    
    
    public Address getAddress() {
        return address;
    }
    
    
    public HashSet<Park> getParks() {
        return parks;
    }

    /**
     * method to set the Id of the pharmacy
     *
     * @param id
     */
    public final void setId(int id) {
        if(id<=0)
            throw new IllegalArgumentException("Invalid pharmacy id");
        this.id = id;
    }

    public final void setName(String name){
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("A pharmacy must have a valid name");
        }
        this.name=name;
    }


    public void setPhoneNumber(int phoneNumber) {
        if (String.valueOf(phoneNumber).length()!=9){
            throw new IllegalArgumentException("Phone number must have 9 digits");
        }
        this.phoneNumber = phoneNumber;
    }



    void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }


    public void setAddress(Address address) {
        this.address = address;
    }


    public void setParks(HashSet<Park> parks) {
        if(parks == null){
            throw new IllegalArgumentException("Invalid pharmacy parks.");
        }
        this.parks = parks;
    }

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pharmacy other = (Pharmacy) obj;
        return this.id == other.getId();
        }

    @Override
    public String toString(){
        return String.format("%d %s  %d  %s  %s ", id, getName(), getPhoneNumber(), getAdministrator().toString(), getAddress().toString());
    }



  
    }




