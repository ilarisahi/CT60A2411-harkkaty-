package ct60a2411.harkkatyö;

/**
 * Donald Trump's wig class
 * 
 * @author Petri Rämö
 * opiskelijanro: 0438578
 * 
 * @author Ilari Sahi
 * opiskelijanro: 0438594
 * 
 * 16.12.2016
 */
public class TrumpWig extends Product {
    public TrumpWig() {
        super();
        name = "Trumpin tupee";
        fragile = true;
        fragile_factor = 1;
        dimension.put("weight", 0.01);
        dimension.put("height", 1.0);
        dimension.put("width", 1.0);
        dimension.put("depth", 1.0);
    }
}
