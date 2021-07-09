import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Arraylist {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int rowsNum = scanner.nextInt();
        scanner.nextLine();

        List<List<Integer>> rows = new ArrayList<>();
        for (int i=0; i<rowsNum; i++){
            rows.add(Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList()));
            rows.get(i).remove(0);
        }

        int numQ = scanner.nextInt();
        scanner.nextLine();

        for (int i=0; i<numQ; i++){
            String[] pos = scanner.nextLine().split(" ");
            try {
                System.out.println(rows.get(Integer.parseInt(pos[0])-1).get(Integer.parseInt(pos[1])-1));
            }catch (IndexOutOfBoundsException ex){
                System.out.println("ERROR!");
            }
        }
        scanner.close();
    }
}
