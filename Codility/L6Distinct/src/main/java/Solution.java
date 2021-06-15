import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int solution(int[] a){

        Set<Integer> distNumbs = new HashSet<>();

        for (int n : a){
            distNumbs.add(n);
        }

        return distNumbs.size();
    }
}
