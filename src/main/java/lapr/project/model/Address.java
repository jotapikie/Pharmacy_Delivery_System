package lapr.project.model;

public class Address {
    private String streetName;
    private GeographicalPoint geoPoint;
    private String city;
    private int portNumber;
    private String zipCode;

//    public Address(String streetName, double latitude, double longitude, String city, int portNumber, String zipCode) {
//        setStreet(streetName);
//        setLatitude( latitude);
//        setLongitude(longitude);
//        setCity(city);
//        setPortNumber(portNumber);
//        setZipCode(zipCode);
//    }

    public Address(String streetName, GeographicalPoint geoPoint, String city, int portNumber, String zipCode) {
        this.streetName = streetName;
        this.geoPoint = geoPoint;
        this.city = city;
        this.portNumber = portNumber;
        this.zipCode = zipCode;
    }
    
    
    public String getStreet() {
        return streetName;
    }

    public void setStreet(String address) {
        if (address==null||address.isEmpty()){
            throw new IllegalArgumentException("Street Name cannot be null");
        }
        this.streetName = address;
    }

    public GeographicalPoint getGeographicalPoint() {
        return geoPoint;
    }

    






    void setCity(String city){
        if (city==null||city.isEmpty()){
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

    @Override
    public String toString() {
        return String.format("Street: %s | City: %s | Port: %d | Zip-Code: %s %s", streetName, city, portNumber, zipCode, geoPoint);
    }
    
    
}
