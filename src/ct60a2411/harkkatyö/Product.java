/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ct60a2411.harkkatyö;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ilari Sahi
 * Opiskelijanumero: 0438594
 * 10.12.2016
 */
abstract public class Product {
    String name;
    boolean fragile;
    int fragile_factor;
    Map<String, Double> dimension = new HashMap<>();
    
    public Product() {
        dimension.put("weight", 0.0);
        dimension.put("height", 0.0);
        dimension.put("width", 0.0);
        dimension.put("depth", 0.0);
    }
    
    static public ArrayList<String> getProductList() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("Haramben luut");
        list.add("Muutama risu");
        list.add("Valkoiset Vansit");
        list.add("Trumpin tupee");
        return list;
    }
}