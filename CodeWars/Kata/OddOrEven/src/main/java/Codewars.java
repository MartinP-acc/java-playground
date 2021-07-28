import java.util.Arrays;

public class Codewars {
    public static String oddOrEven (int[] array) {
        return Arrays.stream(array).sum()%2==0 ? "even" : "odd";
    }
}