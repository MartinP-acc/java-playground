import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Finder {

    private static int movesToExit;

    public static void move(String[][] maze, Point pos, Set<Point> way){
        if (way.size()<movesToExit) {
            if (pos.y >= 0 && pos.y < maze.length) {
                if (pos.x >= 0 && pos.x < maze[pos.y].length) {
                    if (maze[pos.y][pos.x].equals(".") && !way.contains(pos)) {
                        if (pos.y == maze.length - 1 && pos.x == maze[pos.y].length - 1) {
                            movesToExit = way.size();
                            for (int y=0; y< maze.length; y++){
                                System.out.println();
                                for (int x=0; x<maze[y].length; x++){
                                    if (way.contains(new Point(x,y))) System.out.print("*");
                                    else System.out.print(maze[y][x]);
                                }
                            }
                            System.out.println(way.size());
                        } else {
                            Set<Point> newWay = new HashSet<>(way);
                            newWay.add(pos);
                            move(maze, new Point(pos.x + 1, pos.y), newWay);
                            move(maze, new Point(pos.x, pos.y + 1), newWay);
                            move(maze, new Point(pos.x - 1, pos.y), newWay);
                            move(maze, new Point(pos.x, pos.y - 1), newWay);
                        }
                    }
                }
            }
        }
    }

    public static int pathFinder(String maze) {
        String[] rows = maze.split("\n");
        String[][] maze2d = new String[rows.length][rows[0].length()];
        for (int y=0; y< maze2d.length; y++){
            maze2d[y] = rows[y].split("");
        }
        movesToExit=Integer.MAX_VALUE;
        Set<Point> way = new HashSet<>();
        move(maze2d, new Point(0,0), way);
        if (movesToExit==Integer.MAX_VALUE) return -1;
        return movesToExit;
    }
}
