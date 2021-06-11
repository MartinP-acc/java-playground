public class Main {

    public static void main(String[] args) {

        Solution code = new Solution();
        // example test
        int[] a = new int[]{3,4,4,6,1,4,4};
        int n = 5;

        for (int i : code.solution(n,a)) System.out.print(i+",");
    }
}
