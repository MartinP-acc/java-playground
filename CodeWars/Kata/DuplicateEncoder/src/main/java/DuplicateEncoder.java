import java.util.HashMap;

public class DuplicateEncoder {

    private static HashMap<Character,Integer> charCounter;

    static String encode(String word){
        word = word.toLowerCase();
        countChars(word);
        return getEncoded(word);
    }

    private static String getEncoded(String word) {
        String result = "";
        for (int i=0; i<word.length(); i++){
            char currChar = word.charAt(i);
            if (charCounter.get(currChar)>1)
                result = result.concat(")");
            else
                result = result.concat("(");
        }
        return result;
    }

    private static void countChars(String word) {
        charCounter = new HashMap<>();
        for (int i=0; i<word.length(); i++){
            char currChar = word.charAt(i);
            charCounter.put(currChar,charCounter.getOrDefault(currChar,0)+1);
        }
    }
}