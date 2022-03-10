package Model.adt;

import java.util.List;
import java.util.Map;

public interface IStack<T> {
    T pop();
    void push(T v);
    boolean isEmpty();
    String toString();
    List<T> getAll();

}
