import java.util.LinkedList;

public class Solution {

    public int solution(String s){

        if (s.length()==0) return 1;
        if (s.length()%2>0) return 0;

        LinkedList<Character> queue = new LinkedList<>();

        for (int i=0; i<s.length(); i++){
            switch (s.charAt(i)){
                case '(' : queue.push(')');
                break;
                case '[' : queue.push(']');
                break;
                case '{' : queue.push('}');
                break;
                default:
                    if (queue.isEmpty()) return 0;
                    else {
                        char popChar = queue.pop();
                        if (popChar!=s.charAt(i)) return 0;
                    }
            }
        }
        return queue.isEmpty()? 1 : 0;
    }
}
