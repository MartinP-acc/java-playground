package activities.cal_calc;

import activities.Activities;
import activities.AirBike;
import activities.Gimnastic;
import activities.Weightlifting;

public interface Visitor {

    void calcCalWeightlifting(Weightlifting weightlifting);
    void calcCalAirBike(AirBike airBike);
    void calcCalGimnastic(Gimnastic gimnastic);
}
