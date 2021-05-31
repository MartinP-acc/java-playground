package meal;

public class RiceMeal extends Meal{

    @Override
    public void prepareMeal() {
        super.prepareMeal();
        System.out.print("Danie na bazie ryżu ");
    }
}
