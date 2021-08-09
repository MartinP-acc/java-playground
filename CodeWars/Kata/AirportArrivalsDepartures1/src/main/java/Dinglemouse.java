public class Dinglemouse {

    private static final String ALPHABET = Preloaded.ALPHABET;

    public static String[] flapDisplay(final String[] lines, final int[][] rotors) {

        for (int i=0; i<lines.length; i++){
            StringBuilder line = new StringBuilder(lines[i]);
            int rotate = 0;
            for (int c=0; c<line.length(); c++){
                char current = line.charAt(c);
                rotate+=rotors[i][c];
                int index = (ALPHABET.indexOf(current)+rotate)%54;
                line.setCharAt(c,ALPHABET.charAt(index));
            }
            lines[i]=line.toString();
        }
        return lines;
    }
}
