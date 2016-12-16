/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ct60a2411.harkkatyö;

/**
 *
 * @author Petri Rämö
 * opiskelijanro: 0438578
 * 16.12.2016
 * 
 * This class contains all the information about the post automaton, like their addresses.
 * We have also put functions for these informations so that we can call them. This class follows
 * singleton design.
 */
public class SmartPost {
    private String code;
    private String city;
    private String address;
    private String availability;
    private String postoffice;
    private String lat;
    private String lng;
    private int id;
    GeoPoint GP;
    
    
    
    public SmartPost(String a, String b, String c, String d, String e, String f, String g, int i) {
        code = a;
        city = b;
        address = c;
        availability = d;
        postoffice = e;
        GP = new GeoPoint(f, g);
        id = i;
    }    
    
    @Override
    public String toString() {
        return postoffice + ", " + address;
    }
    
    public String getCity() {
        return city;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getAvailability() {
        return availability;
    }
    
    public String getPostoffice() {
        return postoffice;
    }
    
    public double getLat() {
        return GP.getLat();
    }
    
    public double getLng() {
        return GP.getLng();
    }
    
    public int getId() {
        return id;
    }
    
}
