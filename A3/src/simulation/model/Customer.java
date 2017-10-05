package simulation.model;

import java.util.Random;

public class Customer {

    public static int customerCount = 0;

    private int timeJoinedQueue;
    private int timeToWindow;
    private int timeSpentAtTheWindow;
    private int customerNumber;

    public Customer(int timeJoinedQueue) {
        customerCount++;
        this.customerNumber = customerCount;
        this.timeJoinedQueue = timeJoinedQueue;
        this.timeSpentAtTheWindow = generateTimeSpentAtTheWindow();
    }

    private int generateTimeSpentAtTheWindow() {
        return new Random().nextInt(131) + 55;
    }

    public int getTimeJoinedQueue() {
        return timeJoinedQueue;
    }

    public int getTimeToWindow() {
        return timeToWindow;
    }

    public void setTimeToWindow(int timeToWindow) {
        this.timeToWindow = timeToWindow;
    }

    public int getTimeSpentAtTheWindow() {
        return timeSpentAtTheWindow;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }
}
