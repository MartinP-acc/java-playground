import java.math.BigInteger;
import java.util.LinkedList;

public class GapInPrimes {

    public static long[] gap(int g, long m, long n) {
        LinkedList<Long> primes = new LinkedList<>();
        for (long i=m; i<=n; i++){
            if (new BigInteger(String.valueOf(i)).isProbablePrime(1)){
                primes.addLast(i);
                if (primes.size()>2) primes.pollFirst();
                if (primes.size()==2 && primes.peekLast()-primes.peekFirst()==g){
                    System.out.println(primes.peekLast()+" "+primes.peekFirst());
                    return new long[]{primes.peekFirst(), primes.peekLast()};
                }
            }
        }
        return null;
    }
}
