package ConcurrentFileHandler;

import ConcurrentFileHandler.exceptions.NotOnlyAlphabetException;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FullMethodTest {
    private String inputFileName = "src/main/resources/alphabet.txt";
    private File inputFile = new File(inputFileName);
    private String outputFileName = "target/outputName.txt";

    @Test
    public void checkSizeOfFiles() throws IOException, NotOnlyAlphabetException, ExecutionException, InterruptedException {
        File outputFile = new File(outputFileName);

        Main.readConcurrentProcessWrite(
                ReadWriteHelper.read(inputFileName),
                outputFileName
        );

        assertEquals(
                "Размеры исходного и полученного файлов отличаются",
                inputFile.length(),
                outputFile.length()
        );
    }

    @Test
    public void checkNumberOfLines() throws IOException, NotOnlyAlphabetException, ExecutionException, InterruptedException {
        Main.readConcurrentProcessWrite(
                ReadWriteHelper.read(inputFileName),
                outputFileName
        );

        BufferedReader inputReader = new BufferedReader(new FileReader(inputFileName));
        long inputFileLines = inputReader.lines().count();
        inputReader.close();

        BufferedReader outputReader = new BufferedReader(new FileReader(outputFileName));
        long outputFileLines = outputReader.lines().count();
        outputReader.close();

        assertEquals(
                "Количество строк различается в файлах",
                inputFileLines,
                outputFileLines
        );
    }

    @Test()
    public void checkBorderLetter() throws IOException, ExecutionException, InterruptedException, NotOnlyAlphabetException {
        String ouputresult = Main.concurrentProcessCharToNextAlphabet(ReadWriteHelper.read("src/main/resources/OnlyCharZ.txt"));
        assertTrue(
                "Неверная обработка граничного случая - буквы z",
                ouputresult.chars().filter(a -> a != '\n').allMatch(character -> character == 'a')
        );
    }

    @Test
    public void checkCorrectOrder() throws InterruptedException, ExecutionException, IOException, NotOnlyAlphabetException {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String inputFileAlphabet = "target/alphabet.txt";
        ReadWriteHelper.writeResultToFile(alphabet, inputFileName);
        String outputFileShiftAlphabet = "target/shiftAlphabet.txt";
        Main.readConcurrentProcessWrite(inputFileAlphabet, outputFileShiftAlphabet);

        String shiftAlphabetExpected = "bcdefghijklmnopqrstuvwxyza";
        assertEquals("Неверная обработка алфавита", shiftAlphabetExpected, ReadWriteHelper.read(outputFileName));
    }
}


