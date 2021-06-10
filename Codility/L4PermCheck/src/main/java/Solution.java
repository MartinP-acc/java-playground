import java.util.Arrays;

public class Solution {

    public int solution(int[] A){
        Arrays.sort(A);
        for (int N=1; N<=A.length;N++){
            if (N!=A[N-1]) return 0;
        }
        return 1;
    }
}
