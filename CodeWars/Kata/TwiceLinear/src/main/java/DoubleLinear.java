import java.util.ArrayList;

class DoubleLinear {

    public static int dblLinear (int n) {
        int xIndex=0;
        int zIndex=0;

        ArrayList<Integer> u = new ArrayList<>();
        u.add(1);

        for (int i=0; i<n ; i++){
            int x = 2 * u.get(xIndex) + 1;
            int z = 3 * u.get(zIndex) + 1;

            if (x<=z){
                u.add(x);
                xIndex++;
                if (x==z){
                    zIndex++;
                }
            }else {
                u.add(z);
                zIndex++;
            }
        }
        return u.get(n);
    }
}