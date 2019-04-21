package ConcurrentFileHandler.generating;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class BigFile {

    public static final int LINES_STEP_CHECKING_FILE_SIZE = 20000;
    public static final int LINE_LENGTH = 100;

    public static String createRandomAlphabetTextFile(String fileName) {
        //Size in Mbs of file that will created
        double wantedSize = 11;
        return createRandomAlphabetTextFile(fileName, wantedSize);
    }

    public static String createRandomAlphabetTextFile(String fileName, double wantedSize) {
        Random random = new Random();
        File file = new File(fileName);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)), false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int counter = 0;
        while (true) {
            for (int i = 0; i < LINE_LENGTH; i++) {
                writer.print((char) (random.nextInt(26) + 'a'));
            }
            writer.println();
            //Check if the current size is desirable
            if (++counter == LINES_STEP_CHECKING_FILE_SIZE) {
                if (file.length() >= wantedSize * 1024 * 1024) {
                    writer.close();
                    break;
                } else {
                    counter = 0;
                }
            }
        }
        return file.getPath();
    }
}
