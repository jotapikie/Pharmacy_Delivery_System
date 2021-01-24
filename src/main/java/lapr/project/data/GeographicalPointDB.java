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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.GeographicalPoint;
import lapr.project.utils.Utils;
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
                GeographicalPoint p = new GeographicalPoint(rs.getDouble(1), rs.getDouble(2), rs.getDouble(3), rs.getString(4));
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
                p = new GeographicalPoint(rs.getDouble(1), rs.getDouble(2), rs.getDouble(3), rs.getString(4));
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
                GeographicalPoint p = new GeographicalPoint(rs.getDouble(1), rs.getDouble(2), rs.getDouble(3), rs.getString(4));
                listPoints.add(p);
            }
        }
        return listPoints;
    }

    public GeographicalPoint getGeographicalPoint(double longitude, double latitude) throws SQLException {
        if (Math.abs(longitude) > 90 || Math.abs(latitude) > 180) {
            throw new IllegalArgumentException("Invalid cordenates");
        }
        GeographicalPoint p = null;
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetGeographicalPoint(?,?)}")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setDouble(2, longitude);
            callStmt.setDouble(3, latitude);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                p = new GeographicalPoint(rs.getDouble(1), rs.getDouble(2), rs.getDouble(3), rs.getString(4));
                return p;
            }
            
        }
        return p;
    }

    public GeographicalPoint newGeographicalPoint(double longitude, double latitude, double elevation, String description) {
       return new GeographicalPoint(longitude, latitude, elevation, description);
    }

    public int savePoints(Set<GeographicalPoint> points) throws SQLException {
        Connection c = getConnection();
        int rows[];
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procAddPoint(?,?,?,?) }")) {
            for (GeographicalPoint point : points) {
                callStmt.setDouble(1, point.getLongitude());
                callStmt.setDouble(2, point.getLatitude());
                callStmt.setDouble(3, point.getElevation());
                callStmt.setString(4, point.getDescription());


                callStmt.addBatch();
            }
            c.setAutoCommit(false);
            try {
                rows = callStmt.executeBatch();
                c.commit();
            } catch (BatchUpdateException e) {
                c.rollback();
                throw new SQLException(e.getNextException());
            } finally {
                c.setAutoCommit(true);
            }
            return rows.length;
        }
    }

 
}
