package ConcurrentFileHandler;

import ConcurrentFileHandler.exceptions.NotOnlyAlphabetException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static final int THREAD_COUNT = 5;

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException, NotOnlyAlphabetException {
        String inputString = ReadWriteHelper.read("src/main/resources/alphabet.txt");
        String outputFileName = "target/result.txt";

        readConcurrentProcessWrite(inputString, outputFileName);
    }

    public static void readConcurrentProcessWrite(String inputString, String outputFileName) throws InterruptedException, ExecutionException, IOException {
        String resultString = concurrentProcessCharToNextAlphabet(inputString);
        ReadWriteHelper.writeResultToFile(resultString, outputFileName);
    }

    public static String concurrentProcessCharToNextAlphabet(String inputString) throws InterruptedException, ExecutionException {
        int size = inputString.length();
        List<Interval> taskIntervals = Interval.divideJobToIntervals(THREAD_COUNT, size);
        ExecutorService executors = Executors.newFixedThreadPool(THREAD_COUNT);

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
        executors.shutdown();
        return stringBuilder.toString();
    }


    public static String moveEveryLetterInIntervalToNextAlphabet(String inputString, Interval interval) {
        String inputChunk = inputString.substring(interval.getStart(), interval.getEnd());
        char[] inputChunkCharArray = inputChunk.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char character : inputChunkCharArray) {
            if (character == '\n') {
                stringBuilder.append(character);
            } else {
                if (character != 'z') {
                    stringBuilder.append((char) (character + 1));
                } else {
                    stringBuilder.append('a');
                }
            }
        }
        return stringBuilder.toString();
    }


}