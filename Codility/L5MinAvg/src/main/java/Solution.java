public class Solution {

    public int solution(int[] a){

        float minAvg = Integer.MAX_VALUE;
        int minAvgIndex = 0;

        for (int i=0; i<a.length-1; i++){

            float avgOf2 = (float) (a[i] + a[i+1]) / 2;
            float avgOf3;
            if (i<a.length-2){
                avgOf3 = (float) (a[i] + a[i+1] + a[i+2]) / 3;
                avgOf2 = Math.min(avgOf2,avgOf3);
            }

            if (minAvg>avgOf2){
                minAvg=avgOf2;
                minAvgIndex = i;
            }
        }
        return minAvgIndex;
    }
}
