package points;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomPoints {

    private Set<SinglePoint> points;
    private int nrOfPoints;
    private Random random = new Random();

    public RandomPoints(int nrOfPoints) {
        this.nrOfPoints = nrOfPoints;
        points = new HashSet<SinglePoint>();
    }

    public void addNewRandomPoints(){
        for (int i=0; i<nrOfPoints; i++){
            points.add(new SinglePoint(random.nextInt(100), random.nextInt(100)));
        }
    }

    public Set<SinglePoint> getPoints() {
        return points;
    }

    public void setNrOfPoints(int nrOfPoints) {
        this.nrOfPoints = nrOfPoints;
    }

    public int getNrOfPoints() {
        return nrOfPoints;
    }
}
