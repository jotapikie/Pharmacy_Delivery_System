package lapr.project.model;

public class ParkSlot {
    private int id;
    private boolean ableToCharge;
    private EScooter scooter;

    public ParkSlot(int id, boolean ableToCharge, boolean inUse, EScooter scooter) {
        setId(id);
        setAbleToCharge(ableToCharge);
        setScooter(scooter);
    }

    
     public ParkSlot(int id, boolean ableToCharge, EScooter scooter) {
        setId(id);
        setAbleToCharge(ableToCharge);
        setScooter(scooter);

    }
    
    public ParkSlot(int id, boolean ableToCharge) {
        setId(id);
        setAbleToCharge(ableToCharge);
        scooter=null;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id<=0) throw new IllegalArgumentException("Invalid parkslot Id");
        this.id=id;
    }

    public boolean isAbleToCharge() {
        return ableToCharge;
    }

    public void setAbleToCharge(boolean ableToCharge) {
        this.ableToCharge = ableToCharge;
    }


    public EScooter getScooter() {
        return scooter;
    }

    public void setScooter(EScooter scooter) {
        this.scooter = scooter;
    }
}
