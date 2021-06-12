import activities.Activities;
import activities.AirBike;
import activities.Gimnastic;
import activities.Weightlifting;
import activities.cal_calc.CalCalcVisitor;
import activities.cal_calc.Visitor;

public class Main {

    public static void main(String[] args) {

        Visitor calc = new CalCalcVisitor();

        Activities bike = new AirBike(3000);
        Activities pushUps = new Gimnastic(150);
        Activities squats = new Weightlifting(50,60);

        bike.accept(calc);
        pushUps.accept(calc);
        squats.accept(calc);

    }
}
