public class Solution {

    public int solution(int[] a){

        int lastProfit;
        int maxProfit=0;
        int p=0;

        if (a.length<2) return 0;

        for (int q=1; q<a.length; q++){

            lastProfit = Math.max(0,a[q]-a[p]);
            maxProfit = Math.max(lastProfit,maxProfit);

            if (a[q]<a[p]) p=q;
        }

        return maxProfit;
    }
}
