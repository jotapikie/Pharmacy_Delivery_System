package lapr.project.model;


import java.util.Objects;
import java.util.Set;

public class Park{
    private int id;
    private int nMaxVehicles;
    private VehicleCategory type;
    private double maxEnergy;
    private double currentEnergy;
    private Set<ParkSlot> slots;

    public Park(int id, int nMaxVehicles, VehicleCategory type, double maxEnergy, Set<ParkSlot> slots) {
        setId(id);
        setnMaxVehicles(nMaxVehicles);
        setType(type);
        setMaxEnergy(maxEnergy);
        setCurrentEnergy(maxEnergy);
        setSlots(slots);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id<=0)
            throw new IllegalArgumentException("Invalid park id");
        this.id = id;
    }

    public int getnMaxVehicles() {
        return nMaxVehicles;
    }

    public void setnMaxVehicles(int nMaxVehicles) {
        if(nMaxVehicles<=0)
            throw new IllegalArgumentException("Invalid parks nMaxVehicles");
        this.nMaxVehicles = nMaxVehicles;
    }
    
    public double getMaxEnergy(){
        return maxEnergy;
    }

    public double getCurrentEnergy() {
        return currentEnergy;
    }
    
    
    
    public void setMaxEnergy(double maxEnergy){
        this.maxEnergy=maxEnergy;
    }

    public VehicleCategory getType() {
        return type;
    }

    public void setType(VehicleCategory type) {
        this.type = type;
    }

    public void setCurrentEnergy(double currentEnergy) {
        this.currentEnergy = currentEnergy;
    }
    
    
 


    public Set<ParkSlot> getSlots() {
        return  slots;
    }

    public void setSlots(Set<ParkSlot> slots) {
//        if(slots == null || slots.size()>nMaxVehicles)
//            throw new IllegalArgumentException("Invalid park slots");
        this.slots = slots;
    }

    public void updatePark(int nMaxVehicles, VehicleCategory type, Set<ParkSlot> slots ){
        setnMaxVehicles(nMaxVehicles);
        setType(type);
        setSlots(slots);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return (super.equals(other));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }


    @Override
    public String toString(){
        return String.format("%d %d %s", getId(),getnMaxVehicles(),getType());
    }


}
