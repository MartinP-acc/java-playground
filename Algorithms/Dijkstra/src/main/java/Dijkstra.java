import java.util.*;

public class Dijkstra {

    private static Map<String,Map<String,Integer>> graph;
    public static Map<String,Integer> costs;
    public static List<String> proceed;
    public static Map<String,String> parent;

    public static void findCheapestWay(){
        parent = new HashMap<>();
        proceed = new ArrayList<>();
        Integer max = Integer.MAX_VALUE;

        String node = "start";
        while (!node.equals("meta")){
            int cost = costs.get(node);
            for (Map.Entry<String,Integer> neighbor : graph.get(node).entrySet()){
                int newCost = cost + neighbor.getValue();
                if (costs.getOrDefault(neighbor.getKey(),max)>newCost){
                    costs.put(neighbor.getKey(),newCost);
                    parent.put(neighbor.getKey(),node);
                }
            }
            proceed.add(node);
            node = findLowestCostNode();
        }
    }

    private static void printPath(){
        String node = "meta";
        Stack<String> path = new Stack<>();
        path.add(node);
        do {
            node = parent.get(node);
            path.push(node);
        }while (!node.equals("start"));
        System.out.println(String.join(" <= ",path));
    }

    private static String findLowestCostNode() {
        String node = "";
        Integer cost = Integer.MAX_VALUE;
        for (Map.Entry<String,Integer> n : costs.entrySet()){
            if (n.getValue()<cost && !proceed.contains(n.getKey())){
                node = n.getKey();
                cost = n.getValue();
            }
        }
        return node;
    }

    public static void main(String[] args) {


        System.out.print("""
                  example from book\s
                 start ----(6)----> a -(1)-- \s
                   |                ^      | \s
                  (2)              (3)     v \s
                   |                |     meta
                   |                |      ^ \s
                   ---------------> b -(5)-| \s

                search cheapest way from start to meta :
                result :"""
        );

        graph = new HashMap<>();
        graph.put("start",new HashMap<>());
        graph.get("start").put("a",6);
        graph.get("start").put("b",2);

        graph.put("a",new HashMap<>());
        graph.get("a").put("meta",1);

        graph.put("b",new HashMap<>());
        graph.get("b").put("a",3);
        graph.get("b").put("meta",5);

        graph.put("meta",new HashMap<>());

        costs = new HashMap<>();
        costs.put("start",0);

        findCheapestWay();
        printPath();
    }
}
