import java.util.Arrays;
import java.util.HashSet;

public class PangramChecker {

    public boolean check(String sentence){

        return new HashSet<>(Arrays.asList(sentence.toLowerCase().split(""))) //add to set distinct chars
                .stream()
                .filter(c -> c.matches("[a-z]")) //filter to remove all chars like .,!? etc.
                .count()==26;   //if set size is equal to number of letters in alphabet set true.
    }
}                //sorry for my English if something wrong.
