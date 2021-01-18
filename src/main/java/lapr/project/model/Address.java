package lapr.project.model;

public class Address {
    private String streetName;
    private GeographicalPoint geoPoint;
    private String city;
    private int portNumber;
    private String zipCode;



    public Address(String streetName, GeographicalPoint geoPoint, String city, int portNumber, String zipCode) {
        setStreet(streetName);
        setGeoPoint(geoPoint);
        setCity(city);
        setPortNumber(portNumber);
        setZipCode(zipCode);
    }
    
    
    public String getStreet() {
        return streetName;
    }


    public GeographicalPoint getGeographicalPoint() {
        return geoPoint;
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
    
    public final void setStreet(String address) {
        if (address==null||address.isEmpty()){
            throw new IllegalArgumentException("Street Name cannot be null");
        }
        this.streetName = address;
    }
    
    
    public final void setCity(String city){
        if (city==null||city.isEmpty()){
            throw new IllegalArgumentException("City cannot be null");
        }
        this.city=city;
    }

    public final void setGeoPoint(GeographicalPoint geoPoint) {
        if(geoPoint == null){
            throw new IllegalArgumentException("Geographical Point cannot be null.");
        }
        this.geoPoint = geoPoint;
    }
    
    
    
    
    public final void setZipCode(String zipCode) {
        if(!zipCode.matches("[1-9][0-9]{3}-[0-9]{3}")) throw new IllegalArgumentException("Wrong zipCode");
        this.zipCode=zipCode;
    }

    public final void setPortNumber(int portNumber) {
        if (portNumber<=0) throw new IllegalArgumentException("Invalid port number");
        this.portNumber=portNumber;
    }

    @Override
    public String toString() {
        return String.format("Street: %s | City: %s | Port: %d | Zip-Code: %s %s", streetName, city, portNumber, zipCode, geoPoint);
    }
    
    
}
