import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DinglemouseTest {

    @Test
    public void ex() {
        char[][] mountain = {
                "^^^^^^        ".toCharArray(),
                " ^^^^^^^^     ".toCharArray(),
                "  ^^^^^^^     ".toCharArray(),
                "  ^^^^^       ".toCharArray(),
                "  ^^^^^^^^^^^ ".toCharArray(),
                "  ^^^^^^      ".toCharArray(),
                "  ^^^^        ".toCharArray()
        };
        assertEquals(3, Dinglemouse.peakHeight(mountain));
    }

    @Test
    public void simple() {
        char[][] mountain = {
                "^^^".toCharArray(),
                "^^^".toCharArray(),
                "^^^".toCharArray(),
        };
        assertEquals(2, Dinglemouse.peakHeight(mountain));
    }

}