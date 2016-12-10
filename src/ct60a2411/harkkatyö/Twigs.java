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
public class Twigs extends Product{
    public Twigs() {
        super();
        fragile = true;
        name = "Muutama risu";
        fragile_factor = 30;
        dimension.put("weight", 5.0);
        dimension.put("height", 20.0);
        dimension.put("width", 150.0);
        dimension.put("depth", 200.0);
    }
}
