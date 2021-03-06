/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.route;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lapr.project.model.GeographicalPoint;
import lapr.project.model.Pathway;
import lapr.project.utils.Constants;
import lapr.project.utils.Utils;

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
    private double totalEnergy;

    /**
     * Total distance of the route.
     */
    private double totalDistance;
    
    
    private long totalTime;
    
    
    private double minimumEnergy;
    
    
    private double energyToReachChargingPoint;
    
    private double averageHorizontalSpeed;
    
    private double averageVerticalSpeed;
    
    
    private List<GeographicalPoint> stopPoints;
    
    
    private List<GeographicalPoint> chargingPoints;

    /**
     * Constructs a route that is a copy of another route.
     *
     * @param otherRoute the route to copy.
     */
    public Route(Route otherRoute) {
        this.totalEnergy = otherRoute.totalEnergy;
        this.totalDistance = otherRoute.totalDistance;
        this.totalTime = otherRoute.totalTime;
        this.minimumEnergy = otherRoute.minimumEnergy;
        this.paths = new ArrayList<>(otherRoute.paths);
        this.energyToReachChargingPoint = otherRoute.energyToReachChargingPoint;
        this.minimumEnergy = otherRoute.minimumEnergy;
        this.stopPoints = otherRoute.stopPoints;
        this.chargingPoints = otherRoute.chargingPoints;
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
        totalEnergy = startEdge.getCost();
        totalDistance = startEdge.getDistance();
        totalTime = startEdge.getTime();
        energyToReachChargingPoint = totalEnergy;
        minimumEnergy = totalEnergy;
        stopPoints = new LinkedList<>();
        chargingPoints = new LinkedList<>();
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
    public double getTotalEnergy() {
        return totalEnergy;
    }

    /**
     * Returns the total distance of the route.
     *
     * @return total distance.
     */
    public double getTotalDistance() {
        return totalDistance;
    }

    public long getTotalTime() {
        return totalTime;
    }
    
    

    public double getMinimumEnergy() {
        return minimumEnergy;
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

    public List<GeographicalPoint> getChargingPoints() {
        return new LinkedList<>(chargingPoints);
    }

    
    
    public boolean addStopPoint(GeographicalPoint stop){
        for(Pathway p : paths){
          if(p.getOriginPoint().equals(stop) || p.getDestinationPoint().equals(stop)){
              stopPoints.add(stop);
              return true;
          }
        }
        return false;
    }
    
    public boolean addStopPoints(List<GeographicalPoint> stop){
        int i = 0;
        for(Pathway p : paths){
            for(GeographicalPoint stopP : stop){
               if(p.getOriginPoint().equals(stopP) || p.getDestinationPoint().equals(stopP)){
                    stopPoints.add(stopP);
                    i++;
                }
            }
        }
        return i == stop.size();
    }

    public void setAverageSpeed(double averageSpeed) {
        if(averageSpeed < 0){
            throw new IllegalArgumentException("Speed must be a positive number.");
        }
        this.averageHorizontalSpeed = averageSpeed;
    }
    
    public void setVerticalSpeed(double averageSpeed){
        if(averageSpeed < 0){
            throw new IllegalArgumentException("Speed must be a positive number.");
        }
        this.averageVerticalSpeed= averageSpeed;
    }

    public double getAverageHorizontalSpeed() {
        return averageHorizontalSpeed;
    }

    public double getAverageVerticalSpeed() {
        return averageVerticalSpeed;
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
        this.totalEnergy += edge.getCost();
        this.totalDistance += edge.getDistance();
        this.totalTime += edge.getTime();
        this.energyToReachChargingPoint += edge.getCost();
        
        if(energyToReachChargingPoint > minimumEnergy){
            minimumEnergy = energyToReachChargingPoint;
        }
        if(edge.getDestinationPoint().getDescription().contains(Constants.CHARGING_SPOT)){
            energyToReachChargingPoint = 0;
            chargingPoints.add(edge.getDestinationPoint());
         }
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

    protected double getEnergyToReachChargingPoint() {
        return energyToReachChargingPoint;
    }
    
    public void updateValues(){
        Route newRoute = null;
        for(int i = 0; i < paths.size(); i++){
            Pathway p = paths.get(i);
            if(i == 0){
                newRoute = new Route(p);
            }else{
                newRoute.addPath(p);
            }
        }
        this.chargingPoints = newRoute.chargingPoints;
        this.energyToReachChargingPoint = newRoute.energyToReachChargingPoint;
        this.totalDistance = newRoute.totalDistance;
        this.totalEnergy = newRoute.totalEnergy;
        this.totalTime = newRoute.totalTime;
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
        if (this.totalEnergy < otherRoute.totalEnergy) {
            return -1;
        }
        if (this.totalEnergy > otherRoute.totalEnergy) {
            return 1;
        }
        if (this.totalDistance < otherRoute.totalDistance) {
            return -1;
        }
        if (this.totalDistance > otherRoute.totalDistance) {
            return 1;
        }
        if (this.totalTime < otherRoute.totalTime) {
            return -1;
        }
        if (this.totalTime > otherRoute.totalTime) {
            return 1;
        }
        if (this.stopPoints.size() < otherRoute.stopPoints.size()) {
            return -1;
        }
        if (this.stopPoints.size() > otherRoute.stopPoints.size()) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Pathway> it = paths.iterator();
        
        for(int i = 0; i < paths.size(); i++){
            Pathway p = it.next();
            GeographicalPoint or = p.getOriginPoint();
            GeographicalPoint dest = p.getDestinationPoint();
            String sOr;
            String sDest;
            if(i == 0){
                sOr = String.format("%s (%.5f,%.5f) [Origin]",or.getDescription(), or.getLatitude(), or.getLongitude());
                sDest = String.format("%s (%.5f,%.5f) %s",dest.getDescription(), dest.getLatitude(), dest.getLongitude(),(dest.getDescription().contains(Constants.CHARGING_SPOT)?"[Charge]":stopPoints.contains(dest) ? "[Order]": ""));
                
            }else{
                if(i == paths.size() -1){
                    sOr = String.format("%s (%.5f,%.5f) %s",or.getDescription(), or.getLatitude(),or.getLongitude(),(or.getDescription().contains(Constants.CHARGING_SPOT)?"[Charge]": stopPoints.contains(or) ? "[Order]":""));
                    sDest = String.format("%s (%.5f,%.5f) [Destination]",dest.getDescription(), dest.getLatitude(), dest.getLongitude());
                    
                }else{
                    sOr = String.format("%s (%.5f,%.5f) %s",or.getDescription(), or.getLatitude(),or.getLongitude(),(or.getDescription().contains(Constants.CHARGING_SPOT)?"[Charge]": stopPoints.contains(or) ? "[Order]":""));
                    sDest = String.format("%s (%.5f,%.5f) %s",dest.getDescription(), dest.getLatitude(),dest.getLongitude(),(dest.getDescription().contains(Constants.CHARGING_SPOT)?"[Charge]":stopPoints.contains(dest) ? "[Order]": ""));
                    
                }
            }
            String sDist = String.format("%.2fm",p.getDistance());
            String sEner = String.format("%.2f%s", p.getCost(),averageVerticalSpeed==0?"kWh":"mAh");
            sb.append(String.format("%-55s -> %s %-60s  | %-28s | %-10s | %-10s %n", sOr,"   ", sDest, p.getStreet()==null?"Air":p.getStreet(), sDist, sEner));
        }
        String sDist = String.format("%.2fm",totalDistance);
        String sEner = String.format("%.2f%s", totalEnergy, averageVerticalSpeed==0?"kWh":"mAh");
        
        String hSpeed = String.format("| %s: %.2f %s", averageVerticalSpeed== 0 ? "Average Speed": "Average Horizontal Speed",averageHorizontalSpeed,averageVerticalSpeed== 0 ? "km/h": "m/s");
        String vSpeed = String.format("| Average Vertical Speed: %.2f m/s", averageVerticalSpeed);
        if(averageVerticalSpeed == 0){
            vSpeed = null;
        }
        
        sb.append(String.format("Total Distance: %s | Total Energy: %s | Total Time: %s %s %s%n", sDist, sEner, Utils.secondsToTime(totalTime), hSpeed, vSpeed == null ? "": vSpeed));
        return sb.toString();
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
        return this.totalEnergy == other.totalEnergy
                && this.totalDistance == other.totalDistance
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
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.totalEnergy) ^ (Double.doubleToLongBits(this.totalEnergy) >>> 32));
        hash = (int) (43 * hash + this.totalDistance);
        return hash;
    }


}

