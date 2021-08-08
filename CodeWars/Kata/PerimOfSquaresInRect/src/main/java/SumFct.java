import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SumFct {
    public static BigInteger perimeter(BigInteger n) {
        if (n.intValue() == 0) return n;
        List<BigInteger> seq = new ArrayList<>();
        seq.add(new BigInteger("0"));
        seq.add(new BigInteger("1"));
        for (int i=1; i<=n.intValue(); i++){
            seq.add(seq.get(i).add(seq.get(i-1)));
        }
        return seq.stream().reduce(BigInteger::add).get().multiply(new BigInteger("4"));
    }
}