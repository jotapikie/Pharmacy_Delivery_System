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
import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.GeographicalPoint;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Diogo
 */
public class GeographicalPointDB extends DataHandler{

    public List<GeographicalPoint> getGeographicalPoints() throws SQLException {
        List<GeographicalPoint> listPoints = new ArrayList<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetGeographicalPoints() }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                GeographicalPoint p = new GeographicalPoint(rs.getFloat(1), rs.getFloat(2), rs.getFloat(3));
                listPoints.add(p);
            }
        }
        return listPoints;
    }

    public GeographicalPoint getGeographicalPointByPharmacy(int idPharmacy) throws SQLException {
        GeographicalPoint p = null;
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetGeographicalPointByPharmacy(?)}")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, idPharmacy);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                p = new GeographicalPoint(rs.getFloat(1), rs.getFloat(2), rs.getFloat(3));
                return p;
            }
            throw new IllegalArgumentException("Client does not exist");
        }
    }

    public List<GeographicalPoint> getPointsByDeliveryRun(int id) throws SQLException {
        List<GeographicalPoint> listPoints = new ArrayList<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetPointsByDeliveryRun(?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, id);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                GeographicalPoint p = new GeographicalPoint(rs.getFloat(1), rs.getFloat(2), rs.getFloat(3));
                listPoints.add(p);
            }
        }
        return listPoints;
    }
    
}
