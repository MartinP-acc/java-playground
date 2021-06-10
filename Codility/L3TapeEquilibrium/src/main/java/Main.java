public class Main {

    public static void main(String[] args) {

        int[] A = new int[]{3,1,2,4,3};
        for (int n : A) System.out.print(n+", ");
        boolean test = new Solution().solution(A)==1;
        System.out.println(" result : "+new Solution().solution(A)+" - "+test+"\n");

        A = new int[] {-1000,1000};
        for (int n : A) System.out.print(n+", ");
        test = new Solution().solution(A)==2000;
        System.out.println(" result : "+new Solution().solution(A)+" - "+test+"\n");

        A = new int[] {-2,-3,-1,-4};
        for (int n : A) System.out.print(n+", ");
        test = new Solution().solution(A)==0;
        System.out.println(" result : "+new Solution().solution(A)+" - "+test+"\n");


    }
}
