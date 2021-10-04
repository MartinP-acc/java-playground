import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class KidMathTest {

    private static final BigInteger two=new BigInteger("2");
    private static final BigInteger no=new BigInteger("-1");
    @Test
    public void FixedTest1()
    {
        assertEquals(no, KidMath.pow(no, no));
    }
    @Test public void FixedTest2()
    {
        assertEquals(no, KidMath.pow(two, no));
    }
    @Test public void FixedTest3()
    {
        assertEquals(new BigInteger("4"), KidMath.pow(two, two));
    }
    @Test public void FixedTest4()
    {
        final BigInteger three=new BigInteger("3");
        assertEquals(new BigInteger("81"), KidMath.pow(three, three));
    }

}