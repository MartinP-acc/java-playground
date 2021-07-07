import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        // Write your code here.
        scan.close();
        s=s.trim();
        String[] tokens = s.split("[ !,?._'@]+");
        if (tokens.length>0 && !tokens[0].equals("")) {
            System.out.println(tokens.length);
            for (String st : tokens) System.out.println(st);
        }else System.out.println(0);
    }
}
