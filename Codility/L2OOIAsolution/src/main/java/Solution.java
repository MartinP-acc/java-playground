
public class Solution {

    public int solution(int[] A){

        if (A.length!=0){
            int result = A[0];

            for (int n : A){
                result ^= n;
            }
            return result;
        } else {
            return 0;
        }
    }
}
