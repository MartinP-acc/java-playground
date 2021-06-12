package activities;

import activities.cal_calc.Visitor;

public class Weightlifting implements Activities {

    private int reps;
    private int weight;

    public Weightlifting(int reps, int weight) {
        this.reps = reps;
        this.weight = weight;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.calcCalWeightlifting(this);
    }

    public int getReps() {
        return reps;
    }

    public int getWeight() {
        return weight;
    }
}
