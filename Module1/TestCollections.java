package mod1_collection_effectiveness;

import java.util.*;

public class TestCollections {
    private List<List<TimeResult>> time;
    private final int MONITORING_SIZE = 100;
    private int[] indexes = new int[MONITORING_SIZE];
    private final int[] ELEMENTS = new int[]{10_000, 100_000, 1000_000};

    {
        time = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            time.add(new ArrayList<>());
        }
    }

    public void add() {
        for (int i = 0; i < time.size(); i++) {
            setIndexes(ELEMENTS[i]);
            time.get(i).add(new MyList().useMethod(new ArrayList<>(), ELEMENTS[i], indexes, "add"));
            time.get(i).add(new MyList().useMethod(new LinkedList<>(), ELEMENTS[i], indexes, "add"));
            time.get(i).add(new MySet().useMethod(new HashSet<>(), ELEMENTS[i], "add"));
            time.get(i).add(new MySet().useMethod(new TreeSet<>(), ELEMENTS[i], "add"));
        }
    }

    public void get() {
        for (int i = 0; i < time.size(); i++) {
            setIndexes(ELEMENTS[i]);
            time.get(i).add(new MyList().useMethod(new ArrayList<>(), ELEMENTS[i], indexes, "get"));
            time.get(i).add(new MyList().useMethod(new LinkedList<>(), ELEMENTS[i], indexes, "get"));
        }
    }

    public void remove() {
        for (int i = 0; i < time.size(); i++) {
            setIndexes(ELEMENTS[i]);
            time.get(i).add(new MyList().useMethod(new ArrayList<>(), ELEMENTS[i], indexes, "remove"));
            time.get(i).add(new MyList().useMethod(new LinkedList<>(), ELEMENTS[i], indexes, "remove"));
            time.get(i).add(new MySet().useMethod(new HashSet<>(), ELEMENTS[i], "remove"));
            time.get(i).add(new MySet().useMethod(new TreeSet<>(), ELEMENTS[i], "remove"));
        }
    }

    public void contains() {
        for (int i = 0; i < time.size(); i++) {
            time.get(i).add(new MyList().useMethod(new ArrayList<>(), ELEMENTS[i], indexes, "contains"));
            time.get(i).add(new MyList().useMethod(new LinkedList<>(), ELEMENTS[i], indexes, "contains"));
            time.get(i).add(new MySet().useMethod(new HashSet<>(), ELEMENTS[i], "contains"));
            time.get(i).add(new MySet().useMethod(new TreeSet<>(), ELEMENTS[i], "contains"));
        }
    }

    public void populate() {
        for (int i = 0; i < time.size(); i++) {
            time.get(i).add(new MyList().useMethod(new ArrayList<>(), ELEMENTS[i], indexes, "populate"));
            time.get(i).add(new MyList().useMethod(new LinkedList<>(), ELEMENTS[i], indexes, "populate"));
            time.get(i).add(new MySet().useMethod(new HashSet<>(), ELEMENTS[i], "populate"));
            time.get(i).add(new MySet().useMethod(new TreeSet<>(), ELEMENTS[i], "populate"));
        }
    }

    public void iteratorAdd() {
        for (int i = 0; i < time.size(); i++) {
            setIndexes(ELEMENTS[i]);
            time.get(i).add(new MyList().addIterator(new ArrayList<>(), ELEMENTS[i], indexes));
            time.get(i).add(new MyList().addIterator(new LinkedList<>(), ELEMENTS[i], indexes));
        }
    }

    public void iteratorRemove() {
        for (int i = 0; i < time.size(); i++) {
            setIndexes(ELEMENTS[i]);
            time.get(i).add(new MyList().removeIterator(new ArrayList<>(), ELEMENTS[i], indexes));
            time.get(i).add(new MyList().removeIterator(new LinkedList<>(), ELEMENTS[i], indexes));
        }
    }

    public void setIndexes(int size) {
        Random randomNumber = new Random();
        for (int i = 0; i < MONITORING_SIZE; i++) {
            indexes[i] = randomNumber.nextInt(size);
        }
    }

    public void printResults() {
        Printer printer = new Printer();

        for (int i = 0; i < time.size(); i++) {
            printer.printToConsole(time.get(i), ELEMENTS[i]);
            printer.printToFile(time.get(i), ELEMENTS[i]);
        }
    }
}