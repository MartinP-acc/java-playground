public class Solution {

    public int solution(int N){

        int gap = 0;
        int longestGap = 0;
        boolean count = false;

        while (N>0){
            if (N%2==0){
                if (count) gap++;
            }
            else{
                longestGap = Math.max(longestGap,gap);
                gap=0;
                count=true;
            }
            N=N/2;
        }
        return longestGap;
    }
}
