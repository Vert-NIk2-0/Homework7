public class MyLinkedList<T> {
    private Node<T> first = new Node<>();
    private Node<T> last = new Node<>();
    private int size;

    public MyLinkedList() {
        first.next = last;
        last.prev = first;
    }

    private static class Node<T> {
        private T element;
        private Node<T> next;
        private Node<T> prev;
    }

    public int size() {
        return size;
    }

    public void add(T element) {
        Node<T> newNode = new Node<>();
        newNode.element = element;

        Node<T> prevLast = last.prev;
        prevLast.next = newNode;
        newNode.prev = prevLast;
        newNode.next = last;
        last.prev = newNode;
        size++;
    }

    public void add(int index, T element) {
        checkIndex(index);
        Node<T> newNode = new Node<>();
        newNode.element = element;

        Node<T> prevElem;
        Node<T> nextElem;
        if (index == 0) {
            nextElem = first.next;

            newNode.next = nextElem;
            newNode.prev = first;
            first.next = newNode;
        }else if (index == size) {
            add(element);
            size--;
        }else if (index <= size / 2) {
            prevElem = first.next;
            for (int i = 0; i < index - 1; i++) {
                prevElem = prevElem.next;
            }
            nextElem = prevElem.next;

            prevElem.next = newNode;
            newNode.prev = prevElem;
            newNode.next = nextElem;
            nextElem.prev = newNode;
        } else if (index > size / 2){
            nextElem = last.prev;
            for (int i = size - 1; i < index - 1; i--) {
                nextElem = nextElem.prev;
            }
            prevElem = nextElem.prev;

            prevElem.next = newNode;
            newNode.prev = prevElem;
            newNode.next = nextElem;
            nextElem.prev = newNode;
        }
        size++;
    }

    public boolean remove(T element) {
        Node<T> prevElem;
        Node<T> nextElem;
        Node<T> currElem = first.next;
        for (int i = 0; i < size; i++) {
            if (element.equals(currElem.element)) {
                nextElem = currElem.next;
                prevElem = currElem.prev;

                removeCurrentElement(currElem);

                nextElem.prev = prevElem;
                prevElem.next = nextElem;
                size--;
                return true;
            }
            currElem = currElem.next;
        }
        return false;
    }

    public T remove(int index) {
        checkIndex(index);
        Node<T> prevElem;
        Node<T> nextElem;
        T element;

        if (index == 0) {
            nextElem = first.next.next;
            element = first.next.element;
            removeCurrentElement(first.next);

            nextElem.prev = first;
            first.next = nextElem;
            size--;
            return element;
        }else if (index == size - 1) {
            prevElem = last.prev.prev;
            element = last.prev.element;

            removeCurrentElement(last.prev);

            prevElem.next = last;
            last.prev = prevElem;
            size--;
            return element;
        }else if (index <= size / 2) {
            prevElem = first.next;
            for (int i = 0; i < index - 1; i++) {
                prevElem = prevElem.next;
            }
            element = prevElem.next.element;
            nextElem = prevElem.next.next;
            removeCurrentElement(prevElem.next);

            prevElem.next = nextElem;
            nextElem.prev = prevElem;
            size--;
            return element;
        } else if (index > size / 2){
            nextElem = last.prev;
            for (int i = 0; i < index - 1; i++) {
                nextElem = nextElem.prev;
            }
            element = nextElem.prev.element;
            prevElem = nextElem.prev.prev;
            removeCurrentElement(nextElem.prev);

            prevElem.next = nextElem;
            nextElem.prev = prevElem;
            size--;
            return element;
        }
        return null;
    }

    private void removeCurrentElement (Node<T> currElem) {
        currElem.element = null;
        currElem.next = null;
        currElem.prev = null;
    }

    public T get(int index) {
        checkIndex(index);
        Node<T> current = first.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.element;
    }

    public T set(int index, T element) {
        checkIndex(index);
        Node<T> current = first.next;
        T oldElement;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        oldElement = current.element;
        current.element = element;
        return oldElement;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        Node<T> current = first.next;
        StringBuilder stringBuilder = new StringBuilder("[");
        if (current.element != null) {
            for (int i = 0; i < size - 1; i++) {
                stringBuilder.append(current.element).append(", ");
                current = current.next;
            }
            stringBuilder.append(current.element).append("]");
        } else {
            stringBuilder.append("]");
        }
        return stringBuilder.toString();
    }
}
