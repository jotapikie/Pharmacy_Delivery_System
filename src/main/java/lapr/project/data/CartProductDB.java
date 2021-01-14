/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.BatchUpdateException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import lapr.project.model.Product;
import lapr.project.model.ShoppingCart;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Diogo
 */
public class CartProductDB extends DataHandler{
    
    


    
    public int saveShoppingCart(Map<Product, Integer> products, String clientEmail) throws SQLException {
        Connection con = getConnection();
        int[] rows;
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procAddToCart(?,?,?}")) {
            for (Product p : products.keySet()) {

                callStmt.setString(1, clientEmail);
                callStmt.setInt(2, p.getId());
                callStmt.setInt(3, products.get(p));
                
                callStmt.execute();
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
    

    public ShoppingCart getCart(String email) throws SQLException {
        HashMap<Product, Integer> products = new HashMap<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetCart(?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, email);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4));
                products.put(p, rs.getInt(5));
            }
        }
        return new ShoppingCart(products);
    }
    
}
