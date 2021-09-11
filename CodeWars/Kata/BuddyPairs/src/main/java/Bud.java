public class Bud {

    public static String buddy(long start, long limit) {
        for (long current=start; current<=limit; current++){
            long candidate = findAndSumDivisorsOf(current);
            long candidateDivSum = findAndSumDivisorsOf(candidate);
            if (current == candidateDivSum && current<candidate) return "("+current+" "+candidate+")";
        }
        return "Nothing";
    }

    private static long findAndSumDivisorsOf(long num){
        long sum = 0;
        long sqrtOfNum = (long) Math.sqrt(num);
        for (long i=2; i<=sqrtOfNum; i++){
            if (num%i==0) sum += i + num/i;
        }
        return sum;
    }
}
