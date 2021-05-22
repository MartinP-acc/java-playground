import java.util.Random;

public class Minesweeper {

    static GameGUI gameGUI;
    static Random random = new Random();
    static final int MINE = -1;
    static private int numbOfMines = 20;
    static private int height = 8, width = 8;
    static private int[][] mines = new int[numbOfMines][2];
    static private int[][] mineField = new int[height][width];

    public static void randomMines(){
        for (int i=0; i<numbOfMines; i++){
            boolean check;
            do {
                check = false;
                mines[i][0]=random.nextInt(width);
                mines[i][1]=random.nextInt(height);

                System.out.println("nr "+i+"\t x: "+ mines[i][0]+ "\t y: "+ mines[i][1]);

                if (i>0){
                    for (int j=0; j<i; j++){
                        if (mines[j][0]==mines[i][0]&&mines[j][1]==mines[i][1]){
                            check=true;
                            break;
                        }
                    }
                }
            }while (check);
        }
    }

    public static void clearMineField(){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                mineField[i][j] = 0;
            }
        }
    }

    public static void putMinesOnField(){
        for (int i = 0; i < numbOfMines; i++) {
            mineField[mines[i][0]][mines[i][1]] = MINE;
        }
    }

    public static void setCountersOnField() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (mineField[i][j]!= MINE){

                    for (int x=i-1; x<=i+1; x++){
                        for (int y=j-1; y<=j+1; y++){
                            try{
                                if (mineField[x][y] == MINE) mineField[i][j] += 1;
                            }catch (ArrayIndexOutOfBoundsException ex){
                                //ignore and check next
                            }
                        }
                    }
                }
            }
        }
    }

    public static void prepareNewField(int h, int w, int numb){
        numbOfMines = numb;
        height = h;
        width = w;
        mines = new int[numbOfMines][2];
        mineField = new int[height][width];
        randomMines();
        clearMineField();
        putMinesOnField();
        setCountersOnField();
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static int getMineField(int x, int y) {
        return mineField[x][y];
    }

    public static int[][] getMines() {
        return mines;
    }

    public static int getNumbOfMines() {
        return numbOfMines;
    }



    public static void main(String[] args) {

        prepareNewField(10, 10, 15);

        System.out.println();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.print(mineField[i][j]==MINE?"*\t":mineField[i][j]+"\t");
            }
            System.out.println();
        }

        gameGUI = new GameGUI();
    }
}
