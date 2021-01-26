package lapr.project.controller;


import java.sql.SQLException;
import lapr.project.data.AddressDB;
import lapr.project.data.ClientDB;
import lapr.project.data.CreditCardDB;
import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.CreditCard;

public class RegisterClientController {

    private Client client;
    private Address address;
    private CreditCard creditCard;

    private final ClientDB cdb;
    private final AddressDB adb;
    private final CreditCardDB ccdb;

    public RegisterClientController(ClientDB cdb, AddressDB adb, CreditCardDB ccdb) {
        this.cdb = cdb;
        this.adb = adb;
        this.ccdb = ccdb;
    }

    public RegisterClientController() {
        this.cdb = new ClientDB();
        this.adb = new AddressDB();
        this.ccdb = new CreditCardDB();
    }
    
    

    public String newAddress(String street, double longitude, double latitude, double elevation, String city, int portNumber, String zipCode, String desc){
        address = adb.newAdress(street, longitude, latitude, elevation, city, portNumber, zipCode, desc);
        return (address==null)? null : address.toString();
    }
    
    public String newCreditCard(long number, String validityDate, int ccv){
        creditCard = ccdb.newCreditCard(number, validityDate, ccv);
        return (creditCard==null) ? null : creditCard.toString();
    }
    
    public String newClient(String name, String email, String pwd, int nif, int phoneNumber){
        client = cdb.newClient(name, email,pwd, nif, phoneNumber, address, creditCard);
        return (client==null)?null:client.toString();
    }
    
    public boolean registClient() throws SQLException{
        if(client != null){
            return cdb.saveClient(client);
        }
        return false;
    }
    
}