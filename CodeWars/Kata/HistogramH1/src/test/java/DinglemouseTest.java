import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DinglemouseTest {

    @Test
    public void basic() {
        final String expected =
                "6|##### 5\n"+
                        "5|\n"+
                        "4|# 1\n"+
                        "3|########## 10\n"+
                        "2|### 3\n"+
                        "1|####### 7\n";
        assertEquals(expected, Dinglemouse.histogram(new int[]{7,3,10,1,0,5}));
    }
}