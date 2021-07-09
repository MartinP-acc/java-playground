import java.util.Arrays;
import java.util.Scanner;

public class Subarray {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int length = scanner.nextInt();
        scanner.nextLine();

        int[] array = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int countNegative=0;
        for (int i=0; i<length; i++){
            int sum=0;
            for (int j=i; j<length; j++){
                sum+=array[j];
                if (sum<0) countNegative++;
            }
        }

        System.out.println(countNegative);
    }
}
