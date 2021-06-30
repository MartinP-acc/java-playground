import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PerfectPowerTest {

    @Test
    void whenIntegerHavePerfectPowerShouldReturnIt(){
        assertArrayEquals(new int[]{2,2}, PerfectPower.findIfPerfect(4));
        assertArrayEquals(new int[]{2,3}, PerfectPower.findIfPerfect(8));
        assertArrayEquals(new int[]{3,2}, PerfectPower.findIfPerfect(9));
        assertArrayEquals(new int[]{5,2}, PerfectPower.findIfPerfect(25));
        assertArrayEquals(new int[]{12,2}, PerfectPower.findIfPerfect(144));
        assertArrayEquals(new int[]{7,5}, PerfectPower.findIfPerfect(16807));
    }

    @Test
    void whenIntegerDoNotHavePerfectPowerShouldReturnNull(){
        assertNull(PerfectPower.findIfPerfect(5));
    }

}