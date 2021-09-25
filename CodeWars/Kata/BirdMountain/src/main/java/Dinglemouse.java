import java.awt.*;
import java.util.*;
import java.util.List;

public class Dinglemouse {

    private static final int[][] ALL_DIR = new int[][] {{-1,0},{1,0},{0,-1},{0,1},{1,1},{-1,-1},{-1,1},{1,-1}};
    private static final int[][] BASIC_DIR = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    private static final int NONE = ' ';
    private static final int PEAK = '^';
    private static char[][] grid;
    private static Set<Point> openSet;

    public static int peakHeight(char[][] mountain) {
        grid = mountain;
        openSet = new HashSet<>();
        findPeakChar();
        int HighestPointValue = 0;
        while (!openSet.isEmpty()){
            Point current = getFirstLowest();
            openSet.addAll(getNeighbours(current));
            setGridVal(current,getHeightVal(current));
            HighestPointValue = Math.max(HighestPointValue,getValByPoint(current));
            openSet.remove(current);
            if (openSet.isEmpty()) findPeakChar();
        }
        return HighestPointValue;
    }

    private static void findPeakChar(){
        for (int x=0; x<grid.length; x++){
            for (int y=0; y<grid[x].length; y++){
                Point current = new Point(x,y);
                if (getValByPoint(current) == PEAK){
                    openSet.add(current);
                    return;
                }
            }
        }
    }

    private static Point getFirstLowest() {
        Point next = new Point();
        int min = Integer.MAX_VALUE;
        for (Point p : openSet){
            int currPointHeight = getHeightVal(p);
            if (currPointHeight<min){
                next = p;
                min=currPointHeight;
            }
        }
        return next;
    }

    private static int getHeightVal(Point p){
        int lowest = Integer.MAX_VALUE;
        for (int[] direction : BASIC_DIR){
            Point neighbor = new Point(p.x+direction[0],p.y+direction[1]);
            lowest = Math.min(getNeighborHeight(neighbor),lowest);
        }
        return lowest + 1;
    }

    private static int getNeighborHeight(Point neighbor) {
        int neighborHeight;
        try {
            neighborHeight = getValByPoint(neighbor);
            if (neighborHeight == NONE) return  0;
        }catch (IndexOutOfBoundsException e){
            return 0;
        }
        return neighborHeight;
    }

    private static List<Point> getNeighbours(Point p){
        List<Point> neighbours = new ArrayList<>();
        for (int[] direction : ALL_DIR){
            Point neighbor = new Point(p.x+direction[0],p.y+direction[1]);
            try {
                if (getValByPoint(neighbor) == PEAK) neighbours.add(neighbor);
            }catch (IndexOutOfBoundsException e){
                //skip out of bound point
            }
        }
        return neighbours;
    }

    private static int getValByPoint(Point p) throws IndexOutOfBoundsException{
        return grid[p.x][p.y];
    }

    private static void setGridVal(Point p, int val){
        grid[p.x][p.y]= (char) val;
    }
}
