import java.util.*;

public class Dinglemouse {

    public static String makePassword(int len, boolean flagUpper, boolean flagLower, boolean flagDigit) {
        List<Character> upperChars = initList(flagUpper, 65, 90);
        List<Character> lowerChars = initList(flagLower, 97, 122);
        List<Character> digits = initList(flagDigit, 48, 57);
        StringBuilder password = new StringBuilder();
        while (password.length()<len){
            if (flagUpper && upperChars.size()>0) password.append(randomFrom(upperChars));
            if (flagLower && lowerChars.size()>0) password.append(randomFrom(lowerChars));
            if (flagDigit && digits.size()>0) password.append(randomFrom(digits));
        }
        return password.substring(0,len);
    }

    private static List<Character> initList(boolean flag, int start, int end){
        if (flag) {
            List<Character> list = new ArrayList<>();
            for (int i=start; i<=end; i++){
                list.add((char) i);
            }
            return list;
        }
        return null;
    }

    private static char randomFrom(List<Character> list){
        int randomIndex = new Random().nextInt(list.size());
        return list.remove(randomIndex);
    }

}
