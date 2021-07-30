import java.util.LinkedList;

public class BackspacesInString {

    public String cleanString(String s) {

        LinkedList<String> charLeft = new LinkedList<>();
        for (int i=0; i<s.length(); i++){
            if (s.charAt(i)=='#'){
                if (!charLeft.isEmpty()) charLeft.pollLast();
            } else {
                charLeft.addLast(Character.toString(s.charAt(i)));
            }
        }
        return String.join("", charLeft);
    }
}
