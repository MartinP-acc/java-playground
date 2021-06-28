public class NumberFun {

    public static long findNextSquare(long sq) {
        double sqrt = Math.sqrt((double) sq);
        if (sqrt-(long)sqrt==0) return (long) Math.pow(sqrt+1,2);
        return -1;
    }
}
