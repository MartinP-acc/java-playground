import java.util.HashMap;

public class Solution {

    public int solution(int[] a){

        if (a.length==0) return -1;

        HashMap<Integer,Integer> leaderBoard = new HashMap<>();

        for (int j : a) {
            leaderBoard.put(j, leaderBoard.getOrDefault(j, 0) + 1);
        }

        for (HashMap.Entry<Integer, Integer> pair : leaderBoard.entrySet()) {
            if (pair.getValue()>a.length/2) {
                for (int i=0; i<(a.length); i++){
                    if (a[i]==pair.getKey()) return i;
                }
            }
        }

        return -1;
    }
}
