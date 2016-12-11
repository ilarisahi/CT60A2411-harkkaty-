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
    static private ArrayList<Parcel> parcels = null;
    
    private Warehouse() {
        parcels = new ArrayList<>();
    }
    
    static public Warehouse getInstance() {
        if (wh == null) {
            wh = new Warehouse();
        }
        return wh;
    }
    
    static public ArrayList<Parcel> getParcels() {
        return parcels;
    }
    
    public void addParcel(Parcel p) {
        parcels.add(p);
    }
    
    public void deleteParcel(Parcel p) {
        parcels.remove(p);
    }
}