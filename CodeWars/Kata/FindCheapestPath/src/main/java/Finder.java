import java.util.*;
import java.awt.Point;

class Node{

    public int weight;
    public Node previous;
    public String direction;

    Node(int row, int col){
        this.weight = Finder.grid[row][col];
    }

    Node(int addWeight, Node previous, String direction){
        setNode(addWeight,previous,direction);
    }

    public void setNode(int weight, Node previous, String direction){
        this.weight = weight;
        this.previous = previous;
        this.direction = direction;
    }

    public List<Point> neighbours(Point c){
        List<Point> n = new ArrayList<>();
        if (c.x>0) n.add(new Point(c.x-1,c.y));
        if (c.x<Finder.xLimit) n.add(new Point(c.x+1,c.y));
        if (c.y>0) n.add(new Point(c.x,c.y-1));
        if (c.y<Finder.yLimit) n.add(new Point(c.x,c.y+1));
        return n;
    }
}

public class Finder {

    public static int[][] grid;
    public static int xLimit;
    public static int yLimit;
    public static Map<Point,Node> openSet;
    public static List<Point> closedSet;
    public static Point start;
    public static Point end;
    public static Stack<String> path;

    private static List<String> findPath(){

        path = new Stack<>();
        closedSet = new ArrayList<>();
        openSet = new HashMap<>();
        openSet.put(start, new Node(start.x, start.y));

        while (!openSet.isEmpty()){

            Point current = findLowestWeight(openSet);
            Node currentNode = openSet.get(current);

            if (current.equals(end)) return reconstructPath(currentNode);

            for (Point neighbor : currentNode.neighbours(current)){
                if (!closedSet.contains(neighbor)){
                    int temp = currentNode.weight + grid[neighbor.x][neighbor.y];
                    String direction = setDirection(current,neighbor);
                    if (openSet.containsKey(neighbor)){
                        if (temp < openSet.get(neighbor).weight){
                            openSet.get(neighbor).setNode(temp,currentNode,direction);
                        }
                    } else {
                        openSet.put(neighbor,new Node(temp,currentNode,direction));
                    }
                }
            }
            closedSet.add(current);
            openSet.remove(current);
        }
        return new ArrayList<>();
    }

    private static List<String> reconstructPath(Node currentNode) {
        Node node = currentNode;
        while (node.previous!=null){
            path.push(node.direction);
            node = node.previous;
        }
        return path;
    }

    private static String setDirection(Point c, Point n) {
        if (c.x < n.x) return "up";
        if (c.x > n.x) return "down";
        if (c.y < n.y) return "left";
        return "right";
    }

    private static Point findLowestWeight(Map<Point,Node> openSet) {
        Point winner = new Point(-1,-1);
        for (Map.Entry<Point,Node> spot : openSet.entrySet()){
            if (winner.x == -1) winner = spot.getKey();
            else {
                if (spot.getValue().weight < openSet.get(winner).weight) winner = spot.getKey();
            }
        }
        return winner;
    }

    public static List<String> cheapestPath(int[][] t, Point start, Point finish){
        Finder.start = finish;
        end = start;
        grid = t;
        xLimit = grid.length-1;
        yLimit = grid[0].length-1;
        return findPath();
    }
}