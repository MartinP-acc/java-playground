import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DinglemouseTest {

    @Test
    public void example() {
        final String expected =
                """
                        6|██ 5%
                        5|
                        4|███████ 15%
                        3|███████████████████████████████████ 70%
                        2|█ 3%
                        1|███ 7%
                        """;
        assertEquals(expected, Dinglemouse.histogram(new int[]{7,3,70,15,0,5}));
    }

}