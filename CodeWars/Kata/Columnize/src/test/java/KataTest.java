import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KataTest {

    @Test
    void shouldHandleSingleColumn() {

        String[] input = { "1", "12", "123", "1234", "12345", "123456" };
        var expected = "1     \n"
                     + "12    \n"
                + "123   \n"
                + "1234  \n"
                + "12345 \n"
                + "123456";
        var actual = Kata.columnize(input, 1);
        assertEquals(expected,actual);
    }

    @Test
    void shouldHandleMultipleColumns() {

        String[] input = { "1", "12", "123", "1234", "12345", "123456" };
        var expected = "1    | 12    | 123   \n"
                + "1234 | 12345 | 123456";
        var actual = Kata.columnize(input, 3);
        assertEquals(expected,actual);
    }

    @Test
    void shouldHandleUnevenColumns() {

        String[] input = { "1", "12", "123", "1234", "12345", "123456" };
        var expected = "1      | 12 | 123 | 1234 | 12345\n"
                + "123456";
        var actual = Kata.columnize(input, 5);
        assertEquals(expected,actual);
    }

    @Test
    void shouldHandleMoreColumnsThanInput() {

        String[] input = { "1", "12", "123", "1234", "12345", "123456" };
        var expected = "1 | 12 | 123 | 1234 | 12345 | 123456";
        var actual = Kata.columnize(input, 999);
        assertEquals(expected,actual);
    }

    @Test
    void shouldHandleEmptyStrings() {

        String[] input = { "", "12", "123", "", "12345", "" };
        var expected = "      | 12\n"
                + "123   |   \n"
                + "12345 |   ";
        var actual = Kata.columnize(input, 2);
        assertEquals(expected,actual);
    }

    @Test
    void shouldHandleEmptyColumns() {

        String[] input = { "", "", "", ""};
        var expected = " | \n"
                + " | ";
        var actual = Kata.columnize(input, 2);
        assertEquals(expected,actual);
    }

    @Test
    void shouldHandleWhitespace() {

        String[] input = { "1          ", "12", "123", "1234", "12345", "      " };
        var expected = "1           | 12    \n"
                     + "123         | 1234  \n"
                     + "12345       |       ";
        var actual = Kata.columnize(input, 2);
        assertEquals(expected,actual);
    }

}