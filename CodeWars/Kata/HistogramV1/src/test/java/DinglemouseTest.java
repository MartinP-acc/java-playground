import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DinglemouseTest {

    @Test
    public void basic() {
        final String expected =
                        "    10\n"+
                        "    #\n"+
                        "    #\n"+
                        "7   #\n"+
                        "#   #\n"+
                        "#   #     5\n"+
                        "#   #     #\n"+
                        "# 3 #     #\n"+
                        "# # #     #\n"+
                        "# # # 1   #\n"+
                        "# # # #   #\n"+
                        "-----------\n"+
                        "1 2 3 4 5 6\n";
        assertEquals(expected, Dinglemouse.histogram(new int[]{7,3,10,1,0,5}));
    }

    @Test
    public void zeros() {
        final String expected =
                        "-----------\n"+
                        "1 2 3 4 5 6\n";
        assertEquals(expected, Dinglemouse.histogram(new int[]{0,0,0,0,0,0}));
    }

    @Test
    public void k10CubeTest() {
        final String expected =
                "    10\n"+
                        "    #\n"+
                        "    #           8\n"+
                        "7   #           #\n"+
                        "#   #           # 6\n"+
                        "#   #     5     # #\n"+
                        "#   #     #   4 # #\n"+
                        "# 3 #     # 3 # # #\n"+
                        "# # #     # # # # #\n"+
                        "# # # 1   # # # # #\n"+
                        "# # # #   # # # # #\n"+
                        "-------------------\n"+
                        "1 2 3 4 5 6 7 8 9 10\n";
        assertEquals(expected, Dinglemouse.histogram(new int[]{7,3,10,1,0,5,3,4,8,6}));
    }

}