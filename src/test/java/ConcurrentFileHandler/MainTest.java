package ConcurrentFileHandler;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void checkBorderLetter() {
        String actual = Main.moveEveryLetterInIntervalToNextAlphabet(
                "z",
                new Interval(0, 1)
        );
        assertEquals("Неверный результат обработки буквы z", "a", actual);
    }

    @Test
    public void checkLength(){
        String inputString = "www";
        String outputString = Main.moveEveryLetterInIntervalToNextAlphabet(
                inputString,
                new Interval(0, inputString.length()
                ));
        assertEquals("Неверная длина результата", 3, outputString.length());

    }

    @Test
    public void checkResultStringtOrder() {
        String inputString = "abcdefgh";
        String outputString = Main.moveEveryLetterInIntervalToNextAlphabet(
                inputString,
                new Interval(0, inputString.length()
                ));
        assertEquals("Некорректный порядок преобразования", "bcdefghi", outputString);
    }
}