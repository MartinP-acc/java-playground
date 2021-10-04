import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DRootTest {

    @Test
    public void Test1() {
        assertEquals( 7, DRoot.digital_root(16));
    }
    @Test
    public void Test2() {
        assertEquals( 6, DRoot.digital_root(456));
    }

}