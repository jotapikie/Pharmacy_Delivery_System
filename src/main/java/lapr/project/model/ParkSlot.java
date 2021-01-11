package lapr.project.model;

public class ParkSlot {
    private int id;
    private boolean ableToCharge;
    private boolean inUse;
    private EScooter scooter;

    public ParkSlot(int id, boolean ableToCharge, boolean inUse, EScooter scooter) {
        setId(id);
        setAbleToCharge(ableToCharge);
        setInUse(inUse);
        setScooter(scooter);
    }

    public ParkSlot(int id, boolean ableToCharge, boolean inUse) {
        setId(id);
        setAbleToCharge(ableToCharge);
        setInUse(inUse);
        scooter=null;
    }
    
     public ParkSlot(int id, boolean ableToCharge, EScooter scooter) {
        setId(id);
        setAbleToCharge(ableToCharge);
        setInUse(true);
        setScooter(scooter);

    }
    
    public ParkSlot(int id, boolean ableToCharge) {
        setId(id);
        setAbleToCharge(ableToCharge);
        inUse = false;
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

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public EScooter getScooter() {
        return scooter;
    }

    public void setScooter(EScooter scooter) {
        this.scooter = scooter;
    }
}
