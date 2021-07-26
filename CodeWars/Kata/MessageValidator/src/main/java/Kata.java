import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Kata {

    public static boolean isAValidMessage(String message) {
        String[] split = message.replaceAll("([a-zA-Z])([0-9])","$1,$2").split(",");
        for (String s : split) {
            Matcher num = Pattern.compile("([0-9]+)").matcher(s);
            if (num.find()) if (Integer.parseInt(num.group()) + num.group().length() != s.length()) return false;
        }
        return true;
    }
}