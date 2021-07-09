import java.math.BigInteger;

public class Prime {

    public void checkPrime(int ... a){

        StringBuilder out = new StringBuilder();

        for (int n : a){
            String s = Integer.toString(n);
            if (new BigInteger(s).isProbablePrime(1)){
                out.append(s).append(" ");
            }
        }
        System.out.println(out);
    }
}
