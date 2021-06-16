public class Solution {

    public int solution(int[] a){

        int p = -1000;
        int q = 0;
        int r = 0;

        int qMinus = 0;
        int rMinus = 0;

        for (int n=0; n<a.length; n++){

            if(p<=a[n]){
                r=q;
                q=p;
                p=a[n];
            }else if (q<=a[n]){
                r=q;
                q=a[n];
            }else if (r<a[n]){
                r=a[n];
            }

            if (qMinus>=a[n]){
                rMinus=qMinus;
                qMinus=a[n];
            } else if (rMinus>a[n]){
                rMinus=a[n];
            }
        }

        if (p<0) return p*q*r;

        return Math.max(q*r,qMinus*rMinus)*p;
    }
}
