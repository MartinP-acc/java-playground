public class Solution {

    public int solution(int[] a){

        int maxActual=0;
        int maxSliceSum=0;
        int maxNegativeOrZero=Integer.MIN_VALUE;

        for (int n : a){
            maxActual = Math.max(0,maxActual+n);
            maxSliceSum = Math.max(maxActual,maxSliceSum);

            if (n<1) maxNegativeOrZero = Math.max(maxNegativeOrZero,n);
        }

        if (maxSliceSum==0) maxSliceSum=maxNegativeOrZero;

        return maxSliceSum;
    }
}
