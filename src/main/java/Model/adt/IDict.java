package Model.adt;

import java.util.Map;

public interface IDict<T1,T2> {
    void add(T1 key, T2 value);
    void update(T1 key, T2 value);
    T2 lookup(T1 id);
    boolean isDefined(T1 id);
    String toString();
    T2 remove(T1 key);
    Map<T1, T2> getContent();
    IDict<T1,T2> clone_dict();
    Iterable<Map.Entry<T1, T2>> getAll();

}
