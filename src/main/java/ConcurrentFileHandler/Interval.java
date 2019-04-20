package ConcurrentFileHandler;

import java.util.ArrayList;
import java.util.List;

class Interval {
    private int start;
    private int end;


    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    static List<Interval> divideJobToIntervals(int parts, int size) {
        int chunkSize = size / parts;
        int chunkExtra = size % parts;
        List<Interval> taskIntervals = new ArrayList<>();

        for (int chunkNum = 0; chunkNum < parts; chunkNum++) {
            int start = chunkNum * chunkSize;
            int end = start + chunkSize;
            if (chunkNum + 1 == parts) {
                taskIntervals.add(new Interval(start, end + chunkExtra));
            }
            else{
                taskIntervals.add(new Interval(start, end));
            }
        }
        return taskIntervals;
    }
}