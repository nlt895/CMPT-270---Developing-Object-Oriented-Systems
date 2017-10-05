package simulation.model;

public abstract class LList<E> {

    protected Element<E> head;
    protected Element<E> tail;

    protected int size;

    public LList() {
        this.size = 0;
    }

    public void add(E element) {
        if (head == null) {
            head = new Element<>(element, null, null);
            tail = head;
        } else {
            tail.next = new Element<>(element, tail, null);
            tail = tail.next;
        }
        size++;
    }

    abstract public E removeNext();

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    protected class Element<E> {
        E value;
        Element<E> prev;
        Element<E> next;

        public Element(E value, Element<E> prev, Element<E> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}
