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
