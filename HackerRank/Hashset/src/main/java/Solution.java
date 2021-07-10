import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lines = scanner.nextInt();
        scanner.nextLine();

        Set<String> set = new HashSet<>();
        for (int i=0; i<lines; i++){
            set.add(scanner.nextLine());
            System.out.println(set.size());
        }
    }
}
