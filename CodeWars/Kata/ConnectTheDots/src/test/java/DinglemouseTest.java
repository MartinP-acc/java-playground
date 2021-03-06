import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DinglemouseTest {

    @Test
    public void example1() {
        String in =
                "           \n" +
                        " a       b \n" +
                        " e         \n" +
                        "           \n" +
                        " d       c \n" +
                        "           \n";
        String out =
                "           \n" +
                        " ********* \n" +
                        " *       * \n" +
                        " *       * \n" +
                        " ********* \n" +
                        "           \n";
        Util.show(in, out);
        assertEquals(out, Dinglemouse.connectTheDots(in));
    }

    @Test
    public void example2() {
        String in =
                "           \n" +
                        "     a     \n" +
                        "    e      \n" +
                        "           \n" +
                        "  d     b  \n" +
                        "           \n" +
                        "           \n" +
                        "     c     \n" +
                        "           \n";
        String out =
                "           \n" +
                        "     *     \n" +
                        "    * *    \n" +
                        "   *   *   \n" +
                        "  *     *  \n" +
                        "   *   *   \n" +
                        "    * *    \n" +
                        "     *     \n" +
                        "           \n";
        Util.show(in, out);
        assertEquals(out, Dinglemouse.connectTheDots(in));
    }

    @Test
    public void example3() {
        String in = "  e     f   m     n   \n" +
                " d       g l       o  \n" +
                "          j           \n" +
                " c       h k       p  \n" +
                "  b     a   s     r   \n";

        String out = "  *******   *******   \n" +
                " *       * *       *  \n" +
                " *       ***       *  \n" +
                " *       * *       *  \n" +
                "  *******   *******   \n";

        Util.show(in, out);
        assertEquals(out, Dinglemouse.connectTheDots(in));
    }

}