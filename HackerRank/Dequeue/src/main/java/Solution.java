import java.util.*;

public class Solution {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        int subSize = scanner.nextInt();

        int maxUnique = 0;
        Deque<Integer> deque = new LinkedList<>();
        Set<Integer> hs = new HashSet<>();
        for (int i=0; i<size; i++){
            deque.add(scanner.nextInt());
            hs.add(deque.peekLast());
            if (deque.size()==subSize+1){
                Integer first = deque.pollFirst();
                if (!deque.contains(first)) hs.remove(first);
                maxUnique = Math.max(maxUnique, hs.size());
            }
        }
        System.out.println(maxUnique);
    }
}
