import java.util.BitSet;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int op = scanner.nextInt();
        scanner.nextLine();
        BitSet a = new BitSet(n);
        BitSet b = new BitSet(n);

        for (int i=0; i<op; i++){
            String[] operation = scanner.nextLine().split(" ");
            int bs = Integer.parseInt(operation[1]);
            int val = Integer.parseInt(operation[2]);
            switch (operation[0]){
                case "AND" -> {
                    if (bs==1) a.and(b);
                    else b.and(a);
                }
                case "XOR" -> {
                    if (bs==1) a.xor(b);
                    else b.xor(a);
                }
                case "OR" -> {
                    if (bs==1) a.or(b);
                    else b.or(a);
                }
                case "FLIP" -> {
                    if (bs==1) a.flip(val);
                    else b.flip(val);
                }
                case "SET" -> {
                    if (bs==1) a.set(val);
                    else b.set(val);
                }
            }
            System.out.printf("%d %d\n",a.cardinality(),b.cardinality());
        }
    }
}
