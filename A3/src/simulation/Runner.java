package simulation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Runner {

    public static void main(String[] args) throws IOException {
        List<SimulationOneResult> simulationOneResults = new ArrayList<>();
        List<SimulationTwoResult> simulationTwoResults = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            simulationOneResults.add(SimulationOne.run());
            simulationTwoResults.add(SimulationTwo.run());
        }


        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("simulationOne1000.txt"));
        for (SimulationOneResult simulationOneResult : simulationOneResults) {
            printSimulationOneResult(bufferedWriter, simulationOneResult);
        }
        bufferedWriter.close();
        bufferedWriter = new BufferedWriter(new FileWriter("simulationTwo1000.txt"));
        for (SimulationTwoResult simulationTwoResult : simulationTwoResults) {
            printSimulationTwoResult(bufferedWriter, simulationTwoResult);
        }
        bufferedWriter.close();

        SimulationOneResult averageSimulationOneResult = getFirstSimulationAverageResult(simulationOneResults);
        bufferedWriter = new BufferedWriter(new FileWriter("simulationOneAverage.txt"));
        printSimulationOneResult(bufferedWriter, averageSimulationOneResult);
        bufferedWriter.close();

        SimulationTwoResult averageSimulationTwoResult = getSecondSimulationAverage(simulationTwoResults);
        bufferedWriter = new BufferedWriter(new FileWriter("simulationTwoAverage.txt"));
        printSimulationTwoResult(bufferedWriter, averageSimulationTwoResult);
        bufferedWriter.close();

        bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        printSimulationOneResult(bufferedWriter, averageSimulationOneResult);
        printSimulationTwoResult(bufferedWriter, averageSimulationTwoResult);
        bufferedWriter.close();
    }

    private static SimulationOneResult getFirstSimulationAverageResult(List<SimulationOneResult> simulationOneResults) throws IOException {
        long totalCustomersServed = 0;
        long maximumNumberOfCustomersServedPerWindowPerHour[] = new long[2];
        long averageNumberOfCustomersServedPerWindowPerHour[] = new long[2];
        long averageWaitTimeInTheQueue = 0;
        long maximumWaitTimeInTheQueue = 0;
        long average1QueueLength = 0;
        long average2QueueLength = 0;
        long maximum1QueueLength = 0;
        long maximum2QueueLength = 0;
        for (SimulationOneResult r : simulationOneResults) {
            totalCustomersServed += r.totalCustomersServed;
            maximumNumberOfCustomersServedPerWindowPerHour[0] += r.maximumNumberOfCustomersServedPerWindowPerHour[0];
            maximumNumberOfCustomersServedPerWindowPerHour[1] += r.maximumNumberOfCustomersServedPerWindowPerHour[1];
            averageNumberOfCustomersServedPerWindowPerHour[0] += r.averageNumberOfCustomersServedPerWindowPerHour[0];
            averageNumberOfCustomersServedPerWindowPerHour[1] += r.averageNumberOfCustomersServedPerWindowPerHour[1];
            averageWaitTimeInTheQueue += r.averageWaitTimeInTheQueue;
            maximumWaitTimeInTheQueue += r.maximumWaitTimeInTheQueue;
            average1QueueLength += r.average1QueueLength;
            average2QueueLength += r.average2QueueLength;
            maximum1QueueLength += r.maximum1QueueLength;
            maximum2QueueLength += r.maximum2QueueLength;
        }
        totalCustomersServed /= simulationOneResults.size();
        maximumNumberOfCustomersServedPerWindowPerHour[0] /= simulationOneResults.size();
        maximumNumberOfCustomersServedPerWindowPerHour[1] /= simulationOneResults.size();
        averageNumberOfCustomersServedPerWindowPerHour[0] /= simulationOneResults.size();
        averageNumberOfCustomersServedPerWindowPerHour[1] /= simulationOneResults.size();
        averageWaitTimeInTheQueue /= simulationOneResults.size();
        maximumWaitTimeInTheQueue /= simulationOneResults.size();
        average1QueueLength /= simulationOneResults.size();
        average2QueueLength /= simulationOneResults.size();
        maximum1QueueLength /= simulationOneResults.size();
        maximum2QueueLength /= simulationOneResults.size();

        return new SimulationOneResult((int)totalCustomersServed,
                new int[]{(int) maximumNumberOfCustomersServedPerWindowPerHour[0],
                        (int) maximumNumberOfCustomersServedPerWindowPerHour[1]},
                new int[]{(int) averageNumberOfCustomersServedPerWindowPerHour[0],
                (int) averageNumberOfCustomersServedPerWindowPerHour[1]}, (int) averageWaitTimeInTheQueue,
                (int) maximumWaitTimeInTheQueue, (int) average1QueueLength, (int) average2QueueLength,
                (int) maximum1QueueLength, (int) maximum2QueueLength);
    }

    private static SimulationTwoResult getSecondSimulationAverage(List<SimulationTwoResult> simulationTwoResults) {
        long totalCustomersServed = 0;
        long maximumNumberOfCustomersServedPerWindowPerHour[] = new long[2];
        long averageNumberOfCustomersServedPerWindowPerHour[] = new long[2];
        long averageWaitTimeInTheQueue = 0;
        long maximumWaitTimeInTheQueue = 0;
        long averageQueueLength = 0;
        long maximumQueueLength = 0;
        for (SimulationTwoResult r : simulationTwoResults) {
            totalCustomersServed += r.totalCustomersServed;
            maximumNumberOfCustomersServedPerWindowPerHour[0] += r.maximumNumberOfCustomersServedPerWindowPerHour[0];
            maximumNumberOfCustomersServedPerWindowPerHour[1] += r.maximumNumberOfCustomersServedPerWindowPerHour[1];
            averageNumberOfCustomersServedPerWindowPerHour[0] += r.averageNumberOfCustomersServedPerWindowPerHour[0];
            averageNumberOfCustomersServedPerWindowPerHour[1] += r.averageNumberOfCustomersServedPerWindowPerHour[1];
            averageWaitTimeInTheQueue += r.averageWaitTimeInTheQueue;
            maximumWaitTimeInTheQueue += r.maximumWaitTimeInTheQueue;
            averageQueueLength += r.averageQueueLength;
            maximumQueueLength += r.maximumQueueLength;
        }
        totalCustomersServed /= simulationTwoResults.size();
        maximumNumberOfCustomersServedPerWindowPerHour[0] /= simulationTwoResults.size();
        maximumNumberOfCustomersServedPerWindowPerHour[1] /= simulationTwoResults.size();
        averageNumberOfCustomersServedPerWindowPerHour[0] /= simulationTwoResults.size();
        averageNumberOfCustomersServedPerWindowPerHour[1] /= simulationTwoResults.size();
        averageWaitTimeInTheQueue /= simulationTwoResults.size();
        maximumWaitTimeInTheQueue /= simulationTwoResults.size();
        averageQueueLength /= simulationTwoResults.size();
        maximumQueueLength /= simulationTwoResults.size();

        return new SimulationTwoResult((int)totalCustomersServed,
                new int[]{(int) maximumNumberOfCustomersServedPerWindowPerHour[0],
                        (int) maximumNumberOfCustomersServedPerWindowPerHour[1]},
                new int[]{(int) averageNumberOfCustomersServedPerWindowPerHour[0],
                        (int) averageNumberOfCustomersServedPerWindowPerHour[1]}, (int) averageWaitTimeInTheQueue,
                (int) maximumWaitTimeInTheQueue, (int) averageQueueLength, (int) maximumQueueLength);
    }

    private static void printSimulationOneResult(BufferedWriter bufferedWriter, SimulationOneResult r) throws IOException {
        bufferedWriter.write("Total number of customers served: " + r.totalCustomersServed + "\n");
        bufferedWriter.write("Maximum number of customers served in 1 window per hour: " + r.maximumNumberOfCustomersServedPerWindowPerHour[0] + "\n");
        bufferedWriter.write("Maximum number of customers served in 2 window per hour: " + r.maximumNumberOfCustomersServedPerWindowPerHour[1] + "\n");
        bufferedWriter.write("Average number of customers served in 1 window per hour: " + r.averageNumberOfCustomersServedPerWindowPerHour[0] + "\n");
        bufferedWriter.write("Average number of customers served in 2 window per hour: " + r.averageNumberOfCustomersServedPerWindowPerHour[1] + "\n");
        bufferedWriter.write(String.format("Average time in the queue: %dmin %dsec\n", r.averageWaitTimeInTheQueue/60, r.averageWaitTimeInTheQueue%60));
        bufferedWriter.write(String.format("Maximum time in the queue: %dmin %dsec\n", r.maximumWaitTimeInTheQueue/60, r.maximumWaitTimeInTheQueue%60));
        bufferedWriter.write("Average 1 queue length: " + r.average1QueueLength + "\n");
        bufferedWriter.write("Average 2 queue length: " + r.average2QueueLength + "\n");
        bufferedWriter.write("Maximum 1 queue length: " + r.maximum1QueueLength + "\n");
        bufferedWriter.write("Maximum 2 queue length: " + r.maximum2QueueLength + "\n\n");
    }

    private static void printSimulationTwoResult(BufferedWriter bufferedWriter, SimulationTwoResult r) throws IOException {
        bufferedWriter.write("Total number of customers served: " + r.totalCustomersServed + "\n");
        bufferedWriter.write("Maximum number of customers served in 1 window per hour: " + r.maximumNumberOfCustomersServedPerWindowPerHour[0] + "\n");
        bufferedWriter.write("Maximum number of customers served in 2 window per hour: " + r.maximumNumberOfCustomersServedPerWindowPerHour[1] + "\n");
        bufferedWriter.write("Average number of customers served in 1 window per hour: " + r.averageNumberOfCustomersServedPerWindowPerHour[0] + "\n");
        bufferedWriter.write("Average number of customers served in 2 window per hour: " + r.averageNumberOfCustomersServedPerWindowPerHour[1] + "\n");
        bufferedWriter.write(String.format("Average time in the queue: %dmin %dsec\n", r.averageWaitTimeInTheQueue/60, r.averageWaitTimeInTheQueue%60));
        bufferedWriter.write(String.format("Maximum time in the queue: %dmin %dsec\n", r.maximumWaitTimeInTheQueue/60, r.maximumWaitTimeInTheQueue%60));
        bufferedWriter.write("Average queue length: " + r.averageQueueLength + "\n");
        bufferedWriter.write("Maximum queue length: " + r.maximumQueueLength + "\n\n");
    }
}
