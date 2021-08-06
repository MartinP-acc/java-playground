import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdFibTest {

    @Test
    public void test1() {
        long[] r = new long[] {55, 89, 1};
        assertArrayEquals(r, ProdFib.productFib(4895));
    }
    @Test
    public void test2() {
        long[] r = new long[] {89, 144, 0};
        assertArrayEquals(r, ProdFib.productFib(5895));
    }

    @Test
    public void test3() {
        long[] r = new long[] {0, 1, 1};
        assertArrayEquals(r, ProdFib.productFib(0));
    }

}