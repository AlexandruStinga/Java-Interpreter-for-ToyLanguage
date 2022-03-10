package Model.adt;

import java.util.Iterator;
import java.util.Map;

public interface IList<T> {
    void add(T v);
    void remove(Object o);
    String toString();
    boolean empty();
    void clear();
    public T getFirstElement();
    Iterator<T> getAll();

}
