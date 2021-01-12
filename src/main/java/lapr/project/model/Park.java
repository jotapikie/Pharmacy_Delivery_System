package lapr.project.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Park{
    private int id;
    private int nMaxVehicles;
    private String type;
    private Set<ParkSlot> slots;

    public Park(int id, int nMaxVehicles, String type, Set<ParkSlot> slots) {
        setId(id);
        setnMaxVehicles(nMaxVehicles);
        setType(type);
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (!type.equalsIgnoreCase("SCOOTER")) throw new IllegalArgumentException("Invalid park type");
        this.type=type;
    }

    public Set<ParkSlot> getSlots() {
        return  slots;
    }

    public void setSlots(Set<ParkSlot> slots) {
        if(slots.size()>nMaxVehicles|| slots.isEmpty())
            throw new IllegalArgumentException("Invalid park slots");
        this.slots = slots;
    }

    public void updatePark(int nMaxVehicles, String type, Set<ParkSlot> slots ){
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
