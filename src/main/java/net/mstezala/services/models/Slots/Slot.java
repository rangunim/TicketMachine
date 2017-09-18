package net.mstezala.services.models.Slots;

import net.mstezala.Helpers.exceptions.CrossMaxNumberException;

import java.util.HashMap;
import java.util.Map;

public class Slot<T> {
    private Map<T, Integer> objectNumber;
    private Map<T, Integer> limitObjectNumber;

    public Slot() {
        objectNumber = new HashMap<>(16);
        limitObjectNumber = new HashMap<>(16);
    }

    public void add(T object) throws CrossMaxNumberException {
        add(object, 1);
    }

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

    public int getNumber(T object) {
        return objectNumber.containsKey(object) ? objectNumber.get(object) : 0;
    }

    public void setLimitObjectNumber(T object, int maxNumber) {
        limitObjectNumber.put(object, maxNumber);
    }

    public int getLimitObjectNumber(T object) {
        Integer result = limitObjectNumber.get(object);
        return result != null ? result : -1;
    }


    public void remove(T object) {
        remove(object, 1);
    }

    public void remove(T object, Integer number2Remove) {
        if (object == null) {
            throw new IllegalArgumentException("Nominal can not be null");
        }
        Integer actualValue = getNumber(object);
        if (actualValue < number2Remove) {
            throw new IllegalArgumentException("CoinSlot does not have " + number2Remove + " objects in slot");
        }
        objectNumber.put(object, actualValue - number2Remove);
    }


    public Map<T, Integer> getObjectNumber() {
        return objectNumber;
    }

    public void setObjectNumber(Map<T, Integer> objectNumber) {
        this.objectNumber = objectNumber;
    }

    public Map<T, Integer> getLimitObjectNumber() {
        return limitObjectNumber;
    }

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
