import java.util.Arrays;

public class Solution {

    public int solution(int[] a){

        Arrays.sort(a);

        long p, q , r;

        for (int i=2; i<a.length; i++){

            p=a[i];
            q=a[i-1];
            r=a[i-2];

            if (p+q>r && p+r>q && q+r>p) return 1;
        }

        return 0;
    }
}
