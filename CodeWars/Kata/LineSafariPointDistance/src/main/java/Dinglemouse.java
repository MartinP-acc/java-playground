public class Dinglemouse {

    private static double sideA;
    private static double sideB;
    private static double sideC;

    public static double distanceFromLine(final int[] a, final int[] b, final int[] c) {
        sideC = getDistance(a,c);
        sideB = getDistance(b,c);
        sideA = getDistance(a,b);
        if (sideA > 0.0) return getAreaOfTriangle()*2 / sideA;
        else return sideB;
    }

    private static double getDistance(int[] from, int[] to){
        return Math.sqrt(Math.pow(from[0] - to[0], 2) + Math.pow(from[1] - to[1], 2));
    }

    private static double getAreaOfTriangle() {
        return Math.sqrt( (sidesSum()/2) * (sidesSum()/2-sideA) * (sidesSum()/2-sideB) * (sidesSum()/2-sideC));
    }

    private static double sidesSum(){
        return sideA+sideB+sideC;
    }
}
