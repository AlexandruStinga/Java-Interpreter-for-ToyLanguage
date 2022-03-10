package Model;

public class Pair<K,V>{
    public K first;
    public V second;

    public K getFirst() {
        return first;
    }

    public void setFirst(K first) {
        this.first = first;
    }

    public V getSecond() {
        return second;
    }

    public void setSecond(V second) {
        this.second = second;
    }

    public Pair(K f, V s)
    {
        this.first = f;
        this.second = s;
    }

    @Override
    public String toString() {
        return this.first + " " + this.second;
    }
}
