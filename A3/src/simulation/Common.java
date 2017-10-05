package simulation;

import simulation.model.Event;

import java.util.List;
import java.util.Random;

public class Common {

    /**
     * @return random time between 2 seconds and 110 seconds
     */
    public static int getCustomerArriveTime() {
        return new Random().nextInt(109) + 2;
    }

    /**
     * @return number from 1 to 6 according to percentage
     * 1 person at 30%
     * 2 people at 40%
     * 3 people at 20%
     * 4 people at 5%
     * 5 people at 3%
     * 6 people at 2%
     */
    public static int getCustomersArrivedCount() {
        int percent = new Random().nextInt(100);
        if (percent < 30)
            return 1;
        if (percent < 70)
            return 2;
        if (percent < 90)
            return 3;
        if (percent < 95)
            return 4;
        if (percent < 98)
            return 5;
        return 6;
    }

    /**
     * Counts average number of customers served per window per hour
     * @param leaveWindowEvent list of events when customer leaves the window
     * @return average number of customers served per window per hour
     */
    public static int[] countAverageNumberOfCustomersServedPerWindowPerHour(List<Event> leaveWindowEvent) {
        int[] result = new int[2];
        int[][] servedPerWindowPerHour = new int[][]{{0,0,0,0,0,0}, {0,0,0,0,0,0}};

        Common.countNumberOfCustomersServedPerWindowPerHour(leaveWindowEvent, servedPerWindowPerHour);

        for (int i = 0; i < servedPerWindowPerHour.length; i++) {
            int sum = 0;
            for (int j : servedPerWindowPerHour[i])
                sum += j;
            result[i] = sum / servedPerWindowPerHour[i].length;
        }
        return result;
    }

    /**
     * Counts maximum number of customers served per window per hour
     * @param leaveWindowEvent list of events when customer leaves the window
     * @return maximum number of customers served per window per hour
     */
    public static int[] countMaxNumberOfCustomersServedPerWindowPerHour(List<Event> leaveWindowEvent) {
        int[] result = new int[2];
        int[][] servedPerWindowPerHour = new int[][]{{0,0,0,0,0,0}, {0,0,0,0,0,0}};

        Common.countNumberOfCustomersServedPerWindowPerHour(leaveWindowEvent, servedPerWindowPerHour);

        for (int i = 0; i < servedPerWindowPerHour.length; i++) {
            int max = 0;
            for (int j : servedPerWindowPerHour[i]) {
                if (j > max)
                    max = j;
            }
            result[i] = max;
        }
        return result;
    }

    /**
     * Counts number of customers served per window per hour to provided array
     * @param leaveWindowEvent list of events when customer leaves the window
     * @param servedPerWindowPerHour array to store results
     */
    static void countNumberOfCustomersServedPerWindowPerHour(List<Event> leaveWindowEvent, int[][] servedPerWindowPerHour) {
        for (Event event : leaveWindowEvent) {
            int t = 1 * 60 * 60;
            for (int i = 1; i <= 6; i++) {
                if (event.getEventClockTime() < i * t) {
                    servedPerWindowPerHour[event.getWindowNumber()][i - 1]++;
                    break;
                }
            }
        }
    }
}