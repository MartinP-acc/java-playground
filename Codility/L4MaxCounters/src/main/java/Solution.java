import java.util.Arrays;

public class Solution {

    public int[] solution(int n, int[] a){

        int[] result = new int[n];
        Arrays.fill(result,0);
        int minCounter = 0;
        int maxCounter = 0;

        for (int k=0; k<a.length; k++){

            if (a[k]<=n){

                result[a[k]-1]=Math.max(result[a[k]-1],minCounter);

                result[a[k]-1]+=1;

                maxCounter = Math.max(maxCounter,result[a[k]-1]);

            }else{
                minCounter=maxCounter;
            }
        }

        for (int i=0; i<n; i++){
            result[i]=Math.max(result[i],minCounter);
        }

        return result;
    }
}
