import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionReductionTest {

    @Test
    public void testSimpleDirReduc() {
        assertEquals(2,
                DirectionReduction.dirReduc(new String[]{"EAST", "EAST", "WEST", "NORTH", "WEST", "EAST", "EAST", "SOUTH", "NORTH", "WEST"}).length);
    }
}