import java.util.Arrays;

public class Dinglemouse {

    private static int sumOfRolls;

    public static String histogram(final int[] results) {
        StringBuilder graph = new StringBuilder();
        setSumOfRolls(results);
        for (int diceSide=6, index=5; diceSide>0; diceSide--, index--){
            int percentRolled = calculatePercentRolled(results[index]);
            graph.append(drawRow(diceSide,percentRolled));
        }
        return graph.toString();
    }

    private static void setSumOfRolls(int[] results){
        sumOfRolls = Arrays.stream(results).sum();
    }

    private static int calculatePercentRolled(int rolled){
        double percent = (double) rolled / sumOfRolls * 100;
        return (int) percent;
    }

    private static String drawRow(int side, int percent){
        return side+"|"+"█".repeat(percent/2)+printPercent(percent)+"\n";
    }

    private static String printPercent(int percent){
        return percent > 0 ? " "+percent+"%" : "";
    }
}
