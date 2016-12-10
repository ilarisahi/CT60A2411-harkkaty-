/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ct60a2411.harkkatyö;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ilari Sahi
 * Opiskelijanumero: 0438594
 * 10.12.2016
 */
abstract public class Parcel {
    int grade;
    int fragile_factor;
    Map<String, Double> limit_map = new HashMap<>();
    Product item;
    
    public Parcel() {
        limit_map.put("distance", 0.0);
        limit_map.put("weight", 0.0);
        limit_map.put("height", 0.0);
        limit_map.put("width", 0.0);
        limit_map.put("depth", 0.0);
    }
}