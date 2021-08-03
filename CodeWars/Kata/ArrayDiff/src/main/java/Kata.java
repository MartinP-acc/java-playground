import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Kata {
    public static int[] arrayDiff(int[] a, int[] b) {
        List<Integer> bList = Arrays.stream(b).boxed().collect(Collectors.toList());
        List<Integer> diff = new ArrayList<>();
        for (int j : a) if (!bList.contains(j)) diff.add(j);
        return diff.stream().flatMapToInt(IntStream::of).toArray();
    }
}
