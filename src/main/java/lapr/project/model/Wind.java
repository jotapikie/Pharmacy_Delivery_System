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

    public int direction(){
        double res = Math.atan(this.vx/this.vy);
        return (int) Math.toDegrees(res);

    }

    public double speed(){
        return Math.sqrt(Math.pow(this.vx,2)*Math.pow(this.vy, 2));
    }
}
