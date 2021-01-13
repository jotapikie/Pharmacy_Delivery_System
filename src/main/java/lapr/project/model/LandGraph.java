/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.sql.SQLException;
import java.util.List;
import lapr.project.utils.graph.Graph;
import lapr.project.utils.route.Route;
import lapr.project.utils.route.RouteAlgorithms;

/**
 *
 * @author Diogo
 */
public class LandGraph extends MainGraph{
    
    /**
     * Graph of locations connected by paths, considering energy efficiency.
     */
    private Graph<GeographicalPoint, VehiclePath> landGraph;

    /**
     * Total weight to use in energy calculations.
     */
    private int totalWeight;

    /**
     * Vehicle aerodynamic coefficient to use in energy calculations.
     */
    private double vehicleAerodynamicCoef;

    /**
     * Constructs an instance of the energy graph.
     *
     * @param totalWeight total weight of the vehicle and the person.
     * @param vehicleAerodynamicCoef aerodynamic coefficient of the vehicle.
     * @throws java.sql.SQLException
     */
    public LandGraph(int totalWeight, double vehicleAerodynamicCoef) throws SQLException {
        if (totalWeight <= 0 || vehicleAerodynamicCoef <= 0) {
            throw new IllegalArgumentException("Invalid energy information!");
        }
        this.totalWeight = totalWeight;
        this.vehicleAerodynamicCoef = vehicleAerodynamicCoef;

        this.landGraph = new Graph<>(true);
        setupData();
        
        for (GeographicalPoint vertex : super.getVertexes()) {
            this.landGraph.insertVertex(vertex);
        }
        for (Pathway mainEdge : super.getEdges()) {
            VehiclePath energyEdge = new VehiclePath(mainEdge.getOriginPoint(), mainEdge.getDestinationPoint(), mainEdge.getDistance(),
                    mainEdge.getKineticCoef(), mainEdge.getWind(), totalWeight, vehicleAerodynamicCoef);
            landGraph.insertEdge(energyEdge.getOriginPoint(), energyEdge.getDestinationPoint(), energyEdge, energyEdge.getCost());
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
    @Override
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
    

    @Override
    public Graph<GeographicalPoint, VehiclePath> getRouteGraph() {
        return this.landGraph;
    }






}
