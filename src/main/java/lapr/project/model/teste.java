/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.sql.SQLException;
import lapr.project.data.ClientDB;
import lapr.project.data.ProductDB;

/**
 *
 * @author Diogo
 */
public class teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        ClientDB cdb = new ClientDB();
        System.out.println(cdb.getClientPoints("clien1@lapr3"));
        
        ProductDB pdb = new ProductDB();
        pdb.saveProduct(new Product(4, "Brufen", 0.45, 5.99));
    }
    
}
