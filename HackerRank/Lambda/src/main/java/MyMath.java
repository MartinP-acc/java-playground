class MyMath {
    public static boolean checker(PerformOperation p, int num) {
        return p.check(num);
    }

    public PerformOperation isOdd(){
        return a -> a%2!=0;
    }

    public PerformOperation isPrime(){
        return a -> {
            for (int i=3; i<=a/2; i++){
                if (a%i==0) return false;
            }
            return true;
        };
    }

    public PerformOperation isPalindrome(){
        return a -> {
            String s = Integer.toString(a);
            StringBuilder s2 = new StringBuilder();
            for (int i=s.length()-1; i>=0;i--){
                s2.append(s.charAt(i));
            }
            return s.equals(s2.toString());
        };
    }
}