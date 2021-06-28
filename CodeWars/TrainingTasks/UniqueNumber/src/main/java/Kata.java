public class Kata {

    public static double findUniq(double arr[]) {

        double maybeUnique=arr[0];
        for (int i=1; i<arr.length; i++){
            if (maybeUnique!=arr[0]){
                if (arr[i]!=maybeUnique) return maybeUnique;
                else return arr[0];
            }
            if (arr[i]!=arr[0]) maybeUnique=arr[i];
        }
        return maybeUnique;
    }
}

