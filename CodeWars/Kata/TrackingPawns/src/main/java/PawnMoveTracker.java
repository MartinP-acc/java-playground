import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PawnMoveTracker {

    private static final int[] ROW = new int[]{8,7,6,5,4,3,2,1};
    private static final String COL = "abcdefgh";

    private static List<Point> blackPawns;
    private static List<Point> whitePawns;

    private static void initPawns(){
        blackPawns = new ArrayList<>();
        whitePawns = new ArrayList<>();
        for (int i=0; i<8; i++){
            blackPawns.add(new Point(1,i));
            whitePawns.add(new Point(6,i));
        }
    }

    private static Point getPoint(String move){
        int x = getX(move.substring(1));
        int y = COL.indexOf(move.charAt(0));
        return new Point(x,y);
    }

    private static int getX(String digit){
        return ROW[Integer.parseInt(digit)];
    }

    private static boolean checkMove(List<Point> current, List<Point> opponent, String move){

        Point setPos;
        Point lastPos;
        int addX;
        int midX;

        if (current==whitePawns){
            addX = 1;
            midX = 4;
        }else {
            addX = -1;
            midX = 3;
        }

        if (move.length()==2){
            setPos = getPoint(move);
            if (setPos.x==midX){
                lastPos = new Point(setPos.x+(2*addX), setPos.y);
                if (current.contains(lastPos) && !opponent.contains(setPos) && !opponent.contains(new Point(setPos.x+addX, setPos.y))){
                    current.remove(lastPos);
                    current.add(setPos);
                    return true;
                }
            }
            lastPos = new Point(setPos.x+addX, setPos.y);
            if (current.contains(lastPos) && !opponent.contains(setPos)){
                current.remove(lastPos);
                current.add(setPos);
                return true;
            }
        }

        if (move.length()==4){
            setPos = getPoint(move.substring(2));
            lastPos = new Point(setPos.x+addX,COL.indexOf(move.charAt(0)));
            if (current.contains(lastPos) && opponent.contains(setPos)){
                current.remove(lastPos);
                opponent.remove(setPos);
                current.add(setPos);
                return true;
            }
        }
        return false;
    }

    private static String[][] drawFinalBoard(){
        String[][] board = new String[8][8];
        for (String[] b :board) Arrays.fill(b,".");
        whitePawns.forEach(p -> board[p.x][p.y]="P");
        blackPawns.forEach(p -> board[p.x][p.y]="p");

        for (String[] x : board){
            for (String y : x){
                System.out.print(y+" ");
            }
            System.out.println();
        }
        return board;
    }

    private static String[][] invalidMove(String move){
        return new String[][]{{move+" is invalid"}};
    }

    public static String[][] movePawns(String[] moves) {

        initPawns();

        for (int i = 0; i < moves.length; i++){
            if (i%2==0) {
                if (!checkMove(whitePawns,blackPawns,moves[i])) return invalidMove(moves[i]);
            }else {
                if (!checkMove(blackPawns,whitePawns,moves[i])) return invalidMove(moves[i]);
            }
        }
        System.out.println();
        return drawFinalBoard();
    }
}