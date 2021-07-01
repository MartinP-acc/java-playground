import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Scramblies {

    public static boolean scramble(String str1, String str2) {

        List<String> splitStr1 = Arrays.stream(str1.split("")).collect(Collectors.toList());
        for (int i=0; i<str2.length(); i++){
            String c = str2.charAt(i)+"";
            if (!splitStr1.contains(c)) return false;
            else {
                splitStr1.remove(c);
            }
        }
        return true;
    }
}
