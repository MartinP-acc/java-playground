import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

    static String toCamelCase(String s){

        StringBuilder camelCase = new StringBuilder();

        Matcher mat = Pattern.compile("\\p{Alpha}+").matcher(s);
        if (mat.find())  camelCase.append(mat.group());

        mat = Pattern.compile("[^a-zA-Z](\\p{Alpha})(\\p{Alpha}+)").matcher(s);
        while (mat.find()){
            camelCase.append(mat.group(1).toUpperCase()).append(mat.group(2));
        }

        return camelCase.toString();
    }
}
