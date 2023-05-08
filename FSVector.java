
import java.util.Arrays;
import java.util.Iterator;

/**
 * <h1>
 * <strong>
 * Dinamic Vector
 * </strong>
 * </h1>
 * 
 * <p>
 * Makes use of primitive arrays to implement a dinamic one which resizes automatically
 * </p>
 * 
 * @see Arrays
 * @see Iterator
 * @since 2023
 * @author Pintescul Patric https://www.github.com/onlypatric
 */

public class FSVector<Type extends BaseType> implements Array<Type>, Iterable<Type> {

    private static final Object[] EMPTY = {};
    protected Object[] vett;

    /**
     * Iterator
     */
    public Iterator<Type> iterator() {
        return new Iterator<Type>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < vett.length && vett[currentIndex] != null;
            }

            @SuppressWarnings("unchecked")
            @Override
            public Type next() {
                return (Type) vett[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
     * Generates an empty vector
     */
    public FSVector() {
        this.vett = EMPTY;
    }

    /**
     * Generates a vector given initial size
     * 
     * @param capacity
     */
    public FSVector(int capacity) {
        this.vett = new Object[capacity];
    }

    /**
     * Generates a vector given an old vector, it gets cloned!
     * 
     * @param v
     */
    public FSVector(FSVector<Type> v) {
        this.vett = v.vett.clone();
    }

    /**
     * Generates a dinamic vector given a non dinamic and primitive one
     * 
     * @param v
     */
    public FSVector(Type[] v) {
        this.vett = v.clone();
    }

    public int getSize() {
        return this.vett.length;
    }
    public int lenght(){
        return this.getSize();
    }

    public void extend(FSVector<Type> v) {
        for (Type obj : v.getDefaultArray()) {
            this.add(obj);
        }
    }

    public void set(int index, Type element) {
        add(index, element);
    }

    public void add(int index, Type element) {
        this.vett[index] = element;
    }

    @SuppressWarnings("unchecked")
    public Type pop(int index) {
        Type r = (Type) this.vett[index];

        this.vett[index] = null;

        Object[] oldvett = this.vett.clone();

        this.vett = new Object[oldvett.length - 1];

        int index_ = 0;

        for (int i = 0; i < oldvett.length; i++) {
            if (i == index)
                continue;
            this.vett[index_] = oldvett[i];
            index_++;
        }

        return r;
    }

    public void add(Type element) {
        int newSize = this.vett.length + 1;
        this.vett = Arrays.copyOf(this.vett.clone(), newSize);
        this.vett[newSize - 1] = element;
    }

    @SuppressWarnings("unchecked")
    public int find(String value) throws NotFoundException {
        int y = 0;
        for (Type vett : (Type[]) this.vett) {
            if (vett.toString().contains(value))
                return y;
            y++;
        }
        String className;
        try {
            className = this.vett[0].getClass().getTypeName();
        } catch (Exception e) {
            className = "Empty";
        }
        throw new NotFoundException(
                String.format("Query \"%s\" has not been found in any Object of type \"%s\"", value, className));
    }

    /**
     * @param value
     * @return
     * @throws NotFoundException
     */
    @SuppressWarnings("unchecked")
    public int threadFinder(String value) throws NotFoundException {

        Search r = new Search(-1, false);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < this.vett.length / 2; i++) {
                if (!r.trovato)
                    if (((Type) (this.vett[i])).toString() == value) {
                        r.trovato = true;
                        r.valore = i;
                        break;
                    }
            }
            r.proc1 = true;
        });
        Thread thread2 = new Thread(() -> {
            for (int i = this.vett.length / 2; i < this.vett.length; i++) {
                if (!r.trovato)
                    if (((Type) (this.vett[i])).toString() == value) {
                        r.trovato = true;
                        r.valore = i;
                        break;
                    }
            }
            r.proc2 = true;
        });
        thread1.start();
        thread2.start();
        while (!(r.proc1 && r.proc2)) {
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
            }
        }
        if (r.trovato)
            return r.valore;
        String className;
        try {
            className = this.vett[0].getClass().getTypeName();
        } catch (Exception e) {
            className = "Empty";
        }
        throw new NotFoundException(
                String.format("Query \"%s\" has not been found in any Object of type \"%s\"", value, className));
    }

    @SuppressWarnings("unchecked")
    public int find(Type value) throws NotFoundException {
        int y = 0;
        for (Type vett : (Type[]) this.vett) {
            if (vett.equals(value))
                return y;
            y++;
        }
        String className;
        try {
            className = this.vett[0].getClass().getTypeName();
        } catch (Exception e) {
            className = "Empty";
        }
        throw new NotFoundException(
                String.format("Query \"%s\" has not been found in any Object of type \"%s\"", value, className));
    }

    /**
     * @param value
     * @return
     * @throws NotFoundException
     */
    @SuppressWarnings("unchecked")
    public int threadFinder(Type value) throws NotFoundException {

        Search r = new Search(-1, false);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < this.vett.length / 2; i++) {
                if (!r.trovato)
                    if ((((Type) (this.vett[i]))).equals(value)) {
                        r.trovato = true;
                        r.valore = i;
                        break;
                    }
            }
            r.proc1 = true;
        });
        Thread thread2 = new Thread(() -> {
            for (int i = this.vett.length / 2; i < this.vett.length; i++) {
                if (!r.trovato)
                    if ((((Type) (this.vett[i]))).equals(value)) {
                        r.trovato = true;
                        r.valore = i;
                        break;
                    }
            }
            r.proc2 = true;
        });
        thread1.start();
        thread2.start();
        while (!(r.proc1 && r.proc2)) {
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
            }
        }
        if (r.trovato)
            return r.valore;
        String className;
        try {
            className = this.vett[0].getClass().getTypeName();
        } catch (Exception e) {
            className = "Empty";
        }
        throw new NotFoundException(
                String.format("Query \"%s\" has not been found in any Object of type \"%s\"", value, className));
    }

    @SuppressWarnings("unchecked")
    public Type get(int index) {
        return (Type) this.vett[index];
    }

    @SuppressWarnings("unchecked")
    public Type[] getDefaultArray() {
        Type[] vect = (Type[]) this.vett.clone();
        return vect;
    }

    @Override
    public FSVector<Type> clone() throws CloneNotSupportedException {
        FSVector<Type> v = new FSVector<Type>(this);
        return v;
    }

    /**
     * makes a deep clone of the current dinamic vector, for this to work the type must support clone() method (usually every class has that)
     * @return
     * @throws CloneNotSupportedException
     * 
     */
    @SuppressWarnings("unchecked")
    public FSVector<Type> fullClone() throws CloneNotSupportedException {
        FSVector<Type> v = new FSVector<Type>();
        for (Type type : v) {
            v.add((Type)type.clone());
        }
        return v;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(vett);
        return result;
    }

    @Override
    public String toString() {
        String className;
        try {
            className = this.vett[0].getClass().getTypeName();
        } catch (Exception e) {
            className = "Empty";
        }
        return String.format("%s<%s>%s", this.getClass().getTypeName(), className, Arrays.toString(vett));
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FSVector<Type> other = (FSVector<Type>) obj;
        return Arrays.deepEquals(vett, other.vett);
    }
}