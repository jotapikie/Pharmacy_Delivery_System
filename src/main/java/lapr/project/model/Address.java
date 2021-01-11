package lapr.project.model;

public class Address {
    private String streetName;
    private double latitude;
    private double longitude;
    private String city;
    private int portNumber;
    private String zipCode;

    public Address(String streetName, double latitude, double longitude, String city, int portNumber, String zipCode) {
        setAddress(streetName);
        setLatitude( latitude);
        setLongitude(longitude);
        setCity(city);
        setPortNumber(portNumber);
        setZipCode(zipCode);
    }
    public String getAddress() {
        return streetName;
    }

    public void setAddress(String address) {
        if (streetName.isEmpty()){
            throw new IllegalArgumentException("Street Name cannot be null");
        }
        this.streetName = address;
    }

    public double getLatitude() {
        return latitude;
    }

    void setLatitude(double latitude) {
        if (!(-90<=latitude&&latitude<=90)) {
            throw new IllegalArgumentException("Latitude is invalid.");
        }
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    void setLongitude(double longitude) {
        if (!(-180<=longitude&&longitude<=180)) {
            throw new IllegalArgumentException("Longitude is invalid.");
        }
        this.longitude = longitude;
    }

    void setCity(String city){
        if (city.isEmpty()){
            throw new IllegalArgumentException("City cannot be null");
        }
        this.city=city;
    }


    public void setZipCode(String zipCode) {
        if(!zipCode.matches("[1-9][0-9]{3}-[0-9]{3}")) throw new IllegalArgumentException("Wrong zipCode");
        this.zipCode=zipCode;
    }

    public void setPortNumber(int portNumber) {
        if (portNumber<=0) throw new IllegalArgumentException("Invalid port number");
        this.portNumber=portNumber;
    }

    public String getCity() {
        return city;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public String getZipCode() {
        return zipCode;
    }
}
