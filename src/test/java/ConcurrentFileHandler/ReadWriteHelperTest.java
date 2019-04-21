package ConcurrentFileHandler;

import ConcurrentFileHandler.exceptions.NotOnlyAlphabetException;
import org.junit.Test;

import java.io.IOException;


public class ReadWriteHelperTest {

    @Test(expected = NotOnlyAlphabetException.class)
    public void checkExceptionWithNumber() throws IOException, NotOnlyAlphabetException {
        String fileForProcess = "target/checkExceptionWithNumber";
        ReadWriteHelper.writeResultToFile("aaa1", fileForProcess);
        ReadWriteHelper.read(fileForProcess);
    }

    @Test(expected = NotOnlyAlphabetException.class)
    public void checkExceptionWithPunctuation() throws IOException, NotOnlyAlphabetException {
        String fileForProcess = "target/checkExceptionWithPunctuation";
        ReadWriteHelper.writeResultToFile("aaa.", fileForProcess);
        ReadWriteHelper.read(fileForProcess);
    }

    @Test(expected = NotOnlyAlphabetException.class)
    public void checkExceptionWithSpecialSymbols() throws IOException, NotOnlyAlphabetException {
        String fileForProcess = "target/checkExceptionWithSpecialSymbols";
        ReadWriteHelper.writeResultToFile("aaa/", fileForProcess);
        ReadWriteHelper.read(fileForProcess);
    }


}
