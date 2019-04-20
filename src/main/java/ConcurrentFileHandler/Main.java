package ConcurrentFileHandler;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final String inputString = "abcdefghijklmnoqxyz";

        int parts = 5;
        int size = inputString.length();

        List<Interval> taskIntervals = Interval.divideJobToIntervals(parts, size);

        ExecutorService executors = Executors.newFixedThreadPool(parts);
        List<Future<String>> futures = new LinkedList<>();

        for (Interval interval : taskIntervals) {
            Future<String> future = executors.submit(
                    () -> moveEveryLetterInIntervalToNextAlphabet(inputString, interval)
            );
            futures.add(future);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Future future : futures) {
            stringBuilder.append(future.get());
        }

        System.out.println(stringBuilder.toString());
    }

    public static String moveEveryLetterInIntervalToNextAlphabet(String inputString, Interval interval) {
        String inputChunk = inputString.substring(interval.getStart(), interval.getEnd());
        char[] array = inputChunk.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char character : array) {
            stringBuilder.append((char) (character + 1));
        }
        return stringBuilder.toString();
    }


}