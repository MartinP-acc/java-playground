import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class SumParts {

    public static int[] sumParts(int[] ls) {
        int sum= Arrays.stream(ls).sum();
        List<Integer> sumStack = new ArrayList<>();
        sumStack.add(sum);
        for (int n : ls){
            sum-=n;
            sumStack.add(sum);
        }
        return sumStack.stream().flatMapToInt(IntStream::of).toArray();
    }
}