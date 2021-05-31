package meal;

public class PotatoMeal extends Meal{

    @Override
    public void prepareMeal() {
        super.prepareMeal();
        System.out.print("Danie na bazie ziemniaków ");
    }
}
