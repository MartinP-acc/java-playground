import java.util.Random;
import static helper.GameConst.*;

public class Generator {

    private final Random random;
    private final Coordinate coordinate;
    private double x;
    private double y;
    String alignment;

    public Generator(){
        this.random = new Random();
        this.coordinate = new Coordinate('a',1);
        this.alignment = "H";
    }

    public void generate(){
        int randX = random.nextInt(10)+1;
        int randY = random.nextInt(10)+1;
        coordinate.x = (char) (randX+96);
        coordinate.y = randY;
        x = randX * FIELD;
        y = randY * FIELD;
        alignment = random.nextBoolean() ? "H" : "V";
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public double getPlayerX() {
        return x + PLAYER_BOARD_X;
    }

    public double getPlayerY() {
        return y + PLAYER_BOARD_Y;
    }

    public double getOpponentX() {
        return x + OPPONENT_BOARD_X;
    }

    public double getOpponentY() {
        return y + OPPONENT_BOARD_Y;
    }

    public String getAlignment() {
        return alignment;
    }
}
