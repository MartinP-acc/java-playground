public class SquareDigit {

    public int squareDigits(int n) {

        String sn = ""+n;
        StringBuilder squareN= new StringBuilder();

        for (int i=0; i<sn.length(); i++){
            int d = Integer.parseInt(sn.substring(i,i+1));
            squareN.append(d*d);
        }
        return Integer.parseInt(squareN.toString());
    }
}