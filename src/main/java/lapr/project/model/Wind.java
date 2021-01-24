package lapr.project.model;

public class Wind {
    public double vx;
    public double vy;
    public double vz;

    public Wind(double vx, double vy, double vz){
        this.vx=vx;
        this.vy=vy;
        this.vz= vz;
    }

    public int windDirection(){
        return (int) Math.atan(this.vy/this.vx);

    }
}
