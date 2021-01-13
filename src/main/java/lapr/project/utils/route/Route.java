/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.route;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lapr.project.model.GeographicalPoint;
import lapr.project.model.Pathway;

/**
 *
 * @author Diogo
 */
public class Route implements Comparable<Route>{

    /**
     * List of the edges of the route.
     */
    private List<Pathway> paths;

    /**
     * Total cost of the route.
     */
    private double routeCost;

    /**
     * Total distance of the route.
     */
    private double routeDistance;

    /**
     * Constructs a route that is a copy of another route.
     *
     * @param otherRoute the route to copy.
     */
    public Route(Route otherRoute) {
        this.routeCost = otherRoute.routeCost;
        this.routeDistance = otherRoute.routeDistance;
        this.paths = new ArrayList<>(otherRoute.paths);
    }

    /**
     * Constructs a route given a start edge,
     *
     * @param startEdge first edge of the route.
     */
    public Route(Pathway startEdge) {
        buildRoute(startEdge);
    }

    /**
     * Builds the initial route.
     *
     * @param startEdge first edge of the route.
     */
    private void buildRoute(Pathway startEdge) {
        if (startEdge == null) {
            throw new IllegalArgumentException("Invalid start Edge!");
        }
        paths = new ArrayList<>();
        paths.add(startEdge);
        routeCost = startEdge.getCost();
        routeDistance = startEdge.getDistance();
    }

    /**
     * Returns the list of the edges of the route.
     *
     * @return list of edges.
     */
    public List<Pathway> getPaths() {
        return new ArrayList<>(paths);
    }

    /**
     * Returns the total cost of the route.
     *
     * @return total cost.
     */
    public double getRouteCost() {
        return routeCost;
    }

    /**
     * Returns the total distance of the route.
     *
     * @return total distance.
     */
    public double getRouteDistance() {
        return routeDistance;
    }

    /**
     * Returns the first vertex of the route.
     *
     * @return first vertex.
     */
    public GeographicalPoint getOrigin() {
        return paths.get(0).getOriginPoint();
    }

    /**
     * Returns the last vertex of the route.
     *
     * @return last vertex.
     */
    public GeographicalPoint getDestination() {
        return paths.get(paths.size() - 1).getDestinationPoint();
    }

    /**
     * Returns the number of vertexes on the route (including repeated
     * vertexes).
     *
     * @return number of vertexes.
     */
    public int getNumGeographicalPoints() {
        return paths.size() + 1;
    }

  
    /**
     * Adds an edge to the route, verifying the route's integrity.
     *
     * @param edges edge to add.
     */
    public void addPaths(List<Pathway> edges) {
        if (edges == null) {
            throw new IllegalArgumentException("Invalid List!");
        }
        if (!edges.isEmpty()) {
            for (Pathway edge : edges) {
                addPath(edge);
            }
        }
    }

    /**
     * Adds an edge to the route, verifying the route's integrity.
     *
     * @param edge edge to add.
     */
    public void addPath(Pathway edge) {
        if (edge == null || !this.getDestination().equals(edge.getOriginPoint())) {
            throw new IllegalArgumentException("Invalid Edge!");
        }
        this.paths.add(edge);
        this.routeCost += edge.getCost();
        this.routeDistance += edge.getDistance();
    }

    /**
     * Checks if the route visits a given set of vertexes.
     *
     * @return true if the route contains all the vertexes to visit (or there
     * are no vertexes to visit); otherwise, false.
     */
    public boolean contains(Set<GeographicalPoint> toVisit) {
        if (toVisit == null) {
            throw new IllegalArgumentException("Invalid Set!");
        }
        if (toVisit.isEmpty()) {
            return true;
        }
        Set<GeographicalPoint> routeVertexes = new HashSet<>();
        routeVertexes.add(paths.get(0).getOriginPoint());
        for (Pathway edge : paths) {
            routeVertexes.add(edge.getDestinationPoint());
        }
        return routeVertexes.containsAll(toVisit);
    }

    /**
     * Compares this route to another route.
     *
     * @param otherRoute route to compare to.
     * @return -1 if this is a better route; 1 if this is a worst route; 0 if
     * the routes are equal.
     */
  
    @Override
    public int compareTo(Route otherRoute) {
        if (otherRoute == null) {
            throw new IllegalArgumentException("Invalid Route!");
        }
        if (this.routeCost < otherRoute.routeCost) {
            return -1;
        }
        if (this.routeCost > otherRoute.routeCost) {
            return 1;
        }
        if (this.routeDistance < otherRoute.routeDistance) {
            return -1;
        }
        if (this.routeDistance > otherRoute.routeDistance) {
            return 1;
        }
        if (this.paths.size() < otherRoute.paths.size()) {
            return -1;
        }
        if (this.paths.size() > otherRoute.paths.size()) {
            return 1;
        }
        if (this.paths.equals(otherRoute.paths)) {
            return 0;
        }
        /* If the routes are different but have the same cost, distance and size,
        default is -1 to privilege the route found first. */
        return -1;
    }

    /**
     * Checks if this route is equal to a given object.
     *
     * @param obj other object.
     * @return true if the route is equal to the object; otherwise, false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Route other = (Route) obj;
        return this.routeCost == other.routeCost
                && this.routeDistance == other.routeDistance
                && this.paths.equals(other.paths);
    }

    /**
     * Hash code of this route.
     *
     * @return hash code.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.paths);
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.routeCost) ^ (Double.doubleToLongBits(this.routeCost) >>> 32));
        hash = (int) (43 * hash + this.routeDistance);
        return hash;
    }


}

