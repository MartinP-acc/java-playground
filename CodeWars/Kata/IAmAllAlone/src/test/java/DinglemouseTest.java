import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DinglemouseTest {

    @Test
    public void ex1() {
        char[][] house = {
                "  o                o        #######".toCharArray(),
                "###############             #     #".toCharArray(),
                "#             #        o    #     #".toCharArray(),
                "#  X          ###############     #".toCharArray(),
                "#                                 #".toCharArray(),
                "###################################".toCharArray()
        };
        assertTrue(Dinglemouse.allAlone(house));
    }

    @Test
    public void ex2() {
        char[][] house = {
                "#################             ".toCharArray(),
                "#     o         #   o         ".toCharArray(),
                "#          ######        o    ".toCharArray(),
                "####       #                  ".toCharArray(),
                "   #       ###################".toCharArray(),
                "   #                         #".toCharArray(),
                "   #                  X      #".toCharArray(),
                "   ###########################".toCharArray()
        };
        assertFalse(Dinglemouse.allAlone(house));
    }

}