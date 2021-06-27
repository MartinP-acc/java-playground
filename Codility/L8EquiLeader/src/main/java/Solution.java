import java.util.HashMap;

public class Solution {

    public int solution(int[] a){

        if (a.length==0) return 0;

        HashMap<Integer,Integer> leaderBoard = new HashMap<>();
        int leader=a[0];
        int valuesLeft=0;
        int countValues=0;
        int equiLeaders=0;

        for (int j : a) {
            leaderBoard.put(j, leaderBoard.getOrDefault(j, 0) + 1);
        }

        for (HashMap.Entry<Integer, Integer> pair : leaderBoard.entrySet()) {
            if (pair.getValue()>a.length/2) {
                leader = pair.getKey();
                for (int k : a){
                    if (k==leader) valuesLeft++;
                }
            }
        }

        for (int i = 0 ; i<a.length ; i++){
            if (a[i]==leader) {
                countValues++;
                valuesLeft--;
            }
            if (countValues>(i+1)/2 && valuesLeft>(a.length-(i+1))/2){
                equiLeaders++;
            }
        }

        return equiLeaders;
    }
}
