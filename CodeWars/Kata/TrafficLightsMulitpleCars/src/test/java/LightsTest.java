import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LightsTest {

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
        int n = 16;
        String[] sim = {
                "CCC.G...R...", // 0 initial state as passed
                ".CCCG...R...", // 1
                "..CCC...R...", // 2 show 1st car, not the green light
                "..CCGC..R...", // 3 2nd car cannot enter intersection because 1st car blocks the exit
                "...CC.C.R...", // 4 show 2nd car, not the green light
                "...COC.CG...", // 5 3rd car stops for the orange light
                "...CR.C.C...", // 6
                "...CR..CGC..", // 7
                "...CR...C.C.", // 8
                "...CR...GC.C", // 9
                "...CR...O.C.", // 10
                "....C...R..C", // 11 3rd car can proceed
                "....GC..R...", // 12
                "....G.C.R...", // 13
                "....G..CR...", // 14
                "....G..CR...", // 15
                "....O...C..."  // 16
        };
        doTest(sim[0], sim, n);
    }


}