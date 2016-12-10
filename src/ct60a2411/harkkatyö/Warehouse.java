/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ct60a2411.harkkatyö;

import java.util.ArrayList;

/**
 *
 * @author Ilari Sahi
 * Opiskelijanumero: 0438594
 * 10.12.2016
 */
public class Warehouse {
    static Warehouse wh = null;
    private ArrayList<Parcel> parcels;
    
    private Warehouse() {
        parcels = new ArrayList<>();
    }
    
    static public Warehouse getInstance() {
        if (wh == null) {
            wh = new Warehouse();
        }
        return wh;
    }
}