package Model.adt;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreTable<K,V> implements ISemaphoreTable<K,V>{

    Map<K,V> dictionary;
    ReentrantLock lock;
    Integer first_free_key = 0;

    public SemaphoreTable()
    {
        dictionary = new HashMap<K,V>();
        lock = new ReentrantLock();
        first_free_key = 1;
    }

    @Override
    public void add(K key, V value) {
        lock.lock();
        first_free_key++;
        dictionary.put(key,value);
        lock.unlock();
    }

    @Override
    public V get(K key) {
        return dictionary.get(key);
    }

    @Override
    public void update(K key, V value) {
        lock.lock();
        dictionary.replace(key,value);
        lock.unlock();
    }

    @Override
    public boolean contains(K key) {
        return dictionary.containsKey(key);
    }


    @Override
    public Integer getFreeLocation() {
        return first_free_key;
    }

    @Override
    public Iterable<Map.Entry<K, V>> getAll() {
        return dictionary.entrySet();
    }

    @Override
    public String toString() {
        return dictionary.toString();
    }
}
