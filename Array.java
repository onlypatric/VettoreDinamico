
public interface Array<T extends BaseType> {

    int getSize();

    void extend(FSVector<T> v);

    void set(int index, T element);

    void add(int index, T element);

    void add(T element);

    T get(int index);

    T[] getDefaultArray();
}
