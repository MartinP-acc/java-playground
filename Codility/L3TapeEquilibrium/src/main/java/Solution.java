import java.util.Arrays;

public class Solution {

    public int solution(int[] A) {

        int sum = Arrays.stream(A).sum();

        int firstSplit = 0;
        int secondSplit = 0;
        int diff;
        int result = Integer.MAX_VALUE;

        for (int p = 1; p < A.length; p++) {

            firstSplit += A[p - 1];
            secondSplit = sum - firstSplit;
            diff = Math.abs(firstSplit - secondSplit);
            result = Math.min(diff, result);
        }
        return result;
    }
}
