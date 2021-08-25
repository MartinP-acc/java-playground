/*
         maxk    maxn
    S = SIGMA ( SIGMA (1 / k * (n+1)^2k) )
         k=1     n=1
 */

public class Magnets {

    public static double doubles(int maxk, int maxn) {
        double S = 0.0;
        for (int k = 1 ; k <= maxk ; k++){
            for (int n = 1; n <= maxn ; n++){
                S+= 1 / (k * Math.pow( (n+1), 2*k ));
            }
        }
        return S;
    }
}
