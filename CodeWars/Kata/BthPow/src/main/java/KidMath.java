import java.math.BigInteger;
public class KidMath
{
    public static BigInteger pow(BigInteger base, BigInteger exponent) {
        if (exponent.intValue() < 1) return new BigInteger("-1");
        for (int i=1; i<exponent.intValue(); i++) base = base.multiply(base);
        return base;
    }
}