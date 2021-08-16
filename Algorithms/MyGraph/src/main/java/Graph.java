import java.util.*;

public class Graph {

    protected Map<String,Node> allVertex = new HashMap<>();

    public Node vertex(String name){
        if (!allVertex.containsKey(name)) allVertex.put(name, new Node(name));
        return allVertex.get(name);
    }

    public void showVertexes(){
        allVertex.forEach((s, node) -> System.out.print(s+" "));
    }

    static class Node extends Graph{

        private String name;
        private Set<String> neighbours;

        Node(String name) {
            this.name = name;
            neighbours = new HashSet<>();
        }

        public void edgeTo(String ... neighbours){
            this.neighbours.addAll(Arrays.asList(neighbours));
            for (String n : this.neighbours){
                if (!allVertex.containsKey(n)) super.allVertex.put(n, new Node(n));
                allVertex.get(n).neighbours.add(this.name);
            }
        }

        public String showEdges(){
            return this.name+": "+ String.join(",", neighbours);
        }
    }


}
