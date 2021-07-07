import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        String A=sc.next();
        String B=sc.next();
        /* Enter your code here. Print output to STDOUT. */

        String a1 = A.substring(0,1).toUpperCase();
        String b1 = B.substring(0,1).toUpperCase();

        
        String[] sort = new String[]{A,B};
        Arrays.sort(sort);

        System.out.println((A.length()+B.length()));
        System.out.println(sort[0].equals(A)?"No":"Yes");
        System.out.println(a1+A.substring(1)+" "+b1+B.substring(1));
    }
}
