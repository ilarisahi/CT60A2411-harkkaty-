package ct60a2411.harkkatyö;

import java.util.HashMap;
import java.util.Map;

/**
 * Parcel class
 * 
 * @author Petri Rämö
 * opiskelijanro: 0438578
 * 
 * @author Ilari Sahi
 * opiskelijanro: 0438594
 * 
 * 16.12.2016
 */
abstract public class Parcel {
    protected int grade;
    protected int fragile_factor;
    protected Map<String, Double> limit_map = new HashMap<>();
    protected Product item;
    protected int startPost;
    protected int endPost;
    
    public Parcel() {
        limit_map.put("distance", 0.0);
        limit_map.put("weight", 0.0);
        limit_map.put("height", 0.0);
        limit_map.put("width", 0.0);
        limit_map.put("depth", 0.0);
    }
    
    @Override
    public String toString() {
        String s = item.getName() + " (" + grade + ". luokka)";
        return s;
    }
    
    public int getGrade() {
        return grade;
    }

    public int getFragile_factor() {
        return fragile_factor;
    }

    public Map<String, Double> getLimit_map() {
        return limit_map;
    }

    public Product getItem() {
        return item;
    }

    public int getStartPost() {
        return startPost;
    }

    public int getEndPost() {
        return endPost;
    }
    
    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setFragile_factor(int fragile_factor) {
        this.fragile_factor = fragile_factor;
    }

    public void setItem(Product item) {
        this.item = item;
    }

    public void setStartPost(int startPost) {
        this.startPost = startPost;
    }

    public void setEndPost(int endPost) {
        this.endPost = endPost;
    }
}