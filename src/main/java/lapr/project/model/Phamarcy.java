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
public class Phamarcy {
    private int id;
    private int phoneNumber;
    private String name;
    private Administrator administrator;
    private Address address;
    private HashSet<Park> parks= new HashSet<Park>();
    private Set<Order> orders;
    private List<Courier> couriers;

    public Phamarcy(int id, int phoneNumber, String name, Administrator administrator, Address address, HashSet<Park> parks) {
        setId(id);
        setPhoneNumber(phoneNumber);
        setName(name);
        setAdministrator(administrator);
        setAddress( address);
        setParks(parks);
        orders = new TreeSet<>();
        couriers = new ArrayList<>();
    }


    /**
     * method to get the Id of the pharmacy
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * method to set the Id of the pharmacy
     *
     * @param id
     */
    public void setId(int id) {
        if(id<=0)
            throw new IllegalArgumentException("Invalid pharmacy id");
        this.id = id;
    }

    public void setName(String name){
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("A pharmacy must have a valid name");
        }
        this.name=name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        if (String.valueOf(phoneNumber).length()!=9) throw new IllegalArgumentException("Phone number must have 9 digits");
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    void setAdministrator(Administrator administrator) {
        if (administrator.getEmail() == null || administrator.getEmail().isEmpty() || !administrator.getEmail().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
            throw new IllegalArgumentException("Pharmacy's email is invalid.");
        }
        this.administrator = administrator;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public HashSet<Park> getParks() {
        return parks;
    }

    public void setParks(HashSet<Park> parks) {
        this.parks = parks;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
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
        final Phamarcy other = (Phamarcy) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return (super.equals(other));
        }

        @Override
        public String toString(){
            return String.format("%d %s  %d  %s  %s ", id, getName(), getPhoneNumber(), getAdministrator().toString(), getAddress().toString());
        }

    public List<Order> getSuggestedOrders(Courier cou) {
        List<Order> sOrders = new ArrayList<>();
        double totalWeight = 0;
        for(Order o : orders){
            if(o.getStatus().equals("Prepared")){
                double expWeight = totalWeight + o.getTotalWeight();
                if(expWeight < cou.getMaxWeight()){
                    sOrders.add(o);
                    totalWeight = expWeight;
                }
            }
        }
        
        return sOrders;
    }

    public void addOrder(Order ord) {
        ord.setStatus("Processed");
        orders.add(ord);
    }

    }




