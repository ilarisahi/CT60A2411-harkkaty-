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
public class HarambeBones extends Product{
    public HarambeBones() {
        super();
        name = "Haramben luut";
        fragile = false;
        dimension.put("weight", 50.0);
        dimension.put("height", 50.0);
        dimension.put("width", 20.0);
        dimension.put("depth", 20.0);
    }
}
