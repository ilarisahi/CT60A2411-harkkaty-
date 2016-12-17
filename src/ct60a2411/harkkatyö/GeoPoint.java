package ct60a2411.harkkatyö;

/**
 * GeoPoint class
 * 
 * @author Petri Rämö
 * opiskelijanro: 0438578
 * 
 * @author Ilari Sahi
 * opiskelijanro: 0438594
 * 
 * 16.12.2016
 * 
 * This class contains data structure, which maintains all automatons location in longitude
 * and latitude.
 */
public class GeoPoint {
    private String lat;
    private String lng;
    
    public GeoPoint(String a, String b) {
        lat = a;
        lng = b;
    }
    
    public double getLat() {
        return Double.parseDouble(lat);
    }
    
    public double getLng() {
        return Double.parseDouble(lng);
    }
}