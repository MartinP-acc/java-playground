public class Dinglemouse {

    public static String histogram(final int[] results) {
        StringBuilder realHistogram = new StringBuilder();
        for (int diceSide=6, index=5; diceSide>0; diceSide--, index--){
            int timesRolled = results[index];
            realHistogram.append(diceSide).append("|").append("#".repeat(timesRolled));
            if (timesRolled > 0) realHistogram.append(" ").append(timesRolled);
            realHistogram.append("\n");
        }
        return realHistogram.toString();
    }
}
