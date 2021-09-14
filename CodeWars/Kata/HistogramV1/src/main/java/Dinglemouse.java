import java.util.Arrays;
import java.util.OptionalInt;

public class Dinglemouse {

    private static final String BAR = "#";
    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";
    private static final String SPACES_WITH_NEW_LINE = "[ ]+(\n)";
    private static int[] drawResults;
    private static int cubeSidesNumber;
    private static StringBuilder graph;

    public static String histogram(final int[] results) {
        drawResults = results;
        cubeSidesNumber = drawResults.length;
        graph = new StringBuilder();
        drawMainRowsWithLabel();
        drawLine();
        drawFootLabel();
        return getFinalGraph();
    }

    private static void drawMainRowsWithLabel(){
        int maxDrawResult = findMaxResult();
        if (maxDrawResult < 1) return;
        for (int row=maxDrawResult; row>=0; row--){
            drawColumnParts(row);
            graph.append(NEW_LINE);
        }
    }

    private static int findMaxResult(){
        OptionalInt max = Arrays.stream(drawResults).max();
        if (max.isPresent()) return max.getAsInt();
        else return 0;
    }

    private static void drawColumnParts(int row) {
        for (int side = 0; side<cubeSidesNumber; side++){
            int result = drawResults[side];
            drawSingleCol(result , row);
        }
    }

    private static void drawSingleCol(int result, int row){
        if (result == row) drawColLabel(result);
        else if (result > row) graph.append(BAR).append(SPACE);
        else graph.append(SPACE).append(SPACE);
    }

    private static void drawColLabel(int result) {
        if (result > 0) graph.append(result);
        else graph.append(SPACE);
        if (result < 10) graph.append(SPACE);
    }

    private static void drawFootLabel() {
        for (int i=1; i<=cubeSidesNumber; i++){
            graph.append(i);
            if (i<cubeSidesNumber) graph.append(SPACE);
        }
        graph.append(NEW_LINE);
    }

    private static void drawLine() {
        graph.append("-".repeat( cubeSidesNumber*2 - 1)).append(NEW_LINE);
    }

    private static String getFinalGraph(){
        String result = graph.toString();
        result = result.replaceAll(SPACES_WITH_NEW_LINE, NEW_LINE);
        System.out.println("\n"+result+"\n");
        return result;
    }
}
