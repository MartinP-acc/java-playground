

public class Solution {

    public int solution(int[] A){
        int sum = 0;
        int sumExpected = 1 + A.length;
        for (int i=0; i<A.length; i++){
            sum+=A[i];
            sumExpected+= (i+1);
        }
        return sumExpected-sum;
    }
}
