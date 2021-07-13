import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Dinglemouse {

    private static List<Point> endPoints;
    private static int chNum;

    private static void findXPoints(final char[][] grid){
        chNum=0;
        endPoints = new ArrayList<>();
        for (int x=0; x< grid.length; x++){
            for (int y=0; y< grid[x].length; y++){
                if (grid[x][y]=='X') endPoints.add(new Point(x,y));
                if (grid[x][y]!=' ') chNum++;
            }
        }
    }

    private static boolean isValid(char[][] grid, Point start, Point end){
        List<Point> nearP = new ArrayList<>();
        Stack<Point> visited = new Stack<>();
        visited.add(new Point(-1,-1));
        Point p = new Point(start.x, start.y);
        System.out.println("\n"+end+" end");

        while (p.x!=end.x || p.y != end.y){
            if (grid[p.x][p.y]!='-'){
                if (p.x<grid.length-1 && grid[p.x+1][p.y]!=' ') nearP.add(new Point(p.x+1,p.y));
                if (p.x>0 && grid[p.x-1][p.y]!=' ') nearP.add(new Point(p.x-1,p.y));
            }
            if (grid[p.x][p.y]!='|'){
                if (p.y<grid[p.x].length-1 && grid[p.x][p.y+1]!=' ') nearP.add(new Point(p.x,p.y+1));
                if (p.y>0 && grid[p.x][p.y-1]!=' ') nearP.add(new Point(p.x,p.y-1));
            }
            if (grid[p.x][p.y]=='+'){
                if (visited.peek().y== p.y){
                    nearP.remove(new Point(p.x-1,p.y));
                    nearP.remove(new Point(p.x+1,p.y));
                    if (p.y<grid[p.x].length-1 && grid[p.x][p.y+1]=='|') nearP.remove(new Point(p.x, p.y+1));
                    if (p.y>0 && grid[p.x][p.y-1]=='|') nearP.remove(new Point(p.x, p.y-1));
                }
                if (visited.peek().x== p.x){
                    nearP.remove(new Point(p.x,p.y-1));
                    nearP.remove(new Point(p.x,p.y+1));
                    if (p.x< grid.length-1 && grid[p.x+1][p.y]=='-') nearP.remove(new Point(p.x+1, p.y));
                    if (p.x>0 && grid[p.x-1][p.y]=='-') nearP.remove(new Point(p.x-1, p.y));
                }
            }
            if (grid[p.x][p.y]=='X'){
                if (p.x>0 && grid[p.x-1][p.y]=='-') nearP.remove(new Point(p.x-1, p.y));
                if (p.y>0 && grid[p.x][p.y-1]=='|') nearP.remove(new Point(p.x, p.y-1));
                if (p.x< grid.length-1 && grid[p.x+1][p.y]=='-') nearP.remove(new Point(p.x+1, p.y));
                if (p.y< grid[p.x].length-1 && grid[p.x][p.y+1]=='|') nearP.remove(new Point(p.x, p.y+1));
            }
            System.out.print(p.x+","+p.y+"-> ");
            nearP.removeIf(visited::contains);
            if (nearP.size()!=1) {
                System.out.println("\n wrong number possible directions : "+nearP.size());
                return false;
            }
            visited.push(new Point(p.x,p.y));
            p = nearP.get(0);
            nearP.clear();
        }
        System.out.print(p.x+","+p.y+" finish ");
        System.out.println(chNum+" - "+visited.size());
        return chNum==visited.size();
    }

    public static boolean line(final char [][] grid) {
        findXPoints(grid);
        if (endPoints.size()!=2) return false;
        return isValid(grid,endPoints.get(0), endPoints.get(1)) ||
                isValid(grid,endPoints.get(1), endPoints.get(0));
    }
}