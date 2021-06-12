package activities;

import activities.cal_calc.Visitor;

public class AirBike implements Activities {

    private int distance;

    public AirBike(int distance) {
        this.distance = distance;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.calcCalAirBike(this);
    }

    public int getDistance() {
        return distance;
    }
}
