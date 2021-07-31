import java.util.Arrays;

class Thirteen {

    public static long thirt(long current) {
        long last = 0;
        while(current!=last){
            last = current;
            current=0;
            long[] digits = Arrays.stream(String.valueOf(last).split("")).mapToLong(Long::parseLong).toArray();
            for (int i=digits.length-1, p=0; i>=0; i--, p++){
                current+= digits[i] * (Math.pow(10,p) % 13);
            }
        }
        return last;
    }
}