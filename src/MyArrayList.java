import java.util.Arrays;
import java.util.List;

public class MyArrayList<T> {
    private static final int CAPASITY = 16;
    private Object[] elements;
    private int size;

    public MyArrayList() {
        elements = new Object[CAPASITY];
    }

    public MyArrayList(int capacity) {
        elements = new Object[capacity];
    }

    public int size() {
        return size;
    }

    public void add (T element) {
        if (size == elements.length) {
            int newCapacity = 2 * elements.length;
            elements = Arrays.copyOf(elements, newCapacity);
        }
        elements[size++] = element;
    }

    @SuppressWarnings("unchecked")
    public void add (int index, T element) {
        if (index > size + 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }else{

            if (size == elements.length) {
                int newCapacity = 2 * elements.length;
                elements = Arrays.copyOf(elements, newCapacity);
            }

            Object flag;
            for (int i = index; i < size; i++) {
                flag = elements[i];
                elements[i] = element;
                element = (T) flag;
            }
            elements[size++] = element;
        }
    }

    public void addAll (List<T> list) {
        // Когда лазил по классу HashSet увидел реализацию, где можно было просто не писать цикл while, а просто для каждого элемента вызывать метод add. Сам не додумался)
        // Менять уже не стану, но суть в просто в том, что надо в цикле for каждый element передавать в метод add.
        // Там, где по индексу addAll, надо сделать то же самое, только увеличивать индекс на 1 после каждого метода add.
        while (elements.length - size < list.size()) {
            int newCapacity = 2 * elements.length;
            elements = Arrays.copyOf(elements, newCapacity);
        }
        for (T element : list) {
            elements[size++] = element;
        }
    }

    public void addAll (int index, List<T> list) {
        while (elements.length - size < list.size()) {
            int newCapacity = 2 * elements.length;
            elements = Arrays.copyOf(elements, newCapacity);
        }

        Object[] flag = Arrays.copyOfRange(elements, index, size);
        for (int i = index; i < list.size() + index; i++) {
            elements[i] = list.get(i - index);
        }

        int count = index + list.size();
        for (Object element : flag) {
            elements[count++] = element;
        }
        size += list.size();
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }else {
            T element = (T) elements[index];
            for (int i = index; i < size - 1; i++) {
                elements[i] = elements[i + 1];
            }
            elements[size - 1] = null;
            size--;
            return element;
        }
    }

    public boolean remove(Object element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                for (int j = i; j < size - 1; j++) {
                    elements[j] = elements[j + 1];
                }
                elements[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    public boolean removeAll(List<T> list) {
        int flag = size;
        for (T elementToDelete : list) {
            for (int j = 0; j < size; j++) {
                if (elements[j].equals(elementToDelete)) {
                    for (int k = j; k < size - 1; k++) {
                        elements[k] = elements[k + 1];
                    }
                    elements[size - 1] = null;
                    size--;
                    j--;
                }
            }
        }
        return flag != size;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        } else return (T) elements[index];

    }

    @SuppressWarnings("unchecked")
    public T set(int index, T element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            T oldElement = (T) elements[index];
            elements[index] = element;
            return oldElement;
        }

    }

    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
    }

    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (size > 0) {
            for (int i = 0; i < size - 1; i++) {
                sb.append(elements[i]).append(", ");
            }
            sb.append(elements[size - 1]).append("]");
        }else {
            sb.append("]");
        }

        return sb.toString();
    }
}
