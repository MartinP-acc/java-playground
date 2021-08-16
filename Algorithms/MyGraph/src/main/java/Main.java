public class Main {

    public static void main(String[] args) {

        Graph cities = new Graph();

        cities.vertex("Rzeszów").edgeTo("Lublin","Kraków");
        cities.vertex("Łódź").edgeTo("Kraków", "Wrocław", "Warszawa", "Toruń");
        cities.vertex("Warszawa").edgeTo("Lublin", "Toruń");
        cities.vertex("Kraków").edgeTo("Wrocław");

        cities.showVertexes();

        System.out.println();

        System.out.println(cities.vertex("Kraków").showEdges());
        System.out.println(cities.vertex("Toruń").showEdges());

        System.out.println("remove test");
        System.out.println(cities.vertex("Wrocław").showEdges());
        cities.vertex("Łódź").removeEdgeTo("Wrocław");
        System.out.println(cities.vertex("Wrocław").showEdges());
        cities.vertex("Wrocław").removeEdgeTo("Łódź");

    }
}
