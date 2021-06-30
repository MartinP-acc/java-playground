public class PerfectPower {

    public static int[] findIfPerfect(int n){
        if (n<4) return null;
        for (int i=2; i<=n/2; i++){
            if (i*i>n) break;
            if (n%i==0){
                for (int j=2; j<=n/2; j++){
                    int pow = (int) Math.pow(i,j);
                    if (pow==n) return new int[]{i,j};
                    if (pow>n) break;
                }
            }
        }
        return null;
    }
}

