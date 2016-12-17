package ct60a2411.harkkatyö;

/**
 * Harambe's bones class
 * 
 * @author Petri Rämö
 * opiskelijanro: 0438578
 * 
 * @author Ilari Sahi
 * opiskelijanro: 0438594
 * 
 * 16.12.2016
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
