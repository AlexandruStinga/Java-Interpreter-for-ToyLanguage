package Model.adt;
import Model.exceptions.MyException;

import java.util.HashMap;
import java.util.Map;

public class MyDict<T1, T2> implements IDict<T1, T2> {
    Map<T1,T2> dictionary;

    public MyDict()
    {
        dictionary = new HashMap<T1,T2>();
    }

    @Override
    public void add(T1 key, T2 value) {
        dictionary.put(key,value);
    }

    @Override
    public void update(T1 key, T2 value) {
        if(!dictionary.containsKey(key))
            throw new MyException("Key doesn't exist");
        dictionary.replace(key,value);
    }

    @Override
    public T2 lookup(T1 id) {
        return dictionary.get(id);
    }

    @Override
    public boolean isDefined(T1 id) {
        return dictionary.containsKey(id);
    }

    public String toString()
    {
        return dictionary.toString();
    }

    @Override
    public T2 remove(T1 key) {
        return dictionary.remove(key);
    }

    @Override
    public Map<T1, T2> getContent() {
        return dictionary;
    }

    @Override
    public IDict<T1, T2> clone_dict() {
        IDict<T1,T2> copy = new MyDict<>();
        for(T1 key: dictionary.keySet())
        {
            copy.add(key,dictionary.get(key));
        }
        return copy;
    }

    @Override
    public Iterable<Map.Entry<T1, T2>> getAll() {
        return dictionary.entrySet();
    }
}
