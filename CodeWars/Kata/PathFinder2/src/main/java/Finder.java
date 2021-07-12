import java.awt.*;
import java.util.HashMap;
import java.util.Map;
/*
    solution based on Sample algorithm
    https://en.wikipedia.org/wiki/Pathfinding
 */
public class Finder {
    static private String[][] maze2D;
    static private final HashMap<Point,Integer> basePoints = new HashMap<>(); // stores starting coordinates
    static private final HashMap<Point,Integer> directions = new HashMap<>(); // stores all directions from starting points
    static private final HashMap<Point,Integer> checked = new HashMap<>();    // stores points with steps from end as value

    private static void splitMazeToArray(String maze){              //split (String)maze,
        String[] line = maze.split("\n");                     //creates 2D maze,
        maze2D = new String[line.length+2][line[0].length()+2];     //and add walls around maze
        for (int x = 0; x< maze2D.length; x++){                     //example:
            maze2D[0][x]="W";                                       //            W W W W W
            maze2D[maze2D.length-1][x]="W";                         //  . W .     W . W . W
        }                                                           //  . W .  -> W . W . W
        for (int y=0; y< line.length; y++){                         //  . . .     W . . . W
            maze2D[y+1][0]="W";                                     //            W W W W W
            System.arraycopy(line[y].split(""), 0, maze2D[y + 1], 1, line.length);
            maze2D[y+1][maze2D[y+1].length-1]="W";
        }
    }

    private static void countCellValueFromEndToStart(){
        basePoints.put(new Point(maze2D[0].length-2, maze2D.length-2 ),0);
        while (!basePoints.isEmpty()){          // seeks until no more possible directions
                                                // or found start point (2nd condition is lower)
            for (Map.Entry<Point,Integer> base : basePoints.entrySet()){
                                                  // add new direction for every starting point
                directions.put(new Point(base.getKey().x-1,base.getKey().y),base.getValue()+1);
                directions.put(new Point(base.getKey().x,base.getKey().y-1),base.getValue()+1);
                directions.put(new Point(base.getKey().x+1,base.getKey().y),base.getValue()+1);
                directions.put(new Point(base.getKey().x,base.getKey().y+1),base.getValue()+1);
            }
            checked.putAll(basePoints);           // pulls all starting points to final points list with steps
            basePoints.clear();
            for (Map.Entry<Point,Integer> dir : directions.entrySet()){
                                                // removes repeating points and if point is wall
                if (maze2D[dir.getKey().y][dir.getKey().x].equals(".") &&
                        !checked.containsKey(dir.getKey())) basePoints.put(dir.getKey(),dir.getValue());
            }                                   // and adds rest as new starting points
            directions.clear();
            if (checked.containsKey(new Point(1,1))) basePoints.clear(); // 2nd condition
        }
    }

    public static int pathFinder(String maze) {
        splitMazeToArray(maze);
        countCellValueFromEndToStart();             // if there is starting point return steps
        int result = checked.getOrDefault(new Point(1,1),-1);
        checked.clear();                            // if not return -1
        return result;
    }
}
