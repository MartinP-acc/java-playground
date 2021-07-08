import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int l = scan.nextInt();
        scan.nextLine();

        String[] array = new String[l];
        for (int i=0; i<l; i++){
            String s = scan.nextLine();
            array[i] = s;
        }

        Arrays.sort(array, (o1, o2) -> new BigDecimal(o2).compareTo(new BigDecimal(o1)));

        for (String st : array) System.out.println(st);
    }
}
