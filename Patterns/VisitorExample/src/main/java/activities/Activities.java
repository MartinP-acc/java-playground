package activities;

import activities.cal_calc.Visitor;

public interface Activities{

    void accept(Visitor visitor);
}
