public class LongestConsec {

    public static String longestConsec(String[] strarr, int k) {
        if (k <= 0 || k > strarr.length - 1) return "";
        String longest = "";
        for (int i=0; i< strarr.length-k+1; i++){
            StringBuilder tempConcat = new StringBuilder();
            for (int j=i; j<i+k ; j++){
                tempConcat.append(strarr[j]);
            }
            if (tempConcat.length() > longest.length()) longest=tempConcat.toString();
        }
        return longest;
    }
}
