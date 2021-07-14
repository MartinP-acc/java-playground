import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.awt.Point;
import java.util.Map;

public class Finder {

    private static HashMap<Point,Integer> GRID;
    private static HashMap<Point,Integer> pathValue;

    private static void setGrid(int[][] t){
        for (int x=0; x<t.length; x++){
            for (int y=0; y<t[x].length; y++){
                GRID.put(new Point(x,y),t[x][y]);
            }
        }
    }

    private static Point up(Point currentP){
        return new Point(currentP.x, currentP.y-1);
    }

    private static Point down(Point currentP){
        return new Point(currentP.x, currentP.y+1);
    }

    private static Point right(Point currentP){
        return new Point(currentP.x-1, currentP.y);
    }

    private static Point left(Point currentP){
        return new Point(currentP.x+1, currentP.y);
    }

    private static void setPathValue(Point finish, int[][] t){
        HashMap<Point,Integer> currentP = new HashMap<>();
        HashMap<Point,Integer> visited = new HashMap<>();
        currentP.put(finish,GRID.get(finish));
        while (!currentP.isEmpty()){
            HashMap<Point,Integer> nearby = new HashMap<>();
            currentP.forEach((point, integer) -> {
                if (GRID.containsKey(up(point))){
                    int value = GRID.get(up(point))+integer;
                    if (nearby.containsKey(up(point))) value = Math.min(nearby.get(up(point)),value);
                    if (pathValue.containsKey(up(point))) value = Math.min(pathValue.get(up(point)),value);
                    nearby.put(up(point),value);
                }
                if (GRID.containsKey(down(point))){
                    int value = GRID.get(down(point))+integer;
                    if (nearby.containsKey(down(point))) value = Math.min(nearby.get(down(point)),value);
                    if (pathValue.containsKey(down(point))) value = Math.min(pathValue.get(down(point)),value);
                    nearby.put(down(point),value);
                }
                if (GRID.containsKey(right(point))){
                    int value = GRID.get(right(point))+integer;
                    if (nearby.containsKey(right(point))) value = Math.min(nearby.get(right(point)),value);
                    if (pathValue.containsKey(right(point))) value = Math.min(pathValue.get(right(point)),value);
                    nearby.put(right(point),value);
                }
                if (GRID.containsKey(left(point))){
                    int value = GRID.get(left(point))+integer;
                    if (nearby.containsKey(left(point))) value = Math.min(nearby.get(left(point)),value);
                    if (pathValue.containsKey(left(point))) value = Math.min(pathValue.get(left(point)),value);
                    nearby.put(left(point),value);
                }
            });
            pathValue.putAll(nearby);
            visited.putAll(currentP);
            currentP.clear();
            nearby.forEach((point, integer) -> {if (!visited.containsKey(point)) currentP.put(point,integer);});
            nearby.clear();
        }
    }

    private static ArrayList<String> findCheapest(Point start, Point finish){

        ArrayList<String> path = new ArrayList<>();
        Point current = start;
        while (!current.equals(finish)){
            HashMap<Point,Integer> nearby = new HashMap<>();
            if (pathValue.containsKey(up(current)))nearby.put(up(current),pathValue.get(up(current)));
            if (pathValue.containsKey(down(current)))nearby.put(down(current),pathValue.get(down(current)));
            if (pathValue.containsKey(right(current)))nearby.put(right(current),pathValue.get(right(current)));
            if (pathValue.containsKey(left(current)))nearby.put(left(current),pathValue.get(left(current)));

            String direction="";
            int minValue = Integer.MAX_VALUE;
            Point temp = new Point();
            for (Map.Entry<Point,Integer> n : nearby.entrySet()){
                if (minValue>n.getValue()){
                    minValue=n.getValue();
                    temp = n.getKey();
                    if (n.getKey().equals(up(current))) direction="left";
                    if (n.getKey().equals(down(current))) direction="right";
                    if (n.getKey().equals(right(current))) direction="up";
                    if (n.getKey().equals(left(current))) direction="down";
                }
            }
            current=temp;
            path.add(direction);
        }
        return path;
    }

    private static void print(HashMap<Point,Integer> grid, int[][] t){
        int[][] map = new int[t.length][t[0].length];
        for (Map.Entry<Point,Integer> p : grid.entrySet()){
            map[p.getKey().y][p.getKey().x]=p.getValue();
        }
        for (int[] y : map){
            System.out.println();
            for (int x : y){
                System.out.printf("%d\t",x);
            }
        }
    }

    static List<String> cheapestPath(int[][] t, Point start, Point finish) {
        GRID = new HashMap<>();
        pathValue = new HashMap<>();
        System.out.println(finish);
        setGrid(t);
        setPathValue(finish, t);
        return findCheapest(start,finish);
    }
}