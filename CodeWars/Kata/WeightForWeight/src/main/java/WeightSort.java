import java.util.Arrays;
import java.util.stream.Collectors;

public class WeightSort {

    public static String orderWeight(String strng) {
        if (strng.isEmpty()) return "";
        return  Arrays.stream(strng.trim().replaceAll("\\s+"," ").split(" "))
                .sorted((o1, o2) -> {
                    int a1 = Arrays.stream(o1.split("")).mapToInt(Integer::parseInt).sum();
                    int a2 = Arrays.stream(o2.split("")).mapToInt(Integer::parseInt).sum();
                    if (a1==a2) return o1.compareTo(o2);
                    return a1 - a2;
                }).collect(Collectors.joining(" "));
    }
}
