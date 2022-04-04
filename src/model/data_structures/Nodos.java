package model.data_structures;

public class Nodos<T> {

    private T data;
    private Nodos<T> next;

    public Nodos(T data) {
        this.data = data;
        next = null;
    }

    public void setNext(Nodos<T> next) {
        this.next = next;
    }

    public Nodos<T> next() {
        return next;
    }

    public T data() {
        return data;
    }
}