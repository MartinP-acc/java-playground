import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    public static String camelCase(String input) {
        Matcher matcher = Pattern.compile("([a-z])([A-Z])").matcher(input);
        while (matcher.find()){ input = input.replaceFirst(matcher.group(), matcher.group(1)+" "+matcher.group(2));}
        return input;
    }
}