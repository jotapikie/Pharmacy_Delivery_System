/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import lapr.project.model.Client;
import oracle.jdbc.OracleTypes;

import java.sql.BatchUpdateException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import lapr.project.model.Address;
import lapr.project.model.GeographicalPoint;

/**
 *
 * @author Diogo
 */
public class ClientDB extends DataHandler {

    /**
     * Creates a new client with attributes passed by parameter so it can be
     * then saved in our database.
     *
     * @param name              - client's name
     * @param password          - client's password
     * @param email             - client's email
     * @param nif               - client's nif
     * @param points            - client's points
     * @param expDate           - credit card's expiracy date
     * @param visa              - credit card's visa card number
     * @param ccv               - credit card's ccv card number
     * @param address           - client's address
     * @param latitude          - client's latitude
     * @param longitude         - client's longitude
     * @return client's instance
     */
    public Client newClient(String username, String name, String password, String email, int nif, int points, Date expDate, long visa, int ccv, String address, double latitude, double longitude, String city,int portNumber, String zipCode) {
        //return new Client(username,name,password,email,nif,new CreditCard(visa, expDate, ccv), new Address(address,latitude, longitude,city,portNumber,zipCode));
      return null;
    }

    /**
     * Saves the client passed by parameter in our database.
     *
     * @param c - instance of client that will be saved.
     * @return true if we're able to add the client, false if we're not
     * @throws java.sql.SQLException
     */
    public boolean saveClient(Client c) throws SQLException {
        getConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procRegisterClient(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }")) {

                callStmt.setString(1, c.getName());
                callStmt.setString(2, c.getEmail());
                callStmt.setString(3, c.getPassword());
                callStmt.setInt(4, c.getNif());
                callStmt.setInt(5, c.getPoints());
                callStmt.setString(6, c.getCard().getExpDate().toString());
                callStmt.setLong(7,c.getCard().getVisaNumber());
                callStmt.setInt(8, c.getCard().getCcv());
                callStmt.setString(9, c.getAddress().getStreet());
                callStmt.setDouble(10, c.getAddress().getGeographicalPoint().getLatitude());
                callStmt.setDouble(11,c.getAddress().getGeographicalPoint().getLongitude());
                callStmt.setString(12,c.getAddress().getZipCode());
                callStmt.setInt(13,c.getAddress().getPortNumber());
                callStmt.setString(14,c.getAddress().getZipCode());


            callStmt.execute();
        }
        return true;
    }


    /**
     * Method to get the points of the client which's username is passed by parameter.
     *
     * @param username - username of the client whom's points are being returned.
     * @return client's points.
     */
    public int getClientPoints(String username) throws SQLException {
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetClientPoints(?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setString(2, username);
            callStmt.execute();

            return callStmt.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("No info in the database. Check table Clients.");
    }

    public int saveClients(List<Client> clientsList) throws SQLException {
        Connection con = getConnection();
        int[] rows;
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procRegisterClient(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }")) {
            for (Client c : clientsList) {
                callStmt.setString(1, c.getName());
                callStmt.setString(2, c.getEmail());
                callStmt.setString(3, c.getPassword());
                callStmt.setInt(4, c.getNif());
                callStmt.setInt(5, c.getPoints());
                callStmt.setString(6, c.getCard().getExpDate().toString());
                callStmt.setLong(7,c.getCard().getVisaNumber());
                callStmt.setInt(8, c.getCard().getCcv());
                callStmt.setString(9, c.getAddress().getStreet());
                callStmt.setDouble(10, c.getAddress().getGeographicalPoint().getLatitude());
                callStmt.setDouble(11,c.getAddress().getGeographicalPoint().getLongitude());
                callStmt.setString(12,c.getAddress().getZipCode());
                callStmt.setInt(13,c.getAddress().getPortNumber());
                callStmt.setString(14,c.getAddress().getZipCode());

                callStmt.addBatch();
            }

            con.setAutoCommit(false);

            try {
                rows = callStmt.executeBatch();
                con.commit();
            } catch (BatchUpdateException e) {
                con.rollback();
                throw new SQLException(e.getNextException());
            } finally {
                con.setAutoCommit(true);
            }

            return rows.length;
        }

    }
    public Client getClient(String email) throws SQLException {
        Client c = null;
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetClient(?)}")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, email);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                c = new Client(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), new Address(rs.getString(7), new GeographicalPoint(rs.getFloat(8), rs.getFloat(9), rs.getFloat(10)), rs.getString(11), rs.getInt(12), rs.getString(13)));
                return c;
            }
            throw new IllegalArgumentException("Client does not exist");
        }
    }


    void updateAfterOrder(Client cli) throws SQLException {
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procUpdateClientAfterOrder(?,?)")) {
            

                callStmt.setString(1, cli.getEmail());
                callStmt.setInt(2, cli.getPoints());

                
                callStmt.execute();
            
        }
    }
    
}
