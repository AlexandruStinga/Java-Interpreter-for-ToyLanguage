package Model.adt;

import Model.values.IValue;

import java.util.Map;

public interface IHeap<V> {
    boolean isDefined(Integer address);

    Map<Integer, V> getContent();

    V getValue(Integer key);

    int getFreeLocation();

    Integer add(V value);

    void update(Integer key, V value);

    void remove(Integer key);

    void setContent(Map<Integer, V> newContent);

    boolean isEmpty();

    Iterable<Map.Entry<Integer, V>> getAll();
}