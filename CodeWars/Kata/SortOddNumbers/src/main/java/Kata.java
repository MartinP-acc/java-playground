import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Kata {
    public static int[] sortArray(int[] array) {

        List<Integer> odd = Arrays.stream(array)
                .filter(value -> value % 2 != 0)
                .boxed().sorted(Comparator.comparingInt(o -> o))
                .collect(Collectors.toList());

        int oddIndex=0;
        for (int i=0; i< array.length; i++){
            if (array[i]%2!=0){
                array[i]=odd.get(oddIndex);
                oddIndex++;
            }
        }
        return array;
    }
}