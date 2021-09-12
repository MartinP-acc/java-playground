import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DinglemouseTest {

    private void checkCloseEnough(final double expected, final double got) {
        assertTrue(Math.abs(expected - got) <= 0.001);
    }

    @Test
    public void example() {

        int[] a, b, c;

        a = new int[]{10,10}; c = new int[]{20, 25}; b = new int[]{30,10};
        checkCloseEnough(15d, Dinglemouse.distanceFromLine(a,b,c));

        a = new int[]{10,10}; c = new int[]{40, 40}; b = new int[]{70,70};
        checkCloseEnough(0d, Dinglemouse.distanceFromLine(a,b,c));

        a = new int[]{5,10}; c = new int[]{25,30}; b = new int[]{5,10};
        checkCloseEnough(28.2843, Dinglemouse.distanceFromLine(a,b,c));
    }

}