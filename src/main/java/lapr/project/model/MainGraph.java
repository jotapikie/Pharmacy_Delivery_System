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
public class MainGraph {

//
//    /**
//     * Static instance of the main graph.
//     */
//    private static GeographicalPointDB locationDB;
//
//    /**
//     * Static instance of the main graph.
//     */
//    private static PathwayDB pathDB;
//
//
//
//    /**
//     * Static method to setup the data accesses of the main graph (implemented
//     * to allow for unit testing).
//     * @param newLocationDB
//     * @param newPathDB
//     */
//    public static void setup(GeographicalPointDB newLocationDB, PathwayDB newPathDB) {
//        locationDB = (newLocationDB != null) ? newLocationDB : new GeographicalPointDB();
//        pathDB = (newPathDB != null) ? newPathDB : new PathwayDB();
//    }
//
//
//
//    /**
//     * Main graph of locations connected by paths.
//     */
//    private final Graph<GeographicalPoint, Pathway> graph = new Graph<>(true);
//
//    /**
//     * Constructs an instance of the main graph.
//     * @param gpdb
//     * @param pdb
//     */
//    public MainGraph() throws SQLException {
//        
//        setup(locationDB, pathDB);
//        List<GeographicalPoint> vertexes = locationDB.getGeographicalPoints();
//        List<Pathway> edges = pathDB.getPaths();
//
//        for (GeographicalPoint vertex : vertexes) {
//            if (!graph.insertVertex(vertex)) {
//                throw new IllegalArgumentException("Invalid graph Vertex!");
//            }
//        }
//        for (Pathway edge : edges) {
//            if (!graph.insertEdge(edge.getOriginPoint(), edge.getDestinationPoint(), edge, edge.getCost())) {
//                throw new IllegalArgumentException("Invalid graph Edge!");
//            }
//        }
//    }
//
//    /**
//     * Returns an iterable of the vertexes of the main graph.
//     *
//     * @return iterable of the vertexes of the main graph.
//     */
//    public Iterable<GeographicalPoint> getVertexes() {
//        return graph.vertices();
//    }
//
//    /**
//     * Returns an iterable of the edges of the main graph.
//     *
//     * @return iterable of the edges of the main graph.
//     */
//    public Iterable<Pathway> getEdges() {
//        List<Pathway> edges = new ArrayList<>();
//        for (Edge<GeographicalPoint,? extends Pathway> e : graph.edges()) {
//            edges.add(e.getElement());
//        }
//        return edges;
//    }
//
//
//    
//    
//
//
//
//
//    /**
//     * Returns the graph to use for route algorithms.
//     *
//     * @return graph to use for route algorithms.
//     */
//    public Graph<GeographicalPoint, ? extends Pathway> getRouteGraph(){
//        return graph;
//    }

    
}
