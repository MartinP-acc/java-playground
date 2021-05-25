import find.FindNearest;
import points.RandomPoints;
import points.SinglePoint;

public class Main {

    public static void main(String[] args) {

        SinglePoint centerPoint = new SinglePoint(30, 12);
        System.out.println("Dla punktu: "+ centerPoint);

        RandomPoints randomPoints = new RandomPoints(30);
        randomPoints.addNewRandomPoints();
        System.out.println("Spośród zbioru "+randomPoints.getNrOfPoints()+" punktów");

        FindNearest findNearest = FindNearest.getInstance();
        findNearest.findPoint(randomPoints.getPoints(),centerPoint);
        System.out.println("Najbliższy punkt to : "+findNearest.getNearestPoint());

        System.out.println("\npełny zbiór punktów: ");
        for (SinglePoint p : randomPoints.getPoints()) System.out.println(p.toString());
    }
}
