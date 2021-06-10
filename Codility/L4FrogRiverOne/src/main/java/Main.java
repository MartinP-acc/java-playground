public class Main {

    public static void main(String[] args) {

        int[] A = new int[]{1,3,1,4,2,3,5,4};
        int X = 5;
        Solution solution = new Solution();
        System.out.println(solution.solution(X,A));

        A = new int[]{1,1,1,1};
        X = 2;
        System.out.println(solution.solution(X,A));

    }
}
