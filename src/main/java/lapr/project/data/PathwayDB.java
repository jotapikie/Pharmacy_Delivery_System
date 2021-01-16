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
import java.util.List;
import lapr.project.model.GeographicalPoint;
import lapr.project.model.Pathway;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Diogo
 */
public class PathwayDB extends DataHandler{
    

    public List<Pathway> getPaths() throws SQLException {
        List<Pathway> listPaths = new ArrayList<>();
        getConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetPaths() }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                Pathway p = new Pathway(new GeographicalPoint(rs.getFloat(1), rs.getFloat(2), rs.getFloat(3)), new GeographicalPoint(rs.getFloat(5), rs.getFloat(6), rs.getFloat(7)), rs.getFloat(9),  rs.getFloat(10), rs.getFloat(11));
                listPaths.add(p);
            }
        }
        return listPaths;
    }


    
    
    
}
