package simulation.model;

public class EventQ<E extends Comparable<E>> extends LList<E> {

    @Override
    public E removeNext() {
        if (head == null)
            return null;

        Element<E> nearestEventElement = head;

        Element<E> eventElement = head.next;
        //traverse whole queue
        while (eventElement != null) {
            //if event is less (will happen sooner) then the one stored in nearestEventElement
            if (eventElement.value.compareTo(nearestEventElement.value) < 0)
                //replace nearestEventElement with it
                nearestEventElement = eventElement;

            //take a look at the next event in the queue
            eventElement = eventElement.next;
        }

        //if nearestEventElement isn't the head
        if (nearestEventElement != head)
            //replace link to it from previous element by next event in the queue
            nearestEventElement.prev.next = nearestEventElement.next;
        //if it is the head
        else {
            if (head.next != null)
                //delete link to it from next element
                head.next.prev = null;

            //set next element to be the head
            head = head.next;
        }
        //if nearestEventElement isn't the tail
        if (nearestEventElement != tail)
            //replace link to it from next element by previous event in the queue
            nearestEventElement.next.prev = nearestEventElement.prev;
        //if it is the tail
        else {
            if (tail.prev != null)
                //delete link to it from previous element
                tail.prev.next = null;
            //set previous element to be the tail
            tail = tail.prev;
        }

        size--;
        return nearestEventElement.value;
    }
}
