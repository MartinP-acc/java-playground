import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DinglemouseTest {

    @Test
    public void example() {
        final int[] dice = {2,1,5,1,5,4};
        final int[] board = {0,0,3,0,0,0,0,-2,0,0,0};
        assertEquals(10, Dinglemouse.snakesAndLadders(board, dice));
    }


}