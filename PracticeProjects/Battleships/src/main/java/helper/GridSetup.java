package helper;
import static helper.GameConst.*;

public class GridSetup {

    private final double boardStartX;
    private final double boardStartY;

    public GridSetup(double boardStartX, double boardStartY){
        this.boardStartX = boardStartX;
        this.boardStartY = boardStartY;
    }

    public  double getColumn(char columnLetter){
        int columnNr = columnLetter-96;
        return boardStartX+FIELD*columnNr;
    }

    public double getBoardStartX() {
        return boardStartX;
    }

    public double getBoardStartY() {
        return boardStartY;
    }

    public double getRow(int rowNr){
        return boardStartY+FIELD*rowNr;
    }

}
