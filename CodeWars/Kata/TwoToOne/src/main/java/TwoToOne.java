import java.util.Arrays;
import java.util.HashSet;

public class TwoToOne {

    public static String longest (String s1, String s2) {

        String[] allChars = (s1+s2).split("");
        Arrays.sort(allChars);

        HashSet<String> distChars = new HashSet<>(Arrays.asList(allChars));

        return distChars.stream().reduce("",(String::concat));
    }
}
