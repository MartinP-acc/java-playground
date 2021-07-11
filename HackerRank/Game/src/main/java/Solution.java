import java.util.*;

public class Solution {

    private static boolean checkWay(int leap, int[] game, int pos){
        if (pos<0 || (pos<game.length && game[pos]==1)) return false;
        if (pos>= game.length || (pos== game.length-1 && game[pos]==0)) return true;

        game[pos]=1;

        return checkWay(leap, game, pos+1) || checkWay(leap, game, pos+leap) || checkWay(leap, game, pos-1);
    }

    public static boolean canWin(int leap, int[] game) {

        return checkWay(leap, game, 0);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int q = scan.nextInt();
        while (q-- > 0) {
            int n = scan.nextInt();
            int leap = scan.nextInt();

            int[] game = new int[n];
            for (int i = 0; i < n; i++) {
                game[i] = scan.nextInt();
            }

            System.out.println( (canWin(leap, game)) ? "YES" : "NO" );
        }
        scan.close();
    }
}
