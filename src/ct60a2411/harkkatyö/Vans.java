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
public class Vans extends Product{
    public Vans() {
        super();
        name = "Valkoiset Vansit";
        fragile = false;
        dimension.put("weight", 0.5);
        dimension.put("height", 10.0);
        dimension.put("width", 10.0);
        dimension.put("depth", 25.5);
    }
}
