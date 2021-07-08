public class Solution {

    public int solution(int n){
        int sqrt = (int) Math.sqrt(n);
        int result = 2 + 2 * n;
        for (int i=sqrt; i>0; i--){
            if (n%i==0){
                result = (i*2) + (n/i*2);
                break;
            }
        }
        return result;
    }
}
