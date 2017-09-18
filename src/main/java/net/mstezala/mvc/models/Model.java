package net.mstezala.mvc.models;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Model {
    private Map<String, Object> bag;

    public Model() {
        bag = new HashMap<>();
    }

    public void add(String key, Object obj) {
        bag.put(key, obj);
    }

    public Object remove(String key) {
        if (!bag.containsKey(key)) {
            throw new NoSuchElementException(key + " " + "does not exist in bag");
        }
        return bag.remove(key);
    }

    public Object get(String key) {
        return bag.get(key);
    }

    public void clear() {
        bag.clear();
    }
}
