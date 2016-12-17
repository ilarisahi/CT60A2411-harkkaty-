package ct60a2411.harkkatyö;

import java.util.ArrayList;

/**
 * Warehouse class
 * 
 * @author Petri Rämö
 * opiskelijanro: 0438578
 * 
 * @author Ilari Sahi
 * opiskelijanro: 0438594
 * 
 * 16.12.2016
 * 
 * This class contains all packages that are made and it follows singleton design
 * because our package machine contains only one warehouse.
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