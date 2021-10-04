public class DRoot {

    public static int digital_root(int n) {
        int sumOfDigits = n%10;
        do {
            n /= 10;
            sumOfDigits += n%10;
        }while (n>9);
        if (sumOfDigits>9) return digital_root(sumOfDigits);
        return sumOfDigits;
    }
}
