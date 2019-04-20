package ConcurrentFileHandler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final String strExample = "abcdefghijklmnoqxyz";

        int parts = 5;
        int size = strExample.length();

        List<Interval> taskIntervals = getTaskCharIntervals(parts, size);

        ExecutorService executor = Executors.newFixedThreadPool(parts);
        List<Future<String>> futures = new LinkedList<>();

        for (Interval interval : taskIntervals) {
            Future<String> future = executor.submit(() -> {
                String inputChunk = strExample.substring(interval.getStart(), interval.getEnd());
                char[] array = inputChunk.toCharArray();
                StringBuilder stringBuilder = new StringBuilder();
                for (char character : array) {
                    stringBuilder.append((char) (character + 1));
                }
                return stringBuilder.toString();
            });
            futures.add(future);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Future future : futures) {
            stringBuilder.append(future.get());
        }

        System.out.println(stringBuilder.toString());
    }


    private static List<Interval> getTaskCharIntervals(int parts, int size) {
        int chunkSize = size / parts;
        int chunkExtra = size % parts;
        List<Interval> taskIntervals = new ArrayList<Interval>();

        for (int chunkNum = 0; chunkNum < parts; chunkNum++) {
            int start = chunkNum * chunkSize;
            int end = start + chunkSize;
            if (chunkNum + 1 == parts) taskIntervals.add(new Interval(start, end + chunkExtra));
            else taskIntervals.add(new Interval(start, end));
        }
        return taskIntervals;
    }
}
