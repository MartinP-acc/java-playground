import java.awt.*;

public class Finder {

    public static boolean move(String[][] maze, Point pos){
        if (pos.y==maze.length-1 && pos.x==maze[0].length-1 ) return true;
        if (pos.y<0 || pos.y>=maze.length) return false;
        else if (pos.x<0 || pos.x>=maze[0].length) return false;
        if (!maze[pos.y][pos.x].equals(".")) return false;
        maze[pos.y][pos.x]="X";
        return move(maze, new Point(pos.x+1, pos.y)) ||
                move(maze, new Point(pos.x, pos.y+1)) ||
                move(maze, new Point(pos.x-1, pos.y)) ||
                move(maze, new Point(pos.x, pos.y-1));
    }

    static boolean pathFinder(String maze) {
        String[] rows = maze.split("\n");
        String[][] maze2d = new String[rows.length][rows[0].length()];
        for (int i=0; i<rows.length; i++){
            maze2d[i] = rows[i].split("");
        }
        Point pos = new Point(0,0);
        return move(maze2d, pos);
    }
}
