import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KataTest {

    @Test
    public void fixedTests() {
        doTest(new int[]{3, -4, 8, 7, -10, 19, -3}, 24);
        doTest(new int[]{2, -3, -3, 9, -29, 8, -9}, 9);
    }
    private void doTest(final int[] numbers, final int expected) {
        assertEquals(expected, Kata.maxContiguousSum(numbers));
    }

}