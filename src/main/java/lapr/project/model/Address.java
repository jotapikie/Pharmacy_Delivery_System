package lapr.project.model;

public class Address {
    private String address;
    private double latitude;
    private double longitude;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    void setLatitude(double latitude) {
        if (!(-90<latitude&&latitude<90)) {
            throw new IllegalArgumentException("Latitude is invalid.");
        }
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    void setLongitude(double longitude) {
        if (!(-180<longitude&&longitude<180)) {
            throw new IllegalArgumentException("Longitude is invalid.");
        }
        this.longitude = longitude;
    }

    public Address(String address, double latitude, double longitude) {
        this.address = address;
        setLatitude( latitude);
        setLongitude(longitude);
    }
}
