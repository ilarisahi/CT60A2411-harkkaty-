package ct60a2411.harkkatyö;

/**
 * Parcel grade 3 class
 * 
 * @author Petri Rämö
 * opiskelijanro: 0438578
 * 
 * @author Ilari Sahi
 * opiskelijanro: 0438594
 * 
 * 16.12.2016
 */
public class ParcelGrade3 extends Parcel {
    public ParcelGrade3() {
        super();
        grade = 3;
        fragile_factor = 50;
        limit_map.put("distance", 2000.0);
        limit_map.put("weight", 200.0);
        limit_map.put("height", 200.0);
        limit_map.put("width", 200.0);
        limit_map.put("depth", 200.0);
    }
}
