package by.avdeev.pizzeria.entity;

import java.util.Iterator;
import java.util.Map;

public class CountMap {
    private Map<String, Integer> map;

    public CountMap(Map<String, Integer> map) {
        this.map = map;
        iterator = map.entrySet().iterator();
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }

    private Iterator<Map.Entry<String, Integer>> iterator;

    public int getSize() {
        return map.size();
    }

    public String getCount() {
        if (iterator.hasNext()) {
            Map.Entry<String, Integer> pair = iterator.next();
            return pair.getKey() + " = " + pair.getValue();
        } else {
            return null;
        }
    }
}
