package Model.adt;

import Model.exceptions.MyException;
import Model.values.IValue;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<V> implements IHeap<V>{

    Map<Integer,V> dictionary;
    Integer first_free_key;

    public MyHeap()
    {
        dictionary = new HashMap<Integer,V>();
        first_free_key = 1;
    }

    @Override
    public boolean isDefined(Integer address) {
        return dictionary.containsKey(address);
    }

    @Override
    public Map<Integer, V> getContent() {
        return dictionary;
    }

    @Override
    public V getValue(Integer key) {
        if(!dictionary.containsKey(key))
            throw new MyException("Value associated to the key doesnt exist");
        return dictionary.get(key);
    }

    @Override
    public int getFreeLocation() {
        return first_free_key;
    }

    @Override
    public Integer add(V value) {
        Integer aux_key = first_free_key;
        this.dictionary.put(first_free_key,value);
        first_free_key++;
        return aux_key;
    }

    @Override
    public void update(Integer key, V value) {
        dictionary.replace(key,value);
    }

    @Override
    public void remove(Integer key) {
        dictionary.remove(key);
        first_free_key--;
    }

    @Override
    public void setContent(Map<Integer, V> newContent) {
        dictionary = (HashMap<Integer,V>)newContent;
    }

    @Override
    public boolean isEmpty() {
        return dictionary.isEmpty();
    }

    @Override
    public Iterable<Map.Entry<Integer, V>> getAll() {
        return dictionary.entrySet();
    }

    @Override
    public String toString() {
//        return "MyHeap{" +
//                "dictionary=" + dictionary +
//                ", first_free_key=" + first_free_key +
//                '}';
        return dictionary.toString();
    }
}
