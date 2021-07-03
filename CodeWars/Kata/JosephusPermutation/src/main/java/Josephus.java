import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Josephus {

    public static <T> List<T> josephusPermutation(final List<T> items, final int k) {
        LinkedList<T> queue = new LinkedList<>(items);
        List<T> result = new ArrayList<>();

        while (!queue.isEmpty()){
            for (int i=1; i<k; i++){
                queue.addLast(queue.poll());
            }
            result.add(queue.poll());
        }
        return result;
    }
}
