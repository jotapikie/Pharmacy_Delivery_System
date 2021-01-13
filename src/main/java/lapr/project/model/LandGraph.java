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
public class LandGraph {
    /**
     * Static instance of the main graph.
     */
    private static LandGraph instance = null;

    /**
     * Static instance of the main graph.
     */
    private static GeographicalPointDB locationDB = null;

    /**
     * Static instance of the main graph.
     */
    private static PathwayDB pathDB = null;

    /**
     * Static method to get or create an instance of the main graph.
     *
     * @throws java.sql.SQLException
     */
    public static LandGraph getInstance() throws SQLException {
        if (instance == null) {
            instance = new LandGraph();
        }
        return instance;
    }

    /**
     * Static method to reset the main graph.
     */
    public static void resetInstance() {
        instance = null;
    }

    /**
     * Static method to setup the data accesses of the main graph (implemented
     * to allow for unit testing).
     */
    public static void setup(GeographicalPointDB newLocationDB, PathwayDB newPathDB) {
        locationDB = (newLocationDB != null) ? newLocationDB : new GeographicalPointDB();
        pathDB = (newPathDB != null) ? newPathDB : new PathwayDB();
    }

    /**
     * Static method to erase the setup of data accesses of the main graph
     * (implemented to allow for unit testing).
     */
    public static void eraseSetup() {
        locationDB = null;
        pathDB = null;
    }

    /**
     * Main graph of locations connected by paths.
     */
    private final Graph<GeographicalPoint, Pathway> mainGraph;

    /**
     * Constructs an instance of the main graph.
     */
    private LandGraph() throws SQLException {
        mainGraph = new Graph<>(true);
        setup(locationDB, pathDB);
        List<GeographicalPoint> vertexes = locationDB.getGeographicalPoints();
        List<Pathway> edges = pathDB.getPaths();

        for (GeographicalPoint vertex : vertexes) {
            if (!mainGraph.insertVertex(vertex)) {
                throw new IllegalArgumentException("Invalid graph Vertex!");
            }
        }
        for (Pathway edge : edges) {
            if (!mainGraph.insertEdge(edge.getOriginPoint(), edge.getDestinationPoint(), edge, edge.getEnergy())) {
                throw new IllegalArgumentException("Invalid graph Edge!");
            }
        }
    }

    /**
     * Returns an iterable of the vertexes of the main graph.
     *
     * @return iterable of the vertexes of the main graph.
     */
    public Iterable<GeographicalPoint> getVertexes() {
        return mainGraph.vertices();
    }

    /**
     * Returns an iterable of the edges of the main graph.
     *
     * @return iterable of the edges of the main graph.
     */
    public Iterable<Pathway> getEdges() {
        List<Pathway> edges = new ArrayList<>();
        for (Edge<GeographicalPoint, Pathway> e : mainGraph.edges()) {
            edges.add(e.getElement());
        }
        return edges;
    }

    /**
     * Calculates the k shortest routes from origin to destination.
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
            return RouteAlgorithms.kBestRoutes(this,origin,destination, k);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Invalid graph vertexes!");
        }
    }



    /**
     * Returns the graph to use for route algorithms.
     *
     * @return graph to use for route algorithms.
     */
    public Graph<GeographicalPoint, Pathway> getRouteGraph() {
        LandGraph temp;
        try {
            LandGraph.resetInstance();
            temp = LandGraph.getInstance();
        } catch (SQLException e) {
            return null;
        }
        return temp.mainGraph;
    }
}
