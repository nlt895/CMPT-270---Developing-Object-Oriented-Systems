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
import java.util.Random;

import static simulation.Common.countAverageNumberOfCustomersServedPerWindowPerHour;
import static simulation.Common.countMaxNumberOfCustomersServedPerWindowPerHour;
import static simulation.Common.getCustomersArrivedCount;
import static simulation.model.Event.EventType.CUSTOMER_ARRIVE;
import static simulation.model.Event.EventType.CUSTOMER_LEAVES_QUEUE;
import static simulation.model.Event.EventType.CUSTOMER_LEAVES_WINDOW;

public class SimulationOne {

    private static final String LOG_FILE_PATH = "simulationOne.txt";
    private static BufferedWriter bufferedWriter;

    private static final int SIMULATION_TIME = 6 * 60 * 60;

    public static SimulationOneResult run() throws IOException {

        bufferedWriter = new BufferedWriter(new FileWriter(LOG_FILE_PATH));

        EventQ<Event> eventQ = new EventQ<>();

        Customer[] customerAtTheWindow = new Customer[2];

        boolean[] isWindowFree = new boolean[2];
        isWindowFree[0] = true;
        isWindowFree[1] = true;

        CustomerQ[] customerQArr = new CustomerQ[2];
        customerQArr[0] = new CustomerQ();
        customerQArr[1] = new CustomerQ();

        //used for counting of averageWaitTimeInTheQueue
        //and maxWaitTimeInTheQueue
        List<Customer> servedCustomers = new ArrayList<>();

        //used for counting average queue size
        int[] queueSizeChangesCount = new int[2];
        int[] queueSizeSum = new int[2];

        //used for counting average number of customers
        //served per window per hour
        List<Event> leaveWindowEvent = new ArrayList<>();

        //add first Customer arrival to event queue;
        eventQ.add(new Event(CUSTOMER_ARRIVE, getCustomerArriveTime()));

        Event event;
        while ((event = eventQ.removeNext()) != null && event.getEventClockTime() < SIMULATION_TIME){
            switch (event.getEventType()) {
                case CUSTOMER_ARRIVE: {
                    //add new customers to shortest queue
                    int shorterQNumber = getShortestQueue(customerQArr);
                    int newCustomersCount = getCustomersArrivedCount();
                    for (int i = 0; i < newCustomersCount; i++) {
                        customerQArr[shorterQNumber].joinQ(event.getEventClockTime());

                        bufferedWriter.write(String.format("Customer #%d arrived at %dmin %dsec and joined queue #%d.\n",
                                Customer.customerCount,
                                event.getEventClockTime()/60,
                                event.getEventClockTime()%60,
                                shorterQNumber));
                    }

                    //statistic for queue average length
                    queueSizeChangesCount[shorterQNumber]++;
                    queueSizeSum[shorterQNumber] += customerQArr[shorterQNumber].length();

                    //schedule next customer arrival
                    eventQ.add(new Event(CUSTOMER_ARRIVE, event.getEventClockTime() + getCustomerArriveTime()));

                    //if window is free now add event for leaving the queue
                    if (isWindowFree[shorterQNumber] && customerQArr[shorterQNumber].length() == newCustomersCount) {
                        Event newEvent = new Event(CUSTOMER_LEAVES_QUEUE, event.getEventClockTime());
                        newEvent.setWindowNumber(shorterQNumber);
                        eventQ.add(newEvent);
                    }
                    break;
                }
                case CUSTOMER_LEAVES_QUEUE: {
                    //take customer from queue and put him to window
                    int queueNumber = event.getWindowNumber();
                    Customer customer = customerQArr[queueNumber].leaveQ();
                    customer.setTimeToWindow(event.getEventClockTime());
                    isWindowFree[queueNumber] = false;
                    customerAtTheWindow[queueNumber] = customer;

                    bufferedWriter.write(String.format("Customer #%d left the queue #%d at %dmin %dsec and approached window #%d.\n",
                            customer.getCustomerNumber(),
                            queueNumber,
                            event.getEventClockTime()/60,
                            event.getEventClockTime()%60,
                            queueNumber));

                    //create event for the customer to leave window
                    int leavesWindowTime = event.getEventClockTime() + customer.getTimeSpentAtTheWindow();
                    Event newEvent = new Event(CUSTOMER_LEAVES_WINDOW, leavesWindowTime);
                    newEvent.setWindowNumber(queueNumber);
                    eventQ.add(newEvent);
                    break;
                }
                case CUSTOMER_LEAVES_WINDOW: {
                    leaveWindowEvent.add(event);
                    //put customer to list of served customers
                    int queueNumber = event.getWindowNumber();
                    servedCustomers.add(customerAtTheWindow[queueNumber]);

                    //mark window as free
                    isWindowFree[queueNumber] = true;

                    bufferedWriter.write(String.format("Customer #%d left the window #%d at %dmin %dsec and went away fully satisfied.\n",
                            customerAtTheWindow[queueNumber].getCustomerNumber(),
                            queueNumber,
                            event.getEventClockTime()/60,
                            event.getEventClockTime()%60));

                    customerAtTheWindow[queueNumber] = null;

                    //if queue has waiting customers
                    //create event for leaving the queue
                    if (customerQArr[queueNumber].length() > 0) {
                        Event newEvent = new Event(CUSTOMER_LEAVES_QUEUE, event.getEventClockTime());
                        newEvent.setWindowNumber(queueNumber);
                        eventQ.add(newEvent);
                    }
                    break;
                }
            }
        }
        SimulationOneResult result =
                displaySummary(customerQArr, servedCustomers, queueSizeChangesCount, queueSizeSum, leaveWindowEvent);


        bufferedWriter.close();
        return result;
    }

    private static SimulationOneResult displaySummary(CustomerQ[] customerQArr, List<Customer> servedCustomers,
                                       int[] queueSizeChangesCount, int[] queueSizeSum,
                                       List<Event> leaveWindowEvent) throws IOException {
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

        int average1QueueLength = queueSizeSum[0] / queueSizeChangesCount[0];
        int average2QueueLength = queueSizeSum[1] / queueSizeChangesCount[1];

        int maximum1QueueLength = customerQArr[0].getMaxLength();
        int maximum2QueueLength = customerQArr[1].getMaxLength();
        int maximumCustomersWaiting = maximum1QueueLength + maximum2QueueLength;

        write("\n-------Summary-------\n");
        write("Total number of customers served: " + servedCustomers.size() + "\n");
        write("Maximum number of customers served in 1 window per hour: " + maximumNumberOfCustomersServedPerWindowPerHour[0] + "\n");
        write("Maximum number of customers served in 2 window per hour: " + maximumNumberOfCustomersServedPerWindowPerHour[1] + "\n");
        write("Average number of customers served in 1 window per hour: " + averageNumberOfCustomersServedPerWindowPerHour[0] + "\n");
        write("Average number of customers served in 2 window per hour: " + averageNumberOfCustomersServedPerWindowPerHour[1] + "\n");
        write(String.format("Average time in the queue: %dmin %dsec\n", averageWaitTimeInTheQueue/60, averageWaitTimeInTheQueue%60));
        write(String.format("Maximum time in the queue: %dmin %dsec\n", maximumWaitTimeInTheQueue/60, maximumWaitTimeInTheQueue%60));
        write("Average 1 queue length: " + average1QueueLength + "\n");
        write("Average 2 queue length: " + average2QueueLength + "\n");
        write("Maximum 1 queue length: " + maximum1QueueLength + "\n");
        write("Maximum 2 queue length: " + maximum2QueueLength + "\n");
        write("Maximum customers waiting in both queues: " + maximumCustomersWaiting + "\n");

        return new SimulationOneResult(servedCustomers.size(), maximumNumberOfCustomersServedPerWindowPerHour,
                averageNumberOfCustomersServedPerWindowPerHour, averageWaitTimeInTheQueue, maximumWaitTimeInTheQueue,
                average1QueueLength, average2QueueLength, maximum1QueueLength, maximum2QueueLength);
    }

    private static void write(String s) throws IOException {
//        bufferedWriter.write(s);
//        System.out.print(s);
    }

    private static int getShortestQueue(CustomerQ[] queues) {
        int shortestQueueNumber = 0;
        int shortestQueueSize = Integer.MAX_VALUE;
        for (int i = 0; i < queues.length; i++) {
            if (queues[i].length() < shortestQueueSize) {
                shortestQueueNumber = i;
                shortestQueueSize = queues[i].length();
            }
        }
        return shortestQueueNumber;
    }

    private static int getCustomerArriveTime() {
        return new Random().nextInt(109) + 2;
    }
}
