import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    private static Set<String> parenthesesList;

    private static void buildChain(int o, int c, int n, String chain){
        if (o<=n & c<=n){
            if (o<n) {
                String chain1 = chain;
                buildChain(o + 1, c, n, chain1 += "(");
            }
            if (o>c){
                String chain2=chain;
                buildChain(o,c+1,n,chain2+=")");
            }
            if (o==c && c==n) parenthesesList.add(chain);
        }
    }

    public static ArrayList<String> solution(int n){
        if (n==0) return new ArrayList<>(Arrays.asList(""));
        parenthesesList = new HashSet<>();
        buildChain(1,0,n,"(");
        return new ArrayList<>(parenthesesList);
    }
}
