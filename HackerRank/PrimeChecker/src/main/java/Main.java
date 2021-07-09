public class Main {

    public static void main(String[] args) {

        Prime ob = new Prime();

        int n1 = 2;
        int n2 = 9;
        int n3 = 45;
        int n4 = 6;
        int n5 = 7;


        ob.checkPrime(n1);
        ob.checkPrime(n1,n2);
        ob.checkPrime(n1,n2,n3);
        ob.checkPrime(n1,n2,n3,n4,n5);
    }
}
