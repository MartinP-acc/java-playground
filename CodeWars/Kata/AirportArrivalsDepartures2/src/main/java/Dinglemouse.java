public class Dinglemouse {

    private final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ?!@#&()|<>.:=-+*/0123456789";

    public static int[][] flapRotors(final String[] linesBefore, final String[] linesAfter) {
        int[][] rotors = new int[linesBefore.length][linesBefore[0].length()];
        for (int line = 0; line<rotors.length; line++){
            int rotation = 0;
            for (int c = 0; c<rotors[line].length; c++){
                int b = (ALPHABET.indexOf(linesBefore[line].charAt(c))+rotation) % ALPHABET.length();
                int a = ALPHABET.indexOf(linesAfter[line].charAt(c));
                int currRot = b <= a ? a - b : a + (ALPHABET.length()-b);
                rotors[line][c] = currRot;
                rotation+=currRot;
            }
        }
        return rotors;
    }
}