package Model.adt;

import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public interface ISemaphoreTable<K,V> {
    void add(K key, V value);

    V get(K key);

    void update(K key, V value);

    boolean contains(K key);

    Integer getFreeLocation();

    Iterable<Map.Entry<K,V>> getAll();

}
