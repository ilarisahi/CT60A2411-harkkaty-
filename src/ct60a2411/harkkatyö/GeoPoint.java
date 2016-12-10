/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ct60a2411.harkkatyö;

/**
 *
 * @author Petri
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