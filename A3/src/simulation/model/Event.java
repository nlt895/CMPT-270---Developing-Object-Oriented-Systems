package simulation.model;

public class Event implements Comparable<Event> {

    private EventType eventType;
    private int eventClockTime;
    private int windowNumber;

    public Event(EventType eventType, int eventClockTime) {
        this.eventType = eventType;
        this.eventClockTime = eventClockTime;
    }

    public EventType getEventType() {
        return eventType;
    }

    public int getEventClockTime() {
        return eventClockTime;
    }

    public int getWindowNumber() {
        return windowNumber;
    }

    public void setWindowNumber(int windowNumber) {
        this.windowNumber = windowNumber;
    }

    @Override
    public int compareTo(Event e) {
        return eventClockTime - e.eventClockTime;
    }

    public enum EventType {
        CUSTOMER_ARRIVE,
        CUSTOMER_LEAVES_QUEUE,
        CUSTOMER_LEAVES_WINDOW,
    }
}
