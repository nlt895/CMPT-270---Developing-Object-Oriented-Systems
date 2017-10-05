package simulation;

public class SimulationOneResult {
    int totalCustomersServed;
    int maximumNumberOfCustomersServedPerWindowPerHour[];
    int averageNumberOfCustomersServedPerWindowPerHour[];
    int averageWaitTimeInTheQueue;
    int maximumWaitTimeInTheQueue;
    int average1QueueLength;
    int average2QueueLength;
    int maximum1QueueLength;
    int maximum2QueueLength;

    public SimulationOneResult(int totalCustomersServed, int[] maximumNumberOfCustomersServedPerWindowPerHour,
                               int[] averageNumberOfCustomersServedPerWindowPerHour, int averageWaitTimeInTheQueue,
                               int maximumWaitTimeInTheQueue, int average1QueueLength, int average2QueueLength,
                               int maximum1QueueLength, int maximum2QueueLength) {
        this.totalCustomersServed = totalCustomersServed;
        this.maximumNumberOfCustomersServedPerWindowPerHour = maximumNumberOfCustomersServedPerWindowPerHour;
        this.averageNumberOfCustomersServedPerWindowPerHour = averageNumberOfCustomersServedPerWindowPerHour;
        this.averageWaitTimeInTheQueue = averageWaitTimeInTheQueue;
        this.maximumWaitTimeInTheQueue = maximumWaitTimeInTheQueue;
        this.average1QueueLength = average1QueueLength;
        this.average2QueueLength = average2QueueLength;
        this.maximum1QueueLength = maximum1QueueLength;
        this.maximum2QueueLength = maximum2QueueLength;
    }
}
