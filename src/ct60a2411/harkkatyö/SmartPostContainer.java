package ct60a2411.harkkatyö;

import java.util.ArrayList;
import java.util.Collections;

/**
 * SmartPost container class
 * 
 * @author Petri Rämö
 * opiskelijanro: 0438578
 * 
 * @author Ilari Sahi
 * opiskelijanro: 0438594
 * 
 * 16.12.2016
 * 
 * Follows singleton design and contains all SmartPosts in an ArrayList
 * Contains also ArrayList of city names
 */
public class SmartPostContainer {
    static private SmartPostContainer sps = null;
    private ArrayList<String> cities = new ArrayList();
    private ArrayList<SmartPost> smartPosts = new ArrayList();
    
    private SmartPostContainer() {
    }
    
    static public SmartPostContainer getInstance() {
        if (sps == null) {
            sps = new SmartPostContainer();
        }
        return sps;
    }
    
    public void addCity(String s) {
        if (!(cities.contains(s))) {
            cities.add(s);
            Collections.sort(cities);
        }
    }
    
    public void addSmartPost(SmartPost sp) {
        smartPosts.add(sp);
    }
    
    public ArrayList<String> getCities() {
        return cities;
    }
    
    public ArrayList<SmartPost> getSmartPosts() {
        return smartPosts;
    }
    
    /**
     * Method to get SmartPosts in given city
     * 
     * @param s (city name)
     * @return ArrayList of SmartPosts in given city
     */
    public ArrayList<SmartPost> getCitySmartPosts(String s) {
        ArrayList<SmartPost> sp = new ArrayList();
        s = s.toUpperCase();
        for(SmartPost sPost : smartPosts) {            
            if (sPost.getCity().equals(s)) {
                sp.add(sPost);
            }
        }
        
        return sp;
    }
    
    public SmartPost getSmartPost(int i) {
        return smartPosts.get(i);
    }
}