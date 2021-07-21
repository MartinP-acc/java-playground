import java.util.HashMap;
import java.util.Map;

public class FindOdd {

    public static int findIt(int[] a) {
        int result = 0;
        HashMap<Integer, Integer> aMap = new HashMap<>();
        for (int n : a){
            aMap.put(n, aMap.getOrDefault(n,0)+1);
        }
        for (Map.Entry<Integer, Integer> m : aMap.entrySet()){
            if (m.getValue()%2!=0) result=m.getKey();
        }
        return result;
    }
}
