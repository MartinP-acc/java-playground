import java.util.*;
import java.awt.Point;

class Node{

    private final int row;
    private final int col;
    private int weight;
    private final int limit = Finder.getLimit()-1;
    private final String direction;
    private List<Node> neighbours;

    public Node(int row, int col){
        this.row = row;
        this.col = col;
        this.direction = "";
    }

    public Node(int row, int col, int weight, String direction) {
        this.row = row;
        this.col = col;
        this.weight = weight + Finder.getWeight(row, col);
        this.direction = direction;
    }

    private void addNeighbours(){
        neighbours = new ArrayList<>();
        if (row>0) neighbours.add(new Node(row-1, col, this.weight, "up "));
        if (row<limit) neighbours.add(new Node(row+1, col, this.weight, "down "));
        if (col>0) neighbours.add(new Node(row, col-1, this.weight, "left "));
        if (col<limit) neighbours.add(new Node(row, col+1, this.weight, "right "));
    }

    public List<Node> getNeighbours() {
        addNeighbours();
        return neighbours;
    }

    public String getDirection() {
        return direction;
    }

    public int getWeight() {
        return weight;
    }

    public Point getPoint(){
        return new Point(row,col);
    }
}

public class Finder {

    private static int[][] grid;
    private static List<Point> visited;
    private static Stack<Node> queue;
    private static Point end;
    private static List<String> result;

    public static int getWeight(int row, int col){
        return grid[row][col];
    }

    public static int getLimit(){
        return grid.length;
    }

    public static void findPath(Node next){
        visited.add(next.getPoint());
        result.add(next.getDirection());
        System.out.println(next.getWeight()+" "+next.getDirection()+" "+next.getPoint().x+","+next.getPoint().y);
        if (!next.getPoint().equals(end)){
            next.getNeighbours().forEach(node -> {
                if (!visited.contains(node.getPoint())) queue.push(node);
            });
            queue.sort(Comparator.comparing(Node::getWeight).reversed());
            while (!queue.isEmpty()){
                findPath(queue.pop());
            }
        }
    }

    public static List<String> cheapestPath(int[][] t, Point start, Point finish){
        grid = t;
        end = finish;
        queue = new Stack<>();
        Node startNode = new Node(start.x, start.y);
        visited = new ArrayList<>();
        result = new ArrayList<>();
        findPath(startNode);
        System.out.println();
        return result;
    }
}