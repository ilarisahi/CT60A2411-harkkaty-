package ct60a2411.harkkatyö;

/**
 * White Vans class
 * 
 * @author Petri Rämö
 * opiskelijanro: 0438578
 * 
 * @author Ilari Sahi
 * opiskelijanro: 0438594
 * 
 * 16.12.2016
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
