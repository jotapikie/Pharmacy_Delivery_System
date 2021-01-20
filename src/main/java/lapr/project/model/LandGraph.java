/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.GeographicalPointDB;
import lapr.project.data.PathwayDB;
import lapr.project.utils.graph.Edge;
import lapr.project.utils.graph.Graph;
import lapr.project.utils.route.Route;
import lapr.project.utils.route.RouteAlgorithms;

/**
 *
 * @author Diogo
 */
public class LandGraph{
    
      /**
     * Static instance of the main graph.
     */
    private static GeographicalPointDB locationDB;

    /**
     * Static instance of the main graph.
     */
    private static PathwayDB pathDB;



    /**
     * Static method to setup the data accesses of the main graph (implemented
     * to allow for unit testing).
     * @param newLocationDB
     * @param newPathDB
     */
    public static void setup(GeographicalPointDB newLocationDB, PathwayDB newPathDB) {
        locationDB = (newLocationDB != null) ? newLocationDB : new GeographicalPointDB();
        pathDB = (newPathDB != null) ? newPathDB : new PathwayDB();
    }

    
    /**
     * Graph of locations connected by paths, considering energy efficiency.
     */
    private Graph<GeographicalPoint, VehiclePath> graph;




    /**
     * Constructs an instance of the energy graph.
     *
     * @param totalWeight total weight of the vehicle and the person.
     * @param vehicleAerodynamicCoef aerodynamic coefficient of the vehicle.
     * @throws java.sql.SQLException
     */
    public LandGraph(double totalWeight, double vehicleAerodynamicCoef) throws SQLException {
        if (totalWeight <= 0 || vehicleAerodynamicCoef <= 0) {
            throw new IllegalArgumentException("Invalid energy information!");
        }

        setup(locationDB, pathDB);
        List<GeographicalPoint> vertexes = locationDB.getGeographicalPoints();
        List<Pathway> edges = pathDB.getPaths();

        this.graph = new Graph<>(true);
        
        
        for (GeographicalPoint vertex : vertexes) {
            this.graph.insertVertex(vertex);

        }
        for (Pathway mainEdge : edges) {
            VehiclePath energyEdge = new VehiclePath(mainEdge.getOriginPoint(), mainEdge.getDestinationPoint(), mainEdge.getDistance(),
                    mainEdge.getKineticCoef(), mainEdge.getWind(), totalWeight, vehicleAerodynamicCoef, mainEdge.getStreet());
            graph.insertEdge(energyEdge.getOriginPoint(), energyEdge.getDestinationPoint(), energyEdge, energyEdge.getCost());
         
        }
    }
    
 

    /**
     * Calculates the k shortest routes from origin to destination parks,
     * considering energy efficiency.
     *
     * @param origin origin park.
     * @param destination destination park.
     * @param k number of routes to calculate.
     * @return k shortest routes.
     */
    public List<Route> kBestPaths(GeographicalPoint origin, GeographicalPoint destination, int k) {
        if (origin == null || destination == null || origin.equals(destination) || k <= 0) {
            throw new IllegalArgumentException("Invalid algorithm arguments!");
        }
        try {
            return RouteAlgorithms.kBestRoutes(this, origin, destination, k);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Invalid graph vertexes!");
        }
    }
    
    
    
    public List<Route> kBestPaths(List<GeographicalPoint> toVisit, GeographicalPoint origin, GeographicalPoint destination, int k) {
        if (origin == null || destination == null || toVisit == null || toVisit.contains(origin) || toVisit.contains(destination) || k <= 0) {
            throw new IllegalArgumentException("Invalid algorithm arguments!");
        }
        try {
            return RouteAlgorithms.kBestRoutes(this, toVisit, origin, destination, k, Integer.MAX_VALUE);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("There is no way to reach at least on of the geographical points.");
        }
    }
    
    public List<Route> kBestPaths(List<GeographicalPoint> toVisit, GeographicalPoint origin, GeographicalPoint destination, int k, int maxBattery) {
        if (origin == null || destination == null || toVisit == null || toVisit.contains(origin) || toVisit.contains(destination) || k <= 0) {
            throw new IllegalArgumentException("Invalid algorithm arguments!");
        }
        try {
            return RouteAlgorithms.kBestRoutes(this, toVisit, origin, destination, k, maxBattery);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("There is no way to reach at least on of the geographical points.");
        }
    }
    

    public Graph<GeographicalPoint, VehiclePath> getRouteGraph() {
        return this.graph;
    }








}
