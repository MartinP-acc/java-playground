import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int elements = scanner.nextInt();
        scanner.nextLine();

        Map<String,String> phoneBook = new HashMap<>();
        for (int i=0; i<elements; i++){
            phoneBook.put(scanner.nextLine(),scanner.nextLine());
        }

        while (scanner.hasNextLine()){
            String person = scanner.nextLine();
            if (phoneBook.containsKey(person)) System.out.printf("%s=%s\n",person,phoneBook.get(person));
            else System.out.println("Not Found");
        }
        scanner.close();
    }
}
