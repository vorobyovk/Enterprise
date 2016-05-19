package mod1_collection_effectiveness;

import java.util.*;

public class MyList {
    private List<Integer> list;
    private Random randomNumber;
    private long startStopwatch;
    private long endStopwatch;
    private double timeResults;

    public TimeResult useMethod(List<Integer> type, int size, int[] indexes, String methodName) {
        list = type;
        initList(size);

        if (methodName.equals("contains")) { //почему для "сontains" перезаполняется массив?
                                            // RE: потому что перед массив indexes[] содержит значения от 0 до 10К, 100К и 1000К, а тут я перезаполняю любыми int от MIN_VALUE до MAX_VALUE
                                            // нас в этом тесте не интересуют индексы, а интересуют значения
            indexes = initValues(indexes);
        }

        for (int i = 0; i < indexes.length; i++) { // может будет дешевле внешним сделать switch, а внутри форы?
                                                    // RE: даже не знаю, тут в конце каждого цикла происходит подсчет затраченого времени (timeResults) для среднего арифметического,
                                                    // а так придется в кажом case прописывать вычисление времени, похоже на hardcode
            switch (methodName) {
                case "add":
                    startStopwatch = System.currentTimeMillis();
                    list.add(indexes[i], randomNumber.nextInt());
                    endStopwatch = System.currentTimeMillis();
                    break;
                case "get":
                    startStopwatch = System.currentTimeMillis();
                    list.get(indexes[i]);
                    endStopwatch = System.currentTimeMillis();
                    break;
                case "remove":
                    startStopwatch = System.currentTimeMillis();
                    list.remove(indexes[i]);
                    endStopwatch = System.currentTimeMillis();
                    list.add(randomNumber.nextInt());
                    break;
                case "contains":
                    startStopwatch = System.currentTimeMillis();
                    list.contains(indexes[i]);
                    endStopwatch = System.currentTimeMillis();
                    break;
                case "populate":
                    list.clear();
                    startStopwatch = System.currentTimeMillis();
                    initList(size);
                    endStopwatch = System.currentTimeMillis();
                    break;
            }
            timeResults += endStopwatch - startStopwatch;
        }
        timeResults /= indexes.length;
        return new TimeResult(list.getClass().getSimpleName(), methodName, timeResults);
    }

    public TimeResult addIterator(List<Integer> type, int size, int[] indexes) {
        list = type;
        initList(size);

        for (int index : indexes) {
            ListIterator<Integer> listIterator = list.listIterator();
            startStopwatch = System.currentTimeMillis();
            for (int j = 0; j < index; j++) {
                if (listIterator.hasNext()) {
                    listIterator.next();
                }
            }
            listIterator.add(randomNumber.nextInt());
            endStopwatch = System.currentTimeMillis();
            timeResults += endStopwatch - startStopwatch;
        }
        timeResults /= indexes.length;
        return new TimeResult(list.getClass().getSimpleName(), "iterator.add", timeResults);
    }

    public TimeResult removeIterator(List<Integer> type, int size, int[] indexes) {
        list = type;
        initList(size);

        for (int i = 0; i < indexes.length; i++) {
            Iterator iterator = list.iterator();
            startStopwatch = System.currentTimeMillis();
            for (int j = 0; j < indexes[i]; j++) {
                if (iterator.hasNext()) {
                    iterator.next();
                }
            }
            iterator.remove();
            endStopwatch = System.currentTimeMillis();
            list.add(randomNumber.nextInt());
            timeResults += endStopwatch - startStopwatch;
        }
        timeResults /= indexes.length;
        return new TimeResult(list.getClass().getSimpleName(), "iterator.remove", timeResults);
    }

    private void initList(int size) {
        randomNumber = new Random();
        while (list.size() < size) {
            list.add(randomNumber.nextInt());
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