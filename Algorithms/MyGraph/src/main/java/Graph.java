import java.util.*;

public class Graph {

    protected Map<String,Node> allVertex = new HashMap<>();

    public Node vertex(String name){
        if (!allVertex.containsKey(name)) allVertex.put(name, new Node(name, this));
        return allVertex.get(name);
    }

    public void showVertexes(){
        allVertex.forEach((s, node) -> System.out.print(s+" "));
    }

    static class Node{

        private final Graph instance;
        private final String name;
        private final Set<String> neighbours;

        Node(String name, Graph instance) {
            this.instance = instance;
            this.name = name;
            neighbours = new HashSet<>();
        }

        public void edgeTo(String ... neighbours){
            this.neighbours.addAll(Arrays.asList(neighbours));
            for (String n : this.neighbours){
                if (!instance.allVertex.containsKey(n)) instance.allVertex.put(n, new Node(n, instance));
                instance.allVertex.get(n).neighbours.add(this.name);
            }
        }

        public void removeEdgeTo(String name){
            if (neighbours.contains(name)){
                instance.allVertex.get(name).neighbours.remove(this.name);
                neighbours.remove(name);
            } else System.out.println("Can't remove edge "+this.name+"-"+name+"\nedge is not exist");
        }

        public String showEdges(){
            return this.name+": "+ String.join(",", neighbours);
        }
    }


}
