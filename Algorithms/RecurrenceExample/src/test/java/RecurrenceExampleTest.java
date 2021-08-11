import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecurrenceExampleTest {

    @Test
    void shouldReturnSumOfIntegersInArray(){
        assertEquals(12, RecurrenceExample.sum(new int[]{2,4,6}));
        assertEquals(64, RecurrenceExample.sum(new int[]{8,40,-2,3,4,5,6}));
        assertEquals(0, RecurrenceExample.sum(new int[]{0,0,0}));
    }

    @Test
    void shouldReturnZeroIfArrayIsEmpty(){
        assertEquals(0, RecurrenceExample.sum(new int[]{}));
    }

    @Test
    void shouldReturnFirstElementOfArray(){
        assertEquals(10, RecurrenceExample.sum(new int[]{10}));
    }

}