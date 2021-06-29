public class Solution {

    public static boolean validParentheses(String parens)
    {
        int counter = 0;
        for (int i=0; i<parens.length(); i++){
            if (parens.charAt(i)=='(') counter++;
            else if (parens.charAt(i)==')') counter--;
            if (counter<0) return false;
        }
        return counter==0;
    }
}
