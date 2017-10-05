package simulation;

public class SimulationTwoResult {
    int totalCustomersServed;
    int maximumNumberOfCustomersServedPerWindowPerHour[];
    int averageNumberOfCustomersServedPerWindowPerHour[];
    int averageWaitTimeInTheQueue;
    int maximumWaitTimeInTheQueue;
    int averageQueueLength;
    int maximumQueueLength;

    public SimulationTwoResult(int totalCustomersServed, int[] maximumNumberOfCustomersServedPerWindowPerHour,
                               int[] averageNumberOfCustomersServedPerWindowPerHour, int averageWaitTimeInTheQueue,
                               int maximumWaitTimeInTheQueue, int averageQueueLength, int maximumQueueLength) {
        this.totalCustomersServed = totalCustomersServed;
        this.maximumNumberOfCustomersServedPerWindowPerHour = maximumNumberOfCustomersServedPerWindowPerHour;
        this.averageNumberOfCustomersServedPerWindowPerHour = averageNumberOfCustomersServedPerWindowPerHour;
        this.averageWaitTimeInTheQueue = averageWaitTimeInTheQueue;
        this.maximumWaitTimeInTheQueue = maximumWaitTimeInTheQueue;
        this.averageQueueLength = averageQueueLength;
        this.maximumQueueLength = maximumQueueLength;
    }
}
