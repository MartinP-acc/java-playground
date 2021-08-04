public class Spiralizor {

    private static int[][] spiral;
    private static boolean firstLoop;

    private static int[][] drawRight(int size, int row, int col){
        for (int i=0; i<size; i++, col++){
            spiral[row][col]=1;
        }
        if (!firstLoop) size-=2;
        else firstLoop = false;
        if (size>1) drawDown(size,row,col-1);
        return spiral;
    }

    private static void drawDown(int size, int row, int col){
        for (int i=0; i<size; i++, row++){
            spiral[row][col]=1;
        }
        if (size>2) drawLeft(size,row-1,col);
    }

    private static void drawLeft(int size, int row, int col){
        for (int i=0; i<size; i++, col--){
            spiral[row][col]=1;
        }
        size-=2;
        if (size>1) drawUp(size,row,col+1);
    }

    private static void drawUp(int size, int row, int col){
        for (int i=0; i<size; i++, row--){
            spiral[row][col]=1;
        }
        if (size>2) drawRight(size,row+1,col);
    }

    public static int[][] spiralize(int size) {
        firstLoop=true;
        spiral = new int[size][size];
        return drawRight(size,0,0);
    }
}
