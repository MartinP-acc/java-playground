package state;

public class CoinInserted implements State{

    @Override
    public void insertCoin(CoffeeMachine coffeeMachine) {
        System.out.println("Naciśnij przycisk - nalej kawę");
    }

    @Override
    public void makeCoffee(CoffeeMachine coffeeMachine) {
        System.out.println("Nalewam kawę");
        coffeeMachine.setState(new CupIsFull());
    }

    @Override
    public void takeCup(CoffeeMachine coffeeMachine) {
        System.out.println("Nie nalano kawy");
    }

    @Override
    public void takeChange(CoffeeMachine coffeeMachine) {
        System.out.println("Nie nalano kawy");
    }
}
