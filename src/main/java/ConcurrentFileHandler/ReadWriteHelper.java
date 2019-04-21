package ConcurrentFileHandler;

import ConcurrentFileHandler.exceptions.NotOnlyAlphabetException;

import java.io.*;

public class ReadWriteHelper {


    public static String read(String filename) throws IOException, NotOnlyAlphabetException {
        //чтение по строкам
        BufferedReader in = new BufferedReader(new FileReader(filename));

        String s;
        StringBuilder sb = new StringBuilder();
        String lineSeparator = "";
        while ((s = in.readLine()) != null) {
            if (s.chars().allMatch(Character::isLetter)) {
                sb.append(lineSeparator).append(s);
            } else {
                throw new NotOnlyAlphabetException(String.format("File %s contains not only alphabet symbols", filename));
            }
            lineSeparator = "\n";
        }
        in.close();
        return sb.toString();
    }

    public static void writeResultToFile(String result, String outputFileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
        writer.write(result);
        writer.close();
    }
}
