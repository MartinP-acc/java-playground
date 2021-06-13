import java.util.Set;
import java.util.TreeSet;

public class Solution {

    public int solution(int[] a){

        Set<Integer> setPosA = new TreeSet<>();
        for (int n : a){
            if (n>0) setPosA.add(n);
        }

        if (setPosA.isEmpty()) return 1;

        int min=1;
        for (Integer n : setPosA){
            if (n!=min) return min;
            min++;
        }

        return min;
    }
}
