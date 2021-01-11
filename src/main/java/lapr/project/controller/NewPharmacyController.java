package lapr.project.controller;

import lapr.project.data.PharmacyDB;
import lapr.project.model.Address;
import lapr.project.model.Park;
import lapr.project.model.Pharmacy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lapr.project.model.Administrator;

public class NewPharmacyController {

    private Pharmacy pharmacy;
    private PharmacyDB pharmacyDB;
    private HashSet<Pharmacy> pharmaciesList = new HashSet<>();


    public NewPharmacyController() {
        pharmacyDB = new PharmacyDB();
    }

    public NewPharmacyController( PharmacyDB pharmacyDB) {
        this.pharmacyDB = pharmacyDB;
    }

    public Pharmacy newPharmacy(int id, int phoneNumber, String name, Administrator admin, Address address, HashSet<Park> parks) {
        pharmacy = pharmacyDB.newPhamarcy(id, phoneNumber,name,admin,address,parks);
        return pharmacy;
    }

    public boolean registerPharmacy() throws SQLException {
        return pharmacyDB.save(pharmacy);
    }

    public boolean addPharmacyToQueue(){
        return pharmaciesList.add(pharmacy);
    }

    public void clearList(){
        pharmaciesList.clear();
    }

    public Set<Pharmacy> getPharmacyList(){
        return new HashSet<>(pharmaciesList);
    }

    public int insertPharmacyBatchOp() throws SQLException{
        final int numReturn = pharmacyDB.save(pharmaciesList);
        pharmaciesList= new HashSet<>();
        return numReturn;
    }
}
