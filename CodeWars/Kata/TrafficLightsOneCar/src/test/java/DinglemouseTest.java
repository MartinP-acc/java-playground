import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DinglemouseTest {


    private static void doTest(String initial, String[] expected, int n) {
        String[] got = Dinglemouse.trafficLights(initial, n);
        assertEquals(expected.length, got.length);

        int errIdx = -1;
        for (int i = 0; i < got.length; i++) {
            if (!expected[i].equals(got[i])) { errIdx = i; break; }
        }

        System.out.println("Expected:");
        for (int i = 0; i < expected.length; i++) {
            System.out.printf("%03d %s%n", i, expected[i]);
        }

        if (errIdx >= 0) {
            System.out.println("\nYour result:");
            for (int i = 0; i < got.length; i++) {
                System.out.printf("%03d %s%n", i, got[i]);
            }
            fail(String.format("A difference was detected at index %d", errIdx));
        }
    }

    @Test
    public void example() {
        int n = 10;
        String[] sim = {
                "C...R............G......",  // 0
                ".C..R............G......",  // 1
                "..C.R............G......",  // 2
                "...CR............G......",  // 3
                "...CR............G......",  // 4
                "....C............O......",  // 5
                "....GC...........R......",  // 6
                "....G.C..........R......",  // 7
                "....G..C.........R......",  // 8
                "....G...C........R......",  // 9
                "....O....C.......R......"   // 10
        };
        doTest(sim[0], sim, n);
    }

}