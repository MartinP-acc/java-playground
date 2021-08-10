public class Dinglemouse {
    public static int snakesAndLadders(final int[] board, final int[] dice) {
        int playerPos = 0;
        for (int roll : dice){
            int nextStep;
            if (playerPos+roll>=0 && playerPos+roll<board.length){
                nextStep = playerPos+roll+board[playerPos+roll];
                if (nextStep>=0 && nextStep<board.length) playerPos=nextStep;
            }
        }
        return playerPos;
    }
}