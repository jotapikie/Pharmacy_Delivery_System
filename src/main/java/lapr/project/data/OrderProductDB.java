/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.SQLException;

/**
 *
 * @author Diogo
 */
public class OrderProductDB extends DataHandler{

    public void saveOrderProduct(int idOrder, int idProduct, Integer quantity) throws SQLException {
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procSaveOrderProduct(?,?,?) }")) {

                callStmt.setInt(1, idOrder);
                callStmt.setInt(2, idProduct);
                callStmt.setInt(3, quantity);

                

                callStmt.execute();

        }
    }
    
}
