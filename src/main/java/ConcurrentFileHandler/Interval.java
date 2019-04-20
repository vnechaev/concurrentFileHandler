package ConcurrentFileHandler;

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
}