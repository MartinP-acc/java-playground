import java.util.*;

public class Solution {

    public static boolean canWin(int leap, int[] game) {

        int playerIndex=0;
        int block=0;
        boolean tryJump = false;

        while (playerIndex<game.length-1) {
            if (!tryJump){
                if (game[playerIndex+1]==0){
                    playerIndex+=1;
                }else {
                    tryJump=true;
                    block=playerIndex;
                }
            }else {
                if (playerIndex+leap>game.length-1 || game[playerIndex+leap]==0){
                    playerIndex+=leap;
                    tryJump=false;
                    if (playerIndex==block) return false;
                } else if (playerIndex-1>=0 && game[playerIndex-1]==0){
                    playerIndex-=1;
                } else return false;
            }
        }
        return true;
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
