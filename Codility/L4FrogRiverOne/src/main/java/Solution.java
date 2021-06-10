//Not ideal but better than nothing
public class Solution {

    public int solution(int X, int[] A){
        if (X>A.length) return -1;

        int result = -1;
        while(X>0){
            int step = X;
            for (int K=0; K<A.length;K++){
                if (X==A[K]){
                    result = Math.max(result,K);
                    X--;
                    break;
                }
            }
            if (step==X) return -1;
        }
        return result;
    }
}
