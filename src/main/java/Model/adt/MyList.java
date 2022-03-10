package Model.adt;
import Model.exceptions.MyException;

import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;

public class MyList<T> implements IList<T> {
    Vector<T> list;

    public MyList(){
        list = new Vector<T>();
    }

    @Override
    public void add(T v) {
        list.add(v);
    }

    @Override
    public void remove(Object o) {
        if(list.isEmpty())
            throw new MyException("Empty list");
        list.remove(o);
    }

    public T getFirstElement()
    {
        return list.firstElement();
    }

    @Override
    public Iterator<T> getAll() {
        return list.iterator();
    }

    @Override
    public boolean empty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    public String toString()
    {
        return list.toString();
    }
}
