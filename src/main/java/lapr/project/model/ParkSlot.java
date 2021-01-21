package lapr.project.model;

public class ParkSlot {
    private int id;
    private boolean ableToCharge;
    private Vehicle vehicle;

    public ParkSlot(int id, boolean ableToCharge, Vehicle scooter) {
        setId(id);
        setAbleToCharge(ableToCharge);
        setVehicle(scooter);
    }

    public ParkSlot(int id, boolean ableToCharge) {
        setId(id);
        setAbleToCharge(ableToCharge);
    }
    
    

    public int getId() {
        return id;
    }
    
    public boolean isAbleToCharge() {
        return ableToCharge;
    }
    
    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setId(int id) {
    if (id<=0) throw new IllegalArgumentException("Invalid parkslot Id");
        this.id=id;
    }

   public void setAbleToCharge(boolean ableToCharge) {
        this.ableToCharge = ableToCharge;
    }


    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
