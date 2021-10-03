public class Kata {

    public static String columnize(String[] input, int numberOfColumns) {
        int[] colWidth = new int[numberOfColumns];
        int numOfInput = input.length;

        for (int i=0, col=0 ; i< numOfInput ; i++, col++){
            col = col==numberOfColumns ? 0 : col;
            colWidth[col] = Math.max(input[i].length(),colWidth[col]);
        }

        for (int i=0, col=0; i< numOfInput; i++, col++){
            int len = input[i].length();
            col = col==numberOfColumns ? 0 : col;
            String delimiter = col<numberOfColumns-1 ? " | " : "\n";
            input[i] = input[i]+" ".repeat(colWidth[col]-len);
            if (i< input.length-1) input[i] += delimiter;
        }

        return String.join("",input);
    }
}
