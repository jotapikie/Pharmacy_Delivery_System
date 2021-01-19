package lapr.project.data;

import java.sql.*;
import java.util.*;
import lapr.project.model.Drone;
import lapr.project.model.EScooter;
import lapr.project.model.State;
import lapr.project.model.Vehicle;
import oracle.jdbc.OracleTypes;

public class VehicleDB extends DataHandler {
    private static final String ESCOOTER = "escooter";
    private static final String DRONE = "drone";

    /**
     * Creates a new instance of EScooter
     *
     * @param id
     * @param weight
     * @param state
     * @param maxBat
     * @param currentBat
     * @param motor
     * @param aeroCoef
     * @param frontalArea
     * @return
     */
    public EScooter newEScooter(int id, State state, int maxBat, int currentBat) {
        return new EScooter(id,state,maxBat,currentBat);
    }
    
     public Vehicle newDrone(int id, int weight, State state, int maxBat, int actualBat, int motor) {
        return new Drone(id,weight,state,maxBat,actualBat,motor);
    }

    /**
     * Method to save a instance of bike in the dataBase
     *
     * @param v
     * @param pharmID
     * @return
     * @throws java.sql.SQLException
     */
    public boolean save(Vehicle v, int pharmID) throws SQLException {
            
        if (v instanceof Drone) {
            addVehicle(v, pharmID, DRONE);
        } else if (v instanceof EScooter) {
            addVehicle(v, pharmID, ESCOOTER);
        }
        return true;
    }

    public int save(Set<Vehicle> vehicles,int pharmID) throws SQLException {
        return addVehicles(vehicles,pharmID, ESCOOTER);
    }

    private void addVehicle(Vehicle v, int pharmID, String typeVehicle) throws SQLException {

        getConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procAddVehicle(?,?,?,?,?,?,?,?,?,?,?) }")) {

            callStmt.setInt(1, v.getId());
            callStmt.setDouble(2, v.getWeight());
            callStmt.setString(3, v.getState().toString());
            callStmt.setDouble(4, v.getMaxBat());
            callStmt.setDouble(5, v.getActualBat());
            callStmt.setDouble(6, v.getMotor());
            callStmt.setInt(7, pharmID);

            if (typeVehicle.equalsIgnoreCase(ESCOOTER)) {
                EScooter escooter = (EScooter) v;
                //parameter to actully set
                callStmt.setDouble(8, escooter.getAeroCoef());
                callStmt.setDouble(9, escooter.getFrontalArea());
            }
            callStmt.execute();
        }
    }

    public int addVehicles(Set<Vehicle> vehicles, int pharmID, String typeVehicle ) throws SQLException {

        Connection c = getConnection();
        int[] rows;
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procAddVehicle(?,?,?,?,?,?,?,?,?,?,?) }")) {

            for (Vehicle v : vehicles) {


                callStmt.setInt(1, v.getId());
                callStmt.setDouble(2, v.getWeight());
                callStmt.setString(3, v.getState().toString());
                callStmt.setDouble(4, v.getMaxBat());
                callStmt.setDouble(5, v.getActualBat());
                callStmt.setDouble(6, v.getMotor());
                callStmt.setInt(7, pharmID);

                if (typeVehicle.equalsIgnoreCase(ESCOOTER)) {
                    EScooter escooter = (EScooter) v;
                    //parameter to actully set
                    callStmt.setDouble(8, escooter.getAeroCoef());
                    callStmt.setDouble(9, escooter.getFrontalArea());
                }
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

    public Vehicle getVehicle(int id) throws SQLException {

        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetVehicle(?,?) }")) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.setInt(2, id);

            callStmt.execute();
                ResultSet rSet = (ResultSet) callStmt.getObject(1);
                if (rSet.next()) {
                    return new EScooter(rSet.getInt(1), State.valueOf(rSet.getString(3)),rSet.getInt(4), rSet.getInt(5));
                }
            }

        return null;
    }
    
    /**
     * Remove vehicle
     *
     * @param id
     * @return
     * @throws java.sql.SQLException
     */
    public boolean remove(int id) throws SQLException {

        try (CallableStatement callStmt = getConnection().prepareCall("{ call procRemoveVehicle(?) }")) {
            callStmt.setInt(1, id);
            callStmt.execute();
        }
        return true;
    }

    /**
     * Update vehicle
     *
     * @param vehicle
     * @return
     */
    public boolean update(Vehicle vehicle) throws SQLException {
        if (vehicle instanceof EScooter) {
            updateVehicle(vehicle, ESCOOTER);
        }
        return true;
    }

    private void updateVehicle(Vehicle v, String typeVehicle) throws SQLException {
        getConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procUpdateVehicle(?,?,?,?,?,?,?,?) }")) {

            callStmt.setInt(1, v.getId());
            callStmt.setDouble(2, v.getWeight());
            callStmt.setString(3, v.getState().toString());
            callStmt.setDouble(4, v.getMaxBat());
            callStmt.setDouble(5, v.getActualBat());
            callStmt.setDouble(6, v.getMotor());

            if (typeVehicle.equalsIgnoreCase(ESCOOTER)) {
                EScooter escooter = (EScooter) v;
                //parameter to actully set
                callStmt.setDouble(8, escooter.getAeroCoef());
                callStmt.setDouble(9, escooter.getFrontalArea());
            }

            callStmt.execute();
        }
    }

    /**
     * Method to get all the available escooters at a given park which's id is passed by parameter.
     *
     * @param pharmID - pharmacy's id.
     * @return available escooters.
     */
    public List<String> getEscootersAtPharm(int pharmID) throws SQLException {
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetAvailableEscootersAtPharmacy(?) }")) {
            ArrayList<String> eScooters = new ArrayList<>();

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, pharmID);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);

            if (rs == null) throw new NullPointerException("There are no vehicles in the pharmacy of id " + pharmID);
            else {
                while (rs.next()) {
                    eScooters.add(String.format("%s;%s;%d", rs.getString(1), rs.getString(2), rs.getInt(3)));
                }
            }
            return eScooters;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("No info in the database. Check tables Parks, Escooters, Vehicles and Parked_Vehicles.");
    }

    /**
     * Method to get all the available bicycles at a given park which's id is passed by parameter.
     *
     * @param parkID - park's id.
     * @return available bicycles.
     */
    public List<String> getScooterAtPark(String parkID) throws SQLException {
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetScooterAtPark(?) }")) {
            ArrayList<String> eScooters = new ArrayList<>();

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, parkID);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);

            if (rs == null) throw new NullPointerException("There are no parked vehicles in the park of id " + parkID);
            else {
                while (rs.next()) {
                    eScooters.add(String.format("%s;%d", rs.getString(1), rs.getInt(2)));
                }
            }
            return eScooters;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("No info in the database. Check tables Parks, Bicycles, Vehicles and Parked_Vehicles.");
    }

<<<<<<< HEAD
    public Vehicle newDrone(int id, int weight, State state, int maxBat, int actualBat, int motor) {
        return new Drone(id,weight,state,maxBat,actualBat,motor);
    }

    public List<EScooter> getAvailableScooters(int idPharmacy) throws SQLException {
        List<EScooter> listScooters = new ArrayList<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetAvailableScooters(?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, idPharmacy);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                EScooter sc = new EScooter(rs.getInt(1), State.fromString(rs.getString(2)), rs.getInt(3), rs.getInt(4));
                listScooters.add(sc);
            }
        }
        return listScooters;
    }


/**
    public Map<Integer, Set<EScooter>> checkIncapableEVehicles(int distance) throws SQLException {
        HashMap<Integer, Set<EScooter>> mapVehicles = new HashMap<>();
        HashSet<EScooter> setEScooters = new HashSet<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetEVehicles() }")) {
=======
   
>>>>>>> d71a4452d51904d5a6fe4658b10751cf7a658fdd

}
