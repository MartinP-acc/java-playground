public class Kata {

    public static String high(String s) {

        String[] words = s.split(" ");
        String winner = "";
        int highestScore = 0;

        for (String word : words){
            int wordScore=0;
            for (int i=0; i<word.length(); i++){
                wordScore+=word.charAt(i) - 96;
            }
            if (wordScore>highestScore){
                highestScore=wordScore;
                winner=word;
            }
        }
        return winner;
    }
}
