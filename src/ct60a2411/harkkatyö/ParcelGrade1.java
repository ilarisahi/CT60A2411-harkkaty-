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