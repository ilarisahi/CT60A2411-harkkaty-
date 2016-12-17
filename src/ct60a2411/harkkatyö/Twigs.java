package ct60a2411.harkkatyö;

/**
 * Twig class
 * 
 * @author Petri Rämö
 * opiskelijanro: 0438578
 * 
 * @author Ilari Sahi
 * opiskelijanro: 0438594
 * 
 * 16.12.2016
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
