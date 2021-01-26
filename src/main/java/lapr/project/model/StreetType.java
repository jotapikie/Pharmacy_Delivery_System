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
public enum StreetType {
    ASPHALT("Asphalt"), OFF_ROAD("Off-Road"), SIDEWALK("Sidewalk"), PARALELO("Paralelo");
    
    private final String name; 

    public String getName() {
        return name;
    }
  
    private StreetType(String name) 
    { 
        this.name = name; 
    } 
    
     public static StreetType fromString(String text) {
        for (StreetType b : StreetType.values()) {
            if (b.getName().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
