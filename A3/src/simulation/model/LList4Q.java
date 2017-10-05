package simulation.model;

public class LList4Q<E> extends LList<E> {

    @Override
    public E removeNext() {
        if (head == null)
            return null;

        //take head value
        E value = head.value;
        //now next element becomes head
        head = head.next;
        //if head is present
        if (head != null)
            //delete link to former head
            head.prev = null;
        //if there was no next element
        else
            //tail also becomes null
            tail = null;

        size--;
        return value;
    }
}
