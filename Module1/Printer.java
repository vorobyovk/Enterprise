package mod1_collection_effectiveness;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

public class Printer {
    private final int ROW = 5;
    private final int COLUMN = 8;

    public void printToConsole(List<TimeResult> time, int elementNumber) {
        String[][] resultsTable = new String[ROW][COLUMN];
        resultsTable = tableInit(resultsTable, time);

        System.out.println("It is a results table for " + elementNumber + " elements:");
        for (int i = 0; i < resultsTable.length; i++) {
            for (int j = 0; j < resultsTable[0].length; j++) {
                if (resultsTable[i][j] == null && i > 0 && j > 0) {
                    System.out.printf("%16s", "-");
                } else if (resultsTable[i][j] == null) {
                    System.out.printf("%16s", "");
                } else {
                    System.out.printf("%16s", resultsTable[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println("\n\n");
    }

    public void printToFile(List<TimeResult> time, int elementNumber) {
        String[][] resultsTable = new String[ROW][COLUMN];
        resultsTable = tableInit(resultsTable, time);

        try (Writer writerToFile = new BufferedWriter(new FileWriter("result.txt", true))) {
            writerToFile.write("It is a results table for " + elementNumber + " elements:\n");
            for (int i = 0; i < resultsTable.length; i++) {
                for (int j = 0; j < resultsTable[0].length; j++) {
                    if (resultsTable[i][j] == null && i > 0 && j > 0) {
                        writerToFile.write(String.format("%16s", "-"));
                    } else if (resultsTable[i][j] == null) {
                        writerToFile.write(String.format("%16s", ""));
                    } else {
                        writerToFile.write(String.format("%16s", resultsTable[i][j]));
                    }
                }
                writerToFile.write('\n');
            }
            writerToFile.write("\n\n\n");
        } catch (Exception ex) {
            System.out.println("Error. The writing process was failed.");
        }
    }

    public String[][] tableInit(String[][] resultsTable, List<TimeResult> time) {
        resultsTable[1][0] = "ArrayList";
        resultsTable[2][0] = "LinkedList";
        resultsTable[3][0] = "HashSet";
        resultsTable[4][0] = "TreeSet";

        resultsTable[0][1] = "add";
        resultsTable[0][2] = "get";
        resultsTable[0][3] = "remove";
        resultsTable[0][4] = "contains";
        resultsTable[0][5] = "populate";
        resultsTable[0][6] = "iterator.add";
        resultsTable[0][7] = "iterator.remove";

        for (TimeResult aTime : time) {
            for (int j = 1; j < resultsTable.length; j++) {
                if (resultsTable[j][0].equals(aTime.getRow())) {
                    for (int k = 1; k < resultsTable[0].length; k++) {
                        if (resultsTable[0][k].equals(aTime.getColumn())) {
                            Double resultTime = aTime.getTime();
                            resultsTable[j][k] = resultTime.toString();
                            break;
                        }
                    }
                    break;
                }
            }


        }
        return resultsTable;
    }
}