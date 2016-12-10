/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ct60a2411.harkkatyö;

/**
 *
 * @author Ilari Sahi
 * Opiskelijanumero: 0438594
 * 10.12.2016
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
