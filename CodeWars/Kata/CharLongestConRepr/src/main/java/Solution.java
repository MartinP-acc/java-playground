public class Solution {

    public static Object[] longestRepetition(String s) {

        if (s.length()==0) return new Object[]{"",0};

        char current = s.charAt(0);
        char winner = 0;
        int counter = 0;
        int longest = 0;

        for (int i = 0 ; i < s.length(); i++){

            if (current==s.charAt(i)) counter++;
            else {
                counter=1;
                current=s.charAt(i);
            }

            if (counter>longest){
                longest=counter;
                winner=current;
            }
        }
        return new Object[]{winner+"",longest};
    }
}
