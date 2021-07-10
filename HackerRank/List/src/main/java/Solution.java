import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        scanner.nextLine();
        List<Integer> integers = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());

        int taskNum = scanner.nextInt();
        scanner.nextLine();

        for (int i=0; i<taskNum; i++){
            String task = scanner.nextLine();
            int[] pos = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            try {
                switch (task){
                    case "Insert" -> integers.add(pos[0],pos[1]);
                    case "Delete" -> integers.remove(pos[0]);
                }
            }catch (IndexOutOfBoundsException ex){
                System.out.println("ERROR!");
            }
        }
        integers.forEach(integer -> System.out.print(integer+" "));
        scanner.close();
    }
}
