import java.util.LinkedList;

public class Solution {

    public int solution(int[] a, int[] b){

        int upstreamCounter=0;
        LinkedList<Integer> downstream = new LinkedList<>();

        for (int i=0; i<a.length; i++){
            if (b[i]==1){
                downstream.push(a[i]);
            } else {
                while (!downstream.isEmpty() && downstream.peek()<a[i]){
                    downstream.pop();
                }
                if (downstream.isEmpty()) upstreamCounter++;
            }
        }

        return upstreamCounter + downstream.size();
    }
}
