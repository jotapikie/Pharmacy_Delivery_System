/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import lapr.project.model.Product;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Diogo
 */
public class ProductDB extends DataHandler{

    public Product newProduct(int id, String name, double weight, double price){
        return new Product(id,  name, weight, price);
    }

    public Product getProduct(int id) throws SQLException {
         if (id < 1) {
            throw new IllegalArgumentException("Invalid ID!");
        }
        Product p = null;
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetProduct(?)}")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, id);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                p = new Product(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4));
                return p;
            }
            throw new IllegalArgumentException("Product does not exist");
        }
    }

    public List<Product> getProducts() throws SQLException {
        List<Product> listProducts = new ArrayList<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetProducts() }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4));
                listProducts.add(p);
            }
        }
        return listProducts;
    }

    public boolean save(Product product)throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int save(HashSet<Product> products) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
