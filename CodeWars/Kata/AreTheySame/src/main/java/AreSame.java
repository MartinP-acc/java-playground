import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AreSame {

    public static boolean comp(int[] a, int[] b) {
        if (a.length<1 || a.length!= b.length) return false;
        List<Integer> aList = Arrays.stream(a).map(o -> o*o).boxed().collect(Collectors.toList());
        List<Integer> bList = Arrays.stream(b).boxed().collect(Collectors.toList());
        bList.forEach(aList::remove);
        return aList.size()==0;
    }
}
