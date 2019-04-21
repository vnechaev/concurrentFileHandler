package ConcurrentFileHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedInputFile {

    public static String read(String filename) throws IOException {
        //чтение по строкам
        BufferedReader in = new BufferedReader(new FileReader(filename));

        String s;
        StringBuilder sb = new StringBuilder();

        while ((s = in.readLine()) != null) {
            if (s.chars().allMatch(Character::isLetter)) {
                sb.append(s).append("\n");
            } else {
                throw new RuntimeException(String.format("File %s contains not only alphabet symbols", filename));
            }
        }
        in.close();
        return sb.toString();
    }
}
