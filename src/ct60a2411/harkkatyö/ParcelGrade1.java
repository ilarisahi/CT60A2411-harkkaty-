package ct60a2411.harkkatyö;

/**
 * Parcel grade 1 class
 * 
 * @author Petri Rämö
 * opiskelijanro: 0438578
 * 
 * @author Ilari Sahi
 * opiskelijanro: 0438594
 * 
 * 16.12.2016
 */
public class ParcelGrade1 extends Parcel {
    public ParcelGrade1() {
        super();
        grade = 1;
        fragile_factor = 100;
        limit_map.put("distance", 150.0);
        limit_map.put("weight", 10.0);
        limit_map.put("height", 60.0);
        limit_map.put("width", 50.0);
        limit_map.put("depth", 30.0);
    }
}