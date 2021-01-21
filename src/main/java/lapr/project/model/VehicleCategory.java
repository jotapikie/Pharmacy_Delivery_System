/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author Diogo
 */
public enum VehicleCategory {
    SCOOTER("Scooter"), DRONE("Drone");
    
    private final String name; 

    public String getName() {
        return name;
    }
  
    private VehicleCategory(String name) 
    { 
        this.name = name; 
    } 
    
     public static VehicleCategory fromString(String text) {
        for (VehicleCategory b : VehicleCategory.values()) {
            if (b.getName().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
