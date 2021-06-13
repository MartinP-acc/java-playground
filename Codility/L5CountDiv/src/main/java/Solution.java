public class Solution {

    public int solution(int a, int b, int k){

        int result = (b/k)-(a/k);

        if (a%k==0) result++;

        return result;
    }
}
