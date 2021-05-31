package meal;

public class ShrimpMealDecorator extends MealDecorator{

    public ShrimpMealDecorator(Meal meal) {
        super(meal);
    }

    @Override
    public void prepareMeal() {
        meal.prepareMeal();
        addShrimp();
    }

    public void addShrimp(){
        System.out.print(" z krewetkami");
    }
}
