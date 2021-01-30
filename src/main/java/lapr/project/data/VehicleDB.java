package lapr.project.data;

import java.sql.*;
import java.util.*;
import lapr.project.model.Drone;
import lapr.project.model.EScooter;
import lapr.project.model.State;
import lapr.project.model.Vehicle;
import lapr.project.model.VehicleCategory;
import oracle.jdbc.OracleTypes;

public class VehicleDB extends DataHandler {
    private static final String ESCOOTER = "Scooter";
    private static final String DRONE = "Drone";

    /**
     * Creates a new instance of EScooter
     *
     * @param id
     * @param state
     * @param maxBat
     * @param currentBat
     * @return
     */
    public EScooter newEScooter(int id, State state, double maxBat, double currentBat) {
        return new EScooter(id,state,maxBat,currentBat);
    }
    
     public Vehicle newDrone(int id, State state, double maxBat, double currentBat) {
        return new Drone(id,state, maxBat, currentBat);
    }

 

    public int save(Set<Vehicle> vehicles,int pharmID) throws SQLException {
        int i = 0;
        for(Vehicle v : vehicles){
            addVehicle(v, pharmID);
            i++;
        }
        return i;
    }

    private void addVehicle(Vehicle v, int pharmID) throws SQLException {

        getConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procAddVehicle(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }")) {

            callStmt.setDouble(1, v.getWeight());
            callStmt.setString(2, v.getState().getName());
            callStmt.setDouble(3, v.getMaxBat());
            callStmt.setDouble(4, v.getCurrentBat());
            callStmt.setDouble(5, v.getMotor());
            callStmt.setDouble(6, v.getMaxWeight());
            callStmt.setInt(7, pharmID);
            
            String category;
            double aeroCoef, frontalArea,topArea,liftDrag,eleConsume,power;
            if(v instanceof EScooter){
                category = VehicleCategory.SCOOTER.getName();
                aeroCoef = EScooter.getAeroCoef();
                frontalArea = EScooter.getFrontalArea();
                topArea = -1;
                liftDrag = -1;
                eleConsume = -1;
                power = -1;
            }else{
                category = VehicleCategory.DRONE.getName();
                aeroCoef = Drone.getAeroCoef();
                frontalArea = Drone.getFrontalArea();
                topArea = Drone.getTopArea();
                liftDrag = Drone.getLiftDrag();
                eleConsume = Drone.getEletronicalConsumer();
                power = Drone.getPowerTransfer();
                
            }
            callStmt.setString(8, category);
            callStmt.setDouble(9, aeroCoef);
            callStmt.setDouble(10, frontalArea);
            callStmt.setDouble(11, topArea);
            callStmt.setDouble(12, liftDrag);
            callStmt.setDouble(13, eleConsume);
            callStmt.setDouble(14, power);
            
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
                callStmt.setString(3, v.getState().getName());
                callStmt.setDouble(4, v.getMaxBat());
                callStmt.setDouble(5, v.getCurrentBat());
                callStmt.setDouble(6, v.getMotor());
                callStmt.setDouble(7, v.getMaxWeight());
                callStmt.setInt(8, pharmID);

                if (typeVehicle.equalsIgnoreCase(ESCOOTER)) {
                    EScooter escooter = (EScooter) v;
                    //parameter to actully set
                    callStmt.setDouble(8, EScooter.getAeroCoef());
                    callStmt.setDouble(9, EScooter.getFrontalArea());
                } else if (typeVehicle.equalsIgnoreCase(DRONE)) {
                Drone drone = (Drone) v;
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

        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetVehicle(?) }")) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.setInt(2, id);

            callStmt.execute();
                ResultSet rSet = (ResultSet) callStmt.getObject(1);
                if (rSet.next()) {
                    return new EScooter(rSet.getInt(1), State.fromString(rSet.getString(2)),rSet.getDouble(3), rSet.getDouble(4));
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
    public boolean remove(int id, int idPharmacy) throws SQLException {

        try (CallableStatement callStmt = getConnection().prepareCall("{ call procRemoveVehicle(?,?) }")) {
            callStmt.setInt(1, id);
            callStmt.setInt(2, idPharmacy);
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
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procUpdateVehicle(?,?,?,?,?) }")) {

            callStmt.setInt(1, v.getId());
            callStmt.setDouble(2, v.getWeight());
            callStmt.setString(3, v.getState().toString());
            callStmt.setDouble(4, v.getMaxBat());
            callStmt.setDouble(5, v.getCurrentBat());


            callStmt.execute();
        }
    }
    
    public List<Vehicle> getAvailableScooters(int idPharmacy) throws SQLException{
        List<Vehicle> vehicles = new ArrayList<>();
        for(Vehicle v : getScooters(idPharmacy)){
            if(v.getState().equals(State.LOCKED) || v.getState().equals(State.CHARGING)){
                vehicles.add(v);
            }
        }
        return vehicles;
    }
    
    public List<Vehicle> getAvailableDrones(int idPharmacy) throws SQLException{
        List<Vehicle> vehicles = new ArrayList<>();
        for(Vehicle v : getDrones(idPharmacy)){
            if(v.getState().equals(State.LOCKED) || v.getState().equals(State.CHARGING)){
                vehicles.add(v);
            }
        }
        return vehicles;
    }
    
    public List<Vehicle> getVehicles(int idPharmacy) throws SQLException{
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.addAll(getScooters(idPharmacy));
        vehicles.addAll(getDrones(idPharmacy));
        return vehicles;
    }
    
    




  

    public List<Vehicle> getScooters(int idPharmacy) throws SQLException {
        List<Vehicle> listScooters = new ArrayList<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetScooters(?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, idPharmacy);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                EScooter sc = new EScooter(rs.getInt(1), State.fromString(rs.getString(2)), rs.getDouble(3), rs.getDouble(4));
                listScooters.add(sc);
            }
        }
        return listScooters;
    }

    public List<Vehicle> getDrones(int idPharmacy) throws SQLException {
        List<Vehicle> listDrones = new ArrayList<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetDrones(?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, idPharmacy);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                Drone dr = new Drone(rs.getInt(1), State.fromString(rs.getString(2)), rs.getDouble(3), rs.getDouble(4));
                listDrones.add(dr);
            }
        }
        return listDrones;
    }







}
