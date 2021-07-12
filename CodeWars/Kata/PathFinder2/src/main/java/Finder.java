import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Finder {
    static private String[][] maze2D;
    static private final HashMap<Point,Integer> basePoints = new HashMap<>();
    static private final HashMap<Point,Integer> directions = new HashMap<>();
    static private final HashMap<Point,Integer> checked = new HashMap<>();

    private static void splitMazeToArray(String maze){
        String[] line = maze.split("\n");
        maze2D = new String[line.length+2][line[0].length()+2];
        for (int x = 0; x< maze2D.length; x++){
            maze2D[0][x]="W";
            maze2D[maze2D.length-1][x]="W";
        }
        for (int y=0; y< line.length; y++){
            maze2D[y+1][0]="W";
            System.arraycopy(line[y].split(""), 0, maze2D[y + 1], 1, line.length);
            maze2D[y+1][maze2D[y+1].length-1]="W";
        }
    }

    private static void countCellValueFromEndToStart(){
        basePoints.put(new Point(maze2D[0].length-2, maze2D.length-2 ),0);
        while (!basePoints.isEmpty()){
            for (Map.Entry<Point,Integer> base : basePoints.entrySet()){
                directions.put(new Point(base.getKey().x-1,base.getKey().y),base.getValue()+1);
                directions.put(new Point(base.getKey().x,base.getKey().y-1),base.getValue()+1);
                directions.put(new Point(base.getKey().x+1,base.getKey().y),base.getValue()+1);
                directions.put(new Point(base.getKey().x,base.getKey().y+1),base.getValue()+1);
            }
            checked.putAll(basePoints);
            basePoints.clear();
            for (Map.Entry<Point,Integer> dir : directions.entrySet()){
                if (maze2D[dir.getKey().y][dir.getKey().x].equals(".") &&
                        !checked.containsKey(dir.getKey())) basePoints.put(dir.getKey(),dir.getValue());
            }
            directions.clear();
            if (checked.containsKey(new Point(1,1))) basePoints.clear();
        }
    }

    public static int pathFinder(String maze) {
        splitMazeToArray(maze);
        countCellValueFromEndToStart();
        int result = checked.getOrDefault(new Point(1,1),-1);
        checked.clear();
        return result;
    }
}
