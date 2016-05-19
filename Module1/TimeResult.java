package mod1_collection_effectiveness;

public class TimeResult {
    private String row;
    private String column;
    private double time;

    public TimeResult(String row, String column, double time) {
        this.row = row;
        this.column = column;
        this.time = time;
    }

    public String getRow() {
        return row;
    }

    public String getColumn() {
        return column;
    }

    public double getTime() {
        return time;
    }

}