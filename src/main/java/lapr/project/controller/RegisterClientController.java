package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lapr.project.data.ClientDB;
import lapr.project.model.Client;

public class RegisterClientController {

    private Client client; // client known by the controller

    private final ClientDB clientDB; // client database, used to add a new client to our database
    
    private final List<Client> clientsList;

    public RegisterClientController() { clientDB = new ClientDB(); clientsList = new ArrayList<>(); }

    public RegisterClientController(ClientDB clientDB) { this.clientDB = clientDB; clientsList = new ArrayList<>(); }

    /**
     * Creates a new instance of Client which is instanciated by the class ClientDB and is known by the controller
     * so it can then be saved in the database by the same class.
     *
     * @param username          - client's username
     * @param password          - client's password
     * @param email             - client's email
     * @param nif               - client's nif
     * @param points            - client's points
     * @param expDate           - credit card's expiracy date
     * @param visa              - credit card's visa card number
     * @param ccv               - credit card's ccv card number
     * @param address           - client's address
     * @param longitude         - client's longitude
     * @param latitude          - client's latitude
     * @return created client
     */
    public Client newClient(String username,String name, String password, String email, int nif, int points, Date expDate, long visa, int ccv, String address, double latitude, double longitude, String city, int portNumber, String zipCode) {
        client = clientDB.newClient(username,name,password,email,nif,points,expDate, visa, ccv, address, latitude, longitude, city, portNumber, zipCode);
        return client;
    }

    /*
     * Saves the client known by the controller in our database.
     */
    public boolean registerClient() throws SQLException {  return clientDB.saveClient(client);
    }

    public List<Client> getClientsList(){
        return new ArrayList<>(clientsList);
    }

    public boolean addClientToQueue(){
        return clientsList.add(client);
    }

    
    public int insertClientsBatchOp() throws SQLException {
        final int numRows = clientDB.saveClients(clientsList);
        clientsList.clear();
        return numRows;
    }
}