/*
* Marcin Paszkowski
* 21.05.2021
 */

public class FibSequence {

    //returns number for n
    public static long printNrInSequence(long n){
        if (n<2) return n;
        return printNrInSequence(n-1)+printNrInSequence(n-2);
    }

    //prints full sequence for n from n to 0
    public static void printSequence(int n){
        if (n>-1){
            System.out.println("n="+n+" : "+printNrInSequence(n));
            printSequence(n-1);
        }
    }

    public static void main(String[] args) {

        //better type lower number or take a break and make coffee :)
        int myNumber = 50;

        System.out.println("Fibonacci number for n="+myNumber+" is "+printNrInSequence(myNumber));

        System.out.println("\nFull sequence for n="+myNumber);
        printSequence(myNumber);
    }
}
