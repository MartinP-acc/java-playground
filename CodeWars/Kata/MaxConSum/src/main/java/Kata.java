public class Kata {

    public static int maxContiguousSum(final int[] arr) {

        int posSum = 0;
        int negSum = 0;
        int maxSum = 0;

        for (int n : arr){
            if (n>=0){
                if (negSum<0){
                    if (posSum+negSum>0){
                        posSum+=negSum;
                    }else {
                        posSum=0;
                    }
                    negSum=0;
                }
                posSum+=n;
            }else {
                negSum+=n;
            }
            maxSum=Math.max(posSum,maxSum);
        }
        return maxSum;
    }
}
