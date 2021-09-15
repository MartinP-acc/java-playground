import java.util.Arrays;
import java.util.OptionalInt;

public class Dinglemouse {

    private static final String BAR = "██ ";
    private static final String EMPTY = "   ";
    private static final String FOOT = "------------------\n 1  2  3  4  5  6\n";
    private static final String TRAILING_SPACES = "[ ]+\n";
    private static final int MAX_HIGH = 15;
    private static int[] rollResults;
    private static int[] percentResults;
    private static int[] barHigh;
    private static StringBuilder graph;

    public static String histogram(final int[] results) {
        try {
            initVariables(results);
            makeGraph();
        }catch (ArithmeticException e){
        }
        graph.append(FOOT);
        return graph.toString().replaceAll(TRAILING_SPACES,"\n");
    }

    private static void initVariables(int[] results) throws ArithmeticException{
        graph = new StringBuilder();
        int resultsSum = Arrays.stream(results).sum();
        percentResults = Arrays.stream(results).map( r -> r*100/resultsSum).toArray();
        barHigh = Arrays.stream(results).map( r -> r*MAX_HIGH/resultsSum).toArray();
        rollResults = results;
    }

    private static void makeGraph() {
        int highestBar = getHighest();
        for (int row=highestBar; row>=0; row--){
            addRow(row);
            graph.append("\n");
        }
    }

    private static void addRow(int row){
        for (int side=0; side<6; side++){
            if (barHigh[side]==row && rollResults[side]!=0) graph.append(label(side));
            else if (barHigh[side]>row) graph.append(BAR);
            else graph.append(EMPTY);
        }
    }

    private static String label(int side) {
        if (percentResults[side]==0) return "<1%";
        if (percentResults[side]==100) return "100%";
        return (percentResults[side]+"% ").substring(0,3);
    }

    private static int getHighest(){
        OptionalInt max = Arrays.stream(barHigh).max();
        return max.isPresent() ? max.getAsInt() : 0;
    }
}
