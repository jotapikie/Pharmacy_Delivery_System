/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import lapr.project.model.Address;
import lapr.project.model.GeographicalPoint;

/**
 *
 * @author Diogo
 */
public class AddressDB {

    public Address newAdress(String street, double longitude, double latitude, double elevation, String city, int portNumber, String zipCode) {
        return new Address(street, new GeographicalPoint(longitude, latitude, elevation), city, portNumber, zipCode);
    }
    
}
