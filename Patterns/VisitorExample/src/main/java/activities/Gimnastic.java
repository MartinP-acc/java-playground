package activities;

import activities.cal_calc.Visitor;

public class Gimnastic implements Activities{

    private int reps;

    public Gimnastic(int reps) {
        this.reps = reps;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.calcCalGimnastic(this);
    }

    public int getReps() {
        return reps;
    }
}
