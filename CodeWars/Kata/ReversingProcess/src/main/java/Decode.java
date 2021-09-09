public class Decode {

    private static final String ERROR_MESSAGE = "Impossible to decode";
    private static final char MULTIPLE_RESULT = '#';
    private static String encodedString;
    private static int codingNumber;

    public static String decode(String r) {

        encodedString = r;
        codingNumber = getNumber();
        String encodedLetters = deleteNumber();
        StringBuilder decodedString = new StringBuilder();

        for (int i=0; i<encodedLetters.length(); i++){
            char decoded = calculate(encodedLetters.charAt(i));
            if (decoded==MULTIPLE_RESULT) return ERROR_MESSAGE;
            decodedString.append(decoded);
        }
        return decodedString.toString();
    }

    private static char calculate(char encoded){
        final int PRECEDING_CHARS = 97;
        final int ALPHABET_LENGTH = 26;
        final int INITIAL_STATE = 0;
        int indexInAlphabet = encoded - PRECEDING_CHARS;
        int decodedIndex = INITIAL_STATE;
        for (int i=0; i<ALPHABET_LENGTH; i++){
            if (i * codingNumber % ALPHABET_LENGTH == indexInAlphabet){
                if (decodedIndex!=INITIAL_STATE) return MULTIPLE_RESULT;
                decodedIndex=i;
            }
        }
        return (char) (decodedIndex + PRECEDING_CHARS);
    }

    private static int getNumber(){
        return Integer.parseInt(encodedString.replaceAll("[^0-9]+",""));
    }

    private static String deleteNumber(){
        return encodedString.replaceAll("[0-9]","");
    }
}
