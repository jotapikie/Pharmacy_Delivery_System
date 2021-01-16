/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import lapr.project.model.Product;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Diogo
 */
public class OrderProductDB extends DataHandler{

    public void saveOrderProduct(int idOrder, int idProduct, Integer quantity) throws SQLException {
        getConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procSaveOrderProduct(?,?,?) }")) {

                callStmt.setInt(1, idOrder);
                callStmt.setInt(2, idProduct);
                callStmt.setInt(3, quantity);

                callStmt.execute();

        }
    }

    public HashMap<Product, Integer> getProductsByOrder(int id) throws SQLException {
        HashMap<Product, Integer> products = new HashMap<>();
        getConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetProductsByOrder(?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, id);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                    products.put(new Product(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4)), rs.getInt(5));
            }
        }
        return products;
    }
    
}
