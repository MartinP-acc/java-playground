import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutations {

    private static Set<String> permSet;

    private static void swap(String[] str, int a, int b){
        String temp = str[a];
        str[a] = str[b];
        str[b] = temp;
    }

    public static void generate(int len, String[] str){
        if (len==1) permSet.add(String.join("",str));
        else {
            for (int i=0; i<len; i++){
                swap(str,i,len-1);
                generate(len-1, str);
                swap(str,i,len-1);
            }
        }
    }

    public static List<String> singlePermutations(String s) {
        permSet = new HashSet<>();
        generate(s.length(),s.split(""));
        return new ArrayList<>(permSet);
    }
}
