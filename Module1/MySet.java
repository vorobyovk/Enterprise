package mod1_collection_effectiveness;

import java.util.Random;
import java.util.Set;

public class MySet {
    private Set<Integer> set;
    private Random randomNumber;
    private long startStopwatch;
    private long endStopwatch;
    private double timeResults;

    public TimeResult useMethod(Set<Integer> type, int size, String methodName) {
        set = type;
        initSet(size);

        int[] values = new int[100];
        values = initValues(values);

        for (int i = 0; i < values.length; i++) {
            switch (methodName) {
                case "add":
                    startStopwatch = System.currentTimeMillis();
                    set.add(values[i]);
                    endStopwatch = System.currentTimeMillis();
                    break;
                case "remove":
                    startStopwatch = System.currentTimeMillis();
                    set.remove(values[i]);
                    endStopwatch = System.currentTimeMillis();
                    break;
                case "contains":
                    startStopwatch = System.currentTimeMillis();
                    set.contains(values[i]);
                    endStopwatch = System.currentTimeMillis();
                    break;
                case "populate":
                    set.clear();
                    startStopwatch = System.currentTimeMillis();
                    initSet(size);
                    endStopwatch = System.currentTimeMillis();
                    break;
            }
            timeResults += endStopwatch - startStopwatch;
        }
        timeResults /= values.length;
        return new TimeResult(set.getClass().getSimpleName(), methodName, timeResults);
    }

    private void initSet(int size) {
        randomNumber = new Random();
        while (set.size() < size) {
            set.add(randomNumber.nextInt());
        }
    }

    private int[] initValues(int[] values) {
        randomNumber = new Random();
        for (int i = 0; i < values.length; i++) {
            values[i] = randomNumber.nextInt();
        }
        return values;
    }
}