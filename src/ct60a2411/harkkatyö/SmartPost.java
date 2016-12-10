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
public class SmartPost {
    private String code;
    private String city;
    private String address;
    private String availability;
    private String postoffice;
    private String lat;
    private String lng;
    GeoPoint GP;
    
    public SmartPost(String a, String b, String c, String d, String e, String f, String g) {
        code = a;
        city = b;
        address = c;
        availability = d;
        postoffice = e;
        GP = new GeoPoint(f, g);
        
    }
    
}
