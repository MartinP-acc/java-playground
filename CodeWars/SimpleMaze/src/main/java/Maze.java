import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Maze {

    public static List<Point> kateWasThere;

    public static boolean checkWay(String[] maze, Point pos){
        if (pos.y < 0 || pos.y >= maze.length) return true;
        else if (pos.x < 0 || pos.x >= maze[pos.y].length()) return true;
        if (maze[pos.y].charAt(pos.x)!=' ') return false;
        if (kateWasThere.contains(pos)) return false;
        kateWasThere.add(pos);
        return checkWay(maze, new Point(pos.x+1, pos.y)) ||
                checkWay(maze, new Point(pos.x-1, pos.y)) ||
                checkWay(maze, new Point(pos.x, pos.y+1)) ||
                checkWay(maze, new Point(pos.x, pos.y-1));
    }

    public static boolean hasExit(String[] maze) {
        kateWasThere = new ArrayList<>();
        Point pos = new Point(-1,-1);

        for(int y=0; y< maze.length; y++){
            for (int x=0; x<maze[y].length(); x++){
                if (maze[y].charAt(x)=='k'){
                    if (pos.x == -1) pos = new Point(x,y);
                    else throw new IllegalArgumentException("There are more than one Kate in the maze");
                }
            }
            if (pos.x == -1) throw new RuntimeException("There isn't Kate in the maze");
        }
        return checkWay(maze, new Point(pos.x+1, pos.y)) ||
                checkWay(maze, new Point(pos.x-1, pos.y)) ||
                checkWay(maze, new Point(pos.x, pos.y+1)) ||
                checkWay(maze, new Point(pos.x, pos.y-1));
    }
}
