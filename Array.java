
public interface Array<T> {

    int getSize();

    void extend(Vettore<T> v);

    void set(int index, T element);

    void add(int index, T element);

    void add(T element);

    T get(int index);

    T[] getDefaultArray();
}
