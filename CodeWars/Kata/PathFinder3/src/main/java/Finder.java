import java.awt.*;
import java.util.*;
import java.util.List;

public class Finder {

    private static String[] maze;
    private static int len;
    private static Map<Point,Integer> climbs;

    private static void initMaze(String s){
        maze = s.split("\n");
        len = maze.length;
    }

    private static int getAlt(Point p){
        return Integer.parseInt(maze[p.x].substring(p.y,p.y+1));
    }

    private static Point findBestWay(){
        int minimal = Integer.MAX_VALUE;
        Point best = new Point();
        for (Map.Entry<Point,Integer> pair : climbs.entrySet()){
            if (pair.getValue()<minimal){
                minimal=pair.getValue();
                best=pair.getKey();
            } else if (pair.getValue() == minimal && best.x+best.y<pair.getKey().x+pair.getKey().y){
                best=pair.getKey();
            }
        }
        return best;
    }

    private static List<Point> getNeighbours(Point c){
        List<Point> neighbours = new ArrayList<>();
        if (c.x>0) neighbours.add(new Point(c.x-1, c.y));
        if (c.y>0) neighbours.add(new Point(c.x, c.y-1));
        if (c.x<len-1) neighbours.add(new Point(c.x+1, c.y));
        if (c.y<len-1) neighbours.add(new Point(c.x, c.y+1));
        return neighbours;
    }

    static int pathFinder(String maze) {
        initMaze(maze);

        Set<Point> visited = new HashSet<>();
        climbs = new HashMap<>();

        Point end = new Point(len-1,len-1);
        Point current = new Point(0,0);
        climbs.put(current,0);

        while (!current.equals(end)){
            for (Point p : getNeighbours(current)) {
                int temp = climbs.get(current)+Math.abs(getAlt(current)-getAlt(p));
                if (!visited.contains(p)) {
                    if (temp < climbs.getOrDefault(p,Integer.MAX_VALUE)) climbs.put(p,temp);
                }
            }
            visited.add(current);
            climbs.remove(current);
            current = findBestWay();
        }
        return climbs.get(current);
    }
}
