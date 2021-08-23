public class TimeFormatter {

    public static String formatDuration(int seconds) {

        if (seconds==0) return "now";

        String[] fName  = new String[]{" year"   , " day", " hour", " minute", " second"};
        int[]    div    = new int[]   {31_536_000, 86_400, 3_600  , 60       , 1        };

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 5; i++){
            if (seconds >= div[i]){
                int temp = seconds / div[i];

                if (result.length()>0) result.append(", ");
                result.append(temp).append(fName[i]);
                if (temp > 1) result.append("s");

                seconds %= div[i];
            }
        }

        int coma = result.lastIndexOf(",");
        if (coma>0) result.replace(coma, coma+1, " and");

        return result.toString();
    }
}
