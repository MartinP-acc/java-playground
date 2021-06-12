package activities.cal_calc;
import activities.AirBike;
import activities.Gimnastic;
import activities.Weightlifting;

public class CalCalcVisitor implements Visitor{

    @Override
    public void calcCalWeightlifting(Weightlifting weightlifting) {
        System.out.println("spalone kalorie przy podnoszeniu ciężarów : "+(weightlifting.getWeight()*weightlifting.getReps()*0.2));
    }

    @Override
    public void calcCalAirBike(AirBike airBike) {
        System.out.println("spalone kalorie na AirBike :"+(airBike.getDistance()*0.05));
    }

    @Override
    public void calcCalGimnastic(Gimnastic gimnastic) {
        System.out.println("spalone kalorie podczas ćwiczeń gimnastycznych :"+(gimnastic.getReps()*2.2));
    }
}
