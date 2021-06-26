import java.util.LinkedList;

public class Solution {

    public int solution(int[] h){

        if (h.length==0) return 0;

        LinkedList<Integer> heightStack = new LinkedList<>();
        int blockCounter=0;

        for (int i : h) {
            while (!heightStack.isEmpty() && heightStack.peek() > i) {
                heightStack.pop();
            }
            if (heightStack.isEmpty() || heightStack.peek() < i) {
                heightStack.push(i);
                blockCounter++;
            }
        }
        return blockCounter;
    }
}
