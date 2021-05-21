/*
* Marcin Paszkowski
* 21.05.2021
 */

public class FibSequence {

    //return number for n
    public static long printNrInSequence(long n){
        if (n<0){
            System.out.println("the number must be > 0");
            return 0;
        }
        if (n<2) return n;
        return printNrInSequence(n-1)+printNrInSequence(n-2);
    }

    //print full sequence for n - from n to 0
    public static void printSequence(int n){
        if (n>-1){
            System.out.println("n="+n+" : "+printNrInSequence(n));
            printSequence(n-1);
        }
    }

    //faster way but no recursion - no fun
    public static void fastSequence(int n){
        long[] f = new long[n+1];
        for (int i=0; i<f.length; i++){
            if (i<2) f[i]=i;
            else f[i]=f[i-1]+f[i-2];
            System.out.println("n="+i+" : "+f[i]);
        }
    }

    public static void main(String[] args) {

        //better type lower number or take a break and make coffee :)
        int myNumber = 45;

        System.out.println("Fibonacci number for n="+myNumber+" is "+printNrInSequence(myNumber));

        System.out.println("\nFull sequence for n="+myNumber);
        printSequence(myNumber);

        System.out.println("\nFull sequence for n="+myNumber+" (no recursion)");
        fastSequence(myNumber);
    }
}
