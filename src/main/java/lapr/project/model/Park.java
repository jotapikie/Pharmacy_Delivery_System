package lapr.project.model;


import java.util.Set;

public class Park{
    private int id;
    private int maxVehicles;
    private VehicleCategory type;
    private double maxEnergy;
    private double currentEnergy;
    private Set<ParkSlot> slots;

    public Park(int id, int nMaxVehicles, VehicleCategory type, double maxEnergy, Set<ParkSlot> slots) {
        setId(id);
        setMaxVehicles(nMaxVehicles);
        setType(type);
        setMaxEnergy(maxEnergy);
        setCurrentEnergy(maxEnergy);
        setSlots(slots);
    }


    public int getId() {
        return id;
    }
    
    public int getMaxVehicles() {
        return maxVehicles;
    }
    
    public double getMaxEnergy(){
        return maxEnergy;
    }
    
    public double getCurrentEnergy() {
        return currentEnergy;
    }
    
    public VehicleCategory getType() {
        return type;
    }
    
    public Set<ParkSlot> getSlots() {
        return  slots;
    }

    public void setId(int id) {
        if(id<=0){
            throw new IllegalArgumentException("Invalid park id");
        }
        this.id = id;
    }



    public void setMaxVehicles(int maxVehicles) {
        if(maxVehicles<=0){
            throw new IllegalArgumentException("Invalid parks nMaxVehicles");
        }
        this.maxVehicles = maxVehicles;
    }
    


   public void setMaxEnergy(double maxEnergy){
        if(maxEnergy < 0){
            throw new IllegalArgumentException("Invalid parks max energy");
        }
        this.maxEnergy=maxEnergy;
    }
    
    public void setCurrentEnergy(double currentEnergy) {
        if(currentEnergy < 0 || currentEnergy > maxEnergy){
            throw new IllegalArgumentException("Invalid park current energy");
        }
        this.currentEnergy = currentEnergy;
    }


    public void setType(VehicleCategory type) {
        if(type == null){
            throw new IllegalArgumentException("Invalid park type");
        }
        this.type = type;
    }


    public void setSlots(Set<ParkSlot> slots) {
        if(slots == null || slots.size()> maxVehicles){
           throw new IllegalArgumentException("Invalid park slots"); 
        }
        this.slots = slots;
    }


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
        final Park other = (Park) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + id;
        return hash;
    }


    @Override
    public String toString(){
        return String.format("%d %d %s", getId(),getMaxVehicles(),getType());
    }


}
