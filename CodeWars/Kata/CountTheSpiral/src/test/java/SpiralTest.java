import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class SpiralTest {

    @Test
    public void test05() {
        BigInteger input = BigInteger.valueOf(5);
        BigInteger expected = BigInteger.valueOf(17);

        BigInteger actual = Spiral.sum(input);

        assertEquals(expected, actual);
    }

    @Test
    public void test10() {
        BigInteger input = BigInteger.valueOf(10);
        BigInteger expected = BigInteger.valueOf(59);

        BigInteger actual = Spiral.sum(input);

        assertEquals(expected, actual);
    }

    @Test
    public void test1000() {
        BigInteger input = BigInteger.valueOf(1000);
        BigInteger expected = BigInteger.valueOf(500999);

        BigInteger actual = Spiral.sum(input);

        assertEquals(expected, actual);
    }

}