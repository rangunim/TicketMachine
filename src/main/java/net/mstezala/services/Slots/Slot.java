package net.mstezala.services.Slots;

import net.mstezala.helpers.exceptions.CrossMaxNumberException;

import java.util.HashMap;
import java.util.Map;

public class Slot<T> {
    /**
     * Map of object where key is type of object and value is the actual number objects
     */
    private Map<T, Integer> objectNumber;
    /**
     * This map contains a limit for each object type. Key is type of object and value is a limit(max value) objects in slot
     */
    private Map<T, Integer> limitObjectNumber;

    /**
     * Create an empty slot.
     */
    public Slot() {
        objectNumber = new HashMap<>(16);
        limitObjectNumber = new HashMap<>(16);
    }

    /**
     * Add object to slot.
     *
     * @param object The object which will add to slot.
     * @throws CrossMaxNumberException - it thrown when object number equals a limit of slot object type and try add next object.
     */
    public void add(T object) throws CrossMaxNumberException {
        add(object, 1);
    }

    /**
     * Add a few object at once.
     * @param object the object  which will add to slot.
     * @param number2Add  number of objects which will add to slot.
     * @throws CrossMaxNumberException - it thrown when object number equals a limit of slot object type and try add next object.
     */
    public void add(T object, Integer number2Add) throws CrossMaxNumberException {
        Integer nominalNumber = getNumber(object);
        Integer newValue = nominalNumber + number2Add;
        if (limitObjectNumber.get(object) == null) {
            limitObjectNumber.put(object, Integer.MAX_VALUE);
        }
        if (newValue < limitObjectNumber.get(object)) {
            objectNumber.put(object, newValue);
        } else {
            throw new CrossMaxNumberException("Ticket machine have full slot of this object");
        }
    }

    /**
     * Give an actually number of key object type in slot. In other words, it give a value of a sent object as key of slot.
     * @param object A key object type in slot.
     * @return actually number of key object type in slot.
     */
    public int getNumber(T object) {
        return objectNumber.containsKey(object) ? objectNumber.get(object) : 0;
    }


    /**
     * Set a limit for object type in slot.
     * @param object The object type which will be in slot.
     * @param maxNumber a limit value.
     */
    public void setLimitObjectNumber(T object, int maxNumber) {
        limitObjectNumber.put(object, maxNumber);
    }

    /**
     * Give a limit for object type in slot.
     * @param object The object type which will be in slot.
     * @return a limit for object type in slot or -1 if object type not in slot.
     */
    public int getLimitObjectNumber(T object) {
        Integer result = limitObjectNumber.get(object);
        return result != null ? result : -1;
    }

    /**
     * Removes one object type from slot
     * @param object the object to be removed
     */
    public void remove(T object) {
        remove(object, 1);
    }

    /**
     * Removes object types from slot
     * @param object the object to be removed
     * @param number2Remove number of objects which want to remove.
     */
    public void remove(T object, Integer number2Remove) {
        if (object == null) {
            throw new IllegalArgumentException("Nominal can not be null");
        }
        Integer actualValue = getNumber(object);
        if (actualValue < number2Remove) {
            throw new IllegalArgumentException("Slot does not have " + number2Remove + " objects");
        }
        objectNumber.put(object, actualValue - number2Remove);
    }

    /**
     * Give a reference to objectNumber map field. It contains all object type in slot (set of keys) and number of each object type (set of values)
     * @return a reference to objectNumber map field
     */
    public Map<T, Integer> getObjectNumber() {
        return objectNumber;
    }

    /**
     * Set a map of object number in slot. Note: It does not set the limit of object type.
     * @param objectNumber a map of object number, which key is object type, and value is a actual object number.
     */
    public void setObjectNumber(Map<T, Integer> objectNumber) {
        this.objectNumber = objectNumber;
    }

    /**
     * Give a reference to limitObjectNumber map field. It contains all object type in slot which have a limit (set of keys) and limit of each object type( set of values).
     * @return a reference to limitObjectNumber map field
     */
    public Map<T, Integer> getLimitObjectNumber() {
        return limitObjectNumber;
    }

    /**
     * Set a limit for object number type in slot. Note: The set of keys could be  a subset of keys in object number.
     * @param limitObjectNumber a map of object number, which key is object type, and value is a limit of object number.
     */
    public void setLimitObjectNumber(Map<T, Integer> limitObjectNumber) {
        this.limitObjectNumber = limitObjectNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Slot<?> slot = (Slot<?>) o;

        if (!objectNumber.equals(slot.objectNumber)) return false;
        return limitObjectNumber.equals(slot.limitObjectNumber);
    }

    @Override
    public int hashCode() {
        int result = objectNumber.hashCode();
        result = 31 * result + limitObjectNumber.hashCode();
        return result;
    }
}
