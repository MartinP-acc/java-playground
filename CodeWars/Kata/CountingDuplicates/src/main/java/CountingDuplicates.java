import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CountingDuplicates {

    public static int duplicateCount(String text) {

        String[] sortedString = text.toLowerCase().split("");
        Arrays.sort(sortedString);

        Set<String> duplicated = new HashSet<>();
        int dist = 0;

        for (int i=1; i<sortedString.length ; i++){
            if (!sortedString[dist].equals(sortedString[i])) dist=i;
            else duplicated.add(sortedString[i]);
        }
        return duplicated.size();
    }
}
