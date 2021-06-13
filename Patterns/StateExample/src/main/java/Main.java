import state.CoffeeMachine;

public class Main {

    public static void main(String[] args) {

        CoffeeMachine cm = new CoffeeMachine();

        cm.insertCoin();
        cm.makeCoffee();
        cm.takeCup();
        cm.takeChange();
        System.out.println("-------------------------------------");
        cm.makeCoffee();
        System.out.println("-------------------------------------");
        cm.insertCoin();
        cm.takeCup();
        System.out.println("-------------------------------------");
        cm.makeCoffee();
        cm.takeChange();
    }
}
