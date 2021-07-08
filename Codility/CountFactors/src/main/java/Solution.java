public class Solution {

    public int solution(int n){

        int sqrtOfN = (int) Math.sqrt(n);
        int result = 0;

        for (int i=1; i<=sqrtOfN; i++){
            if (n%i==0){
                result++;
            }
        }
        result*=2;

        if (Math.pow(sqrtOfN,2)==n) result--;

        return result;
    }
}
