package lapr.project.model;

public enum State {
    LOCKED("Locked"), CHARGING("Charging"), INACTIVE("Inactive"), ACTIVE("Active");
    
    private final String name; 

    public String getName() {
        return name;
    }
  
    private State(String name) 
    { 
        this.name = name; 
    } 
    
     public static State fromString(String text) {
        for (State b : State.values()) {
            if (b.getName().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
