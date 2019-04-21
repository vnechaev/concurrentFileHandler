package ConcurrentFileHandler.generating;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import org.apache.commons.io.FileUtils;

public class BigFile {

    public static final int LINES_STEP_CHECKING_FILE_SIZE = 20000;

    public static void createRandomAlphabetTextFile(String fileName) {
        Random random = new Random();

        //Size in Mbs of file that will created
        double wantedSize = 11;

        File file = new File(fileName);
        long start = System.currentTimeMillis();
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)), false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int counter = 0;
        while (true) {
            for (int i = 0; i < 100; i++) {
                writer.print((char) (random.nextInt(26) + 'a'));
            }
            writer.println();
            //Check if the current size is desirable
            if (++counter == LINES_STEP_CHECKING_FILE_SIZE) {
                System.out.println(String.format("Size: %s", FileUtils.byteCountToDisplaySize(file.length())));
                if (file.length() >= wantedSize * 1024 * 1024) {
                    writer.close();
                    break;
                } else {
                    counter = 0;
                }
            }
        }
        long time = System.currentTimeMillis() - start;
        System.out.printf("Took %.1f seconds to createRandomAlphabetTextFile a file of %s", time / 1e3, FileUtils.byteCountToDisplaySize(file.length()));
    }
}
