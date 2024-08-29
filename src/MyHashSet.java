import java.util.HashMap;
import java.util.List;

public class MyHashSet<T> {
    private HashMap<T, Object> map;
    private static final Object PRESENT = new Object();

    public MyHashSet(){
        map = new HashMap<>();
    }

    public MyHashSet(int capasity){
        map = new HashMap<>(capasity);
    }

    public boolean add (T elem) {
        return map.put(elem, PRESENT) == PRESENT;
    }

    public boolean addAll (List<T> list) {
        boolean flag = false;
        for (T t : list) {
            if (add(t)) flag = true;
        }
        return flag;
    }

    public boolean remove(T elem) {
        return map.remove(elem) == PRESENT;
    }

    public boolean removeAll(List<T> list) {
        boolean flag = false;
        for (T t : list) {
            if (remove(t)) flag = true;
        }
        return flag;
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean contains(T elem) {
        return map.containsKey(elem);
    }

    public boolean containsAll(List<T> list) {
        boolean flag = false;
        for (T elem : list) {
            if (map.containsKey(elem)) {
                flag = true;
            }
            if (flag) {
                flag = false;
            }else return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        int flagSize = 0;
        if (!isEmpty()) {
            for (T t : map.keySet()){
                if (flagSize != size() - 1) {
                    sb.append(t).append(", ");
                } else {
                    sb.append(t).append("]");
                }
                flagSize++;
            }

        }else {
            sb.append("]");
        }


        return sb.toString();
    }
}
