package meal;

abstract public class MealDecorator extends Meal{

    protected Meal meal;

    public MealDecorator(Meal meal) {
        this.meal = meal;
    }
}
