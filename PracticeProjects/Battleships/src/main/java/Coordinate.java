import java.util.Objects;

public class Coordinate {

    public char x;
    public int y;

    public Coordinate(char x, int y){
        this.x = x;
        this.y = y;
    }

    public static Coordinate rightSide(Coordinate shot){
        return new Coordinate((char)(shot.x+1),shot.y);
    }

    public static Coordinate leftSide(Coordinate shot){
        return new Coordinate((char)(shot.x-1),shot.y);
    }

    public static Coordinate below(Coordinate shot){
        return new Coordinate(shot.x,shot.y+1);
    }

    public static Coordinate above(Coordinate shot){
        return new Coordinate(shot.x,shot.y-1);
    }

    public static Coordinate parseCoordinate(String line){
        if (line == null) throw new IllegalArgumentException("game stopped");
        char x = line.charAt(0);
        int y = Integer.parseInt(line.substring(1));
        return new Coordinate(x,y);
    }

    @Override
    public String toString() {
        return x+""+y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
