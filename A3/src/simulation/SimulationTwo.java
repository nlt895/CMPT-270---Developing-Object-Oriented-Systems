package simulation;

import simulation.model.Customer;
import simulation.model.CustomerQ;
import simulation.model.Event;
import simulation.model.EventQ;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static simulation.Common.*;
import static simulation.model.Event.EventType.CUSTOMER_ARRIVE;
import static simulation.model.Event.EventType.CUSTOMER_LEAVES_QUEUE;
import static simulation.model.Event.EventType.CUSTOMER_LEAVES_WINDOW;

public class SimulationTwo {

    private static final String LOG_FILE_PATH = "simulationTwo.txt";
    private static BufferedWriter bufferedWriter;

    private static final int SIMULATION_TIME = 6 * 60 * 60;

    public static SimulationTwoResult run() throws IOException {

        bufferedWriter = new BufferedWriter(new FileWriter(LOG_FILE_PATH));

        EventQ<Event> eventQ = new EventQ<>();

        Customer[] customerAtTheWindow = new Customer[2];

        boolean[] isWindowFree = new boolean[2];
        isWindowFree[0] = true;
        isWindowFree[1] = true;

        CustomerQ customerQ = new CustomerQ();

        //used for counting of averageWaitTimeInTheQueue
        //and maxWaitTimeInTheQueue
        List<Customer> servedCustomers = new ArrayList<>();

        //used for counting average queue size
        int queueSizeChangesCount = 0;
        int queueSizeSum = 0;

        //used for counting average number of customers
        //served per window per hour
        List<Event> leaveWindowEvent = new ArrayList<>();

        //add first Customer arrival to event queue;
        eventQ.add(new Event(CUSTOMER_ARRIVE, getCustomerArriveTime()));

        Event event;
        while ((event = eventQ.removeNext()) != null && event.getEventClockTime() < SIMULATION_TIME){
            try {
                switch (event.getEventType()) {
                    case CUSTOMER_ARRIVE: {
                        //add new customers to shortest queue
                        int newCustomersCount = getCustomersArrivedCount();
                        for (int i = 0; i < newCustomersCount; i++) {
                            customerQ.joinQ(event.getEventClockTime());

                            bufferedWriter.write(String.format("Customer #%d arrived at %dmin %dsec and joined the queue.\n",
                                    Customer.customerCount,
                                    event.getEventClockTime()/60,
                                    event.getEventClockTime()%60));
                        }

                        //statistic for queue average length
                        queueSizeChangesCount++;
                        queueSizeSum += customerQ.length();

                        //schedule next customer arrival
                        eventQ.add(new Event(CUSTOMER_ARRIVE, event.getEventClockTime() + getCustomerArriveTime()));

                        //if there is free window and there is waiting customer
                        //add event for leaving the queue
                        int customersSentToWindow = 0;
                        for (int i = 0; i < isWindowFree.length; i++) {
                            if (isWindowFree[i] && customerQ.length() <= newCustomersCount &&
                                    customerQ.length() > customersSentToWindow) {
                                Event newEvent = new Event(CUSTOMER_LEAVES_QUEUE, event.getEventClockTime());
                                newEvent.setWindowNumber(i);
                                eventQ.add(newEvent);
                                customersSentToWindow++;
                            }
                        }
                        break;
                    }
                    case CUSTOMER_LEAVES_QUEUE: {
                        //take customer from queue and put him to window
                        int windowNumber = event.getWindowNumber();
                        Customer customer = customerQ.leaveQ();
                        customer.setTimeToWindow(event.getEventClockTime());
                        isWindowFree[windowNumber] = false;
                        customerAtTheWindow[windowNumber] = customer;

                        bufferedWriter.write(String.format("Customer #%d left the queue at %dmin %dsec and approached window #%d.\n",
                                customer.getCustomerNumber(),
                                event.getEventClockTime()/60,
                                event.getEventClockTime()%60,
                                windowNumber));

                        //create event for the customer to leave window
                        int leavesWindowTime = event.getEventClockTime() + customer.getTimeSpentAtTheWindow();
                        Event newEvent = new Event(CUSTOMER_LEAVES_WINDOW, leavesWindowTime);
                        newEvent.setWindowNumber(windowNumber);
                        eventQ.add(newEvent);
                        break;
                    }
                    case CUSTOMER_LEAVES_WINDOW: {
                        leaveWindowEvent.add(event);
                        //put customer to list of served customers
                        int windowNumber = event.getWindowNumber();
                        servedCustomers.add(customerAtTheWindow[windowNumber]);

                        bufferedWriter.write(String.format("Customer #%d left the window #%d at %dmin %dsec and went away fully satisfied.\n",
                                customerAtTheWindow[windowNumber].getCustomerNumber(),
                                windowNumber,
                                event.getEventClockTime()/60,
                                event.getEventClockTime()%60));

                        customerAtTheWindow[windowNumber] = null;

                        //mark window as free
                        isWindowFree[windowNumber] = true;

                        //if queue has waiting customers
                        //create event for leaving the queue
                        //and approaching the freed window
                        if (customerQ.length() > 0) {
                            Event newEvent = new Event(CUSTOMER_LEAVES_QUEUE, event.getEventClockTime());
                            newEvent.setWindowNumber(windowNumber);
                            eventQ.add(newEvent);
                        }
                        break;
                    }
                }
            } catch (Exception e) {
                continue;
            }

        }
        SimulationTwoResult result = displaySummary(customerQ, servedCustomers, queueSizeChangesCount, queueSizeSum, leaveWindowEvent);

        bufferedWriter.close();
        return result;
    }

    private static SimulationTwoResult displaySummary(CustomerQ customerQ, List<Customer> servedCustomers, int queueSizeChangesCount, int queueSizeSum, List<Event> leaveWindowEvent) throws IOException {
        //count and display all statistic
        int[] maximumNumberOfCustomersServedPerWindowPerHour =
                countMaxNumberOfCustomersServedPerWindowPerHour(leaveWindowEvent);

        int[] averageNumberOfCustomersServedPerWindowPerHour =
                countAverageNumberOfCustomersServedPerWindowPerHour(leaveWindowEvent);

        int averageWaitTimeInTheQueue = 0;
        int maximumWaitTimeInTheQueue = 0;
        for (Customer servedCustomer : servedCustomers) {
            int timeSpentInTheQueue = servedCustomer.getTimeToWindow() - servedCustomer.getTimeJoinedQueue();
            averageWaitTimeInTheQueue += timeSpentInTheQueue;
            if (timeSpentInTheQueue > maximumWaitTimeInTheQueue)
                maximumWaitTimeInTheQueue = timeSpentInTheQueue;
        }
        averageWaitTimeInTheQueue /= servedCustomers.size();

        int averageQueueLength = queueSizeSum / queueSizeChangesCount;

        int maximumQueueLength = customerQ.getMaxLength();

        write("\n-------Summary-------\n");
        write("Total number of customers served: " + servedCustomers.size() + "\n");
        write("Maximum number of customers served in 1 window per hour: " + maximumNumberOfCustomersServedPerWindowPerHour[0] + "\n");
        write("Maximum number of customers served in 2 window per hour: " + maximumNumberOfCustomersServedPerWindowPerHour[1] + "\n");
        write("Average number of customers served in 1 window per hour: " + averageNumberOfCustomersServedPerWindowPerHour[0] + "\n");
        write("Average number of customers served in 2 window per hour: " + averageNumberOfCustomersServedPerWindowPerHour[1] + "\n");
        write(String.format("Average time in the queue: %dmin %dsec\n", averageWaitTimeInTheQueue/60, averageWaitTimeInTheQueue%60));
        write(String.format("Maximum time in the queue: %dmin %dsec\n", maximumWaitTimeInTheQueue/60, maximumWaitTimeInTheQueue%60));
        write("Average queue length: " + averageQueueLength + "\n");
        write("Maximum queue length: " + maximumQueueLength + "\n");

        return new SimulationTwoResult(servedCustomers.size(), maximumNumberOfCustomersServedPerWindowPerHour,
                averageNumberOfCustomersServedPerWindowPerHour, averageWaitTimeInTheQueue, maximumWaitTimeInTheQueue,
                averageQueueLength, maximumQueueLength);
    }

    private static void write(String s) throws IOException {
//        bufferedWriter.write(s);
//        System.out.print(s);
    }
}
