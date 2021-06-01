public class Solution {

    public int solution(int X, int Y, int D){

        if (X>=Y) return 0;

        int rest = (Y-X)%D;

        int jumps = (Y-X-rest)/D;
        return jumps*D+X >= Y ? jumps : jumps+1;
    }
}
