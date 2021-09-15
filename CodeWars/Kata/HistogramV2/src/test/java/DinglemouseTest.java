import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DinglemouseTest {

    private String show(final int results[], String prompt, String hist) {
        System.out.println("Data: " + Arrays.toString(results));
        System.out.println(prompt);
        System.out.println("<pre style='font-family:monospace;color:orange'>");
        System.out.println(hist);
        System.out.println("</pre>");
        return hist;
    }

    @Test
    public void example() {
        final String expected =
                "      70%\n" +
                        "      ██\n" +
                        "      ██\n" +
                        "      ██\n" +
                        "      ██\n" +
                        "      ██\n" +
                        "      ██\n" +
                        "      ██\n" +
                        "      ██ 15%\n" +
                        "7%    ██ ██\n" +
                        "██ 3% ██ ██    5%\n" +
                        "------------------\n"+
                        " 1  2  3  4  5  6\n";
        int results[] = new int[]{14, 6, 140, 30, 0, 10};
        String hist = Dinglemouse.histogram(results);
        show(results, "Your answer:", hist);
        assertEquals(expected, hist);
    }

    @Test
    public void test2() {
        final String expected =
                        "         33%\n" +
                        "         ██\n" +
                        "   22%   ██\n" +
                        "16%██    ██    16%\n" +
                        "██ ██    ██    ██\n" +
                        "██ ██ 5% ██ 5% ██\n" +
                        "------------------\n" +
                        " 1  2  3  4  5  6\n";
        int results[] = new int[]{3, 4, 1, 6, 1, 3};
        String hist = Dinglemouse.histogram(results);
        show(results, "Your answer:", hist);
        assertEquals(expected, hist);
    }

    @Test
    public void test3() {
        final String expected =
                "         26%\n" +
                        "   20%   ██ 20%20%\n" +
                        "   ██    ██ ██ ██\n" +
                        "6% ██ 6% ██ ██ ██\n" +
                        "██ ██ ██ ██ ██ ██\n" +
                        "------------------\n" +
                        " 1  2  3  4  5  6\n";
        int results[] = new int[]{1, 3, 1, 4, 3, 3};
        String hist = Dinglemouse.histogram(results);
        show(results, "Your answer:", hist);
        assertEquals(expected, hist);
    }



}