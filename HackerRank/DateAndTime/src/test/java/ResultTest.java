import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultTest {

    @Test
    void checkSomeDates(){
        assertEquals("MONDAY", Result.findDay(7,5,2021));
        assertEquals("SATURDAY", Result.findDay(5,8,2021));
        assertEquals("WEDNESDAY", Result.findDay(8,5,2015));
    }

}