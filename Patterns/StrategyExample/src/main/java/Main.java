public class Main {

    public static void main(String[] args) {

        Chef chef = new Chef("Zenek Kucharz");

        chef.setMeal(new CookSoup());
        chef.cook();


        chef.setMeal(new CookShoarma());
        chef.cook();


        chef.setMeal(new CookSteak());
        chef.cook();
    }
}
