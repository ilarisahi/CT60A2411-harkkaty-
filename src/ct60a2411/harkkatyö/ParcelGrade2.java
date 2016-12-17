package ct60a2411.harkkatyö;

/**
 * Parcel grade 2 class
 * 
 * @author Petri Rämö
 * opiskelijanro: 0438578
 * 
 * @author Ilari Sahi
 * opiskelijanro: 0438594
 * 
 * 16.12.2016
 */
public class ParcelGrade2 extends Parcel {
    public ParcelGrade2() {
        super();
        grade = 2;
        fragile_factor = 0;
        limit_map.put("distance", 2000.0);
        limit_map.put("weight", 10.0);
        limit_map.put("height", 20.0);
        limit_map.put("width", 30.0);
        limit_map.put("depth", 20.0);
    }
}
