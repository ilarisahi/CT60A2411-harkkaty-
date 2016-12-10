/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ct60a2411.harkkatyö;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Ilari Sahi
 * Opiskelijanumero: 0438594
 * 10.12.2016
 */
public class SmartPosts {
    static private SmartPosts sps = null;
    private ArrayList<String> cities = new ArrayList();
    private ArrayList<SmartPost> smartPosts = new ArrayList();
    
    private SmartPosts() {
    }
    
    static public SmartPosts getInstance() {
        if (sps == null) {
            sps = new SmartPosts();
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
    
    public ArrayList<SmartPost> getCitySmartPosts(String s) {
        ArrayList<SmartPost> sp = new ArrayList();
        s = s.toUpperCase();
        for(SmartPost sPost : smartPosts) {
            if (sPost.getCity() == s) {
                sp.add(sPost);
            }
        }
        
        return sp;
    }
}