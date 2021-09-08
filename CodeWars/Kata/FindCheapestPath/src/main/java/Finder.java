import java.awt.*;
import java.util.*;
import java.util.List;

public class Finder {

    private static int[][] grid;
    private static Map<Point,Integer> weights;
    private static Map<Point,Point> parents;
    private static int height;
    private static int width;

    private static void init(int[][] t){
        grid = t;
        height = t.length;
        width = t[0].length;
    }

    private static int getCost(Point p){
        return grid[p.x][p.y];
    }

    private static List<Point> neighbours(Point p){
        List<Point> pointList = new ArrayList<>();
        if (p.x>0) pointList.add(new Point(p.x-1,p.y));
        if (p.y>0) pointList.add(new Point(p.x,p.y-1));
        if (p.x<height-1) pointList.add(new Point(p.x+1,p.y));
        if (p.y<width-1) pointList.add(new Point(p.x,p.y+1));
        return pointList;
    }

    private static Point findWithLowestCost(){
        int minCost = Integer.MAX_VALUE;
        Point winner = new Point();
        for (Map.Entry<Point,Integer> pair : weights.entrySet()){
            if (pair.getValue()<minCost){
                minCost=pair.getValue();
                winner=pair.getKey();
            }
        }
        return winner;
    }

    private static List<String> getPath(Point p, Point finish){
        List<String> path = new ArrayList<>();
        while (!p.equals(finish)){
            Point prev = parents.get(p);
            if (prev.x > p.x) path.add("down");
            else if (prev.x < p.x) path.add("up");
            else if (prev.y > p.y) path.add("right");
            else path.add("left");
            p = prev;
        }
        return path;
    }

    public static List<String> cheapestPath(int[][] t, Point start, Point finish){
        init(t);

        Set<Point> visited = new HashSet<>();
        weights = new HashMap<>();
        parents = new HashMap<>();

        weights.put(finish,getCost(finish));
        Point current = finish;

        while (!current.equals(start)){
            for(Point n : neighbours(current)){
                if (!visited.contains(n)){
                    int tempCost = weights.get(current)+getCost(n);
                    if (!weights.containsKey(n) || tempCost<weights.get(n)){
                        weights.put(n,tempCost);
                        parents.put(n,current);
                    }
                }
            }
            weights.remove(current);
            visited.add(current);
            current = findWithLowestCost();
        }
        return getPath(current,finish);
    }
}