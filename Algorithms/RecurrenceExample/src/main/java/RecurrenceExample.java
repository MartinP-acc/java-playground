import java.util.Arrays;

public class RecurrenceExample {

    public static int sum(int[] array){
        if (array.length==0) return 0;
        return array[0] + sum(Arrays.copyOfRange(array,1,array.length));
    }
}
