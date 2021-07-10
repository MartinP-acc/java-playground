import java.util.Scanner;
import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String input=sc.next();
            Stack<Character> chars = new Stack<>();
            boolean isBalanced = true;
            for (int i=0; i<input.length(); i++){
                switch (input.charAt(i)){
                    case '[' -> chars.push(']');
                    case '{' -> chars.push('}');
                    case '(' -> chars.push(')');
                    default -> {
                        if (!chars.empty() && chars.peek()==input.charAt(i)){
                            chars.pop();
                        }else{
                            isBalanced=false;
                        }
                    }
                }
            }
            if (!chars.isEmpty()) isBalanced=false;
            System.out.println(isBalanced);
        }
    }
}
