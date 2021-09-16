import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Dinglemouse {

    private static final char WALL = '#';
    private static final char ELVES = 'o';
    private static char[][] houseMap;
    private static Set<Point> searched;

    public static boolean allAlone(char[][] house) {
        houseMap = house;
        searched = new HashSet<>();
        Point startPoint = findPotus();
        return !scan(startPoint);
    }

    private static Point findPotus(){
        for (int x=0; x< houseMap.length; x++){
            for (int y=0; y<houseMap[0].length; y++){
                if (houseMap[x][y]=='X') return new Point(x,y);
            }
        }
        throw new IllegalStateException("Can't find Potus");
    }

    private static boolean scan(Point position){
        if (searched.contains(position)) return false;
        if (lookAt(position)==WALL) return false;
        if (lookAt(position)==ELVES) return true;
        searched.add(position);
        return scan(new Point(position.x-1, position.y)) ||
                scan(new Point(position.x, position.y-1)) ||
                scan(new Point(position.x+1, position.y)) ||
                scan(new Point(position.x, position.y+1));
    }

    private static char lookAt(Point point){
        return houseMap[point.x][point.y];
    }

}
