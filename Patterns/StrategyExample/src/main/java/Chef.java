public class Chef {

    private String name;
    private CookStrategy meal;

    public Chef(String name) {
        this.name = name;
    }

    public void cook(){
        meal.prepareMeal();
    }

    public void setMeal(CookStrategy meal) {
        this.meal = meal;
    }
}
