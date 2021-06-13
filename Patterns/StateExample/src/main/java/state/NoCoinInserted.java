package state;

public class NoCoinInserted implements State{
    @Override
    public void insertCoin(CoffeeMachine coffeeMachine) {
        System.out.println("Wrzucono monetę - wybierz kawę");
        coffeeMachine.setState(new CoinInserted());
    }

    @Override
    public void makeCoffee(CoffeeMachine coffeeMachine) {
        System.out.println("Wrzuć monetę");
    }

    @Override
    public void takeCup(CoffeeMachine coffeeMachine) {
        System.out.println("Wrzuć monetę");
    }

    @Override
    public void takeChange(CoffeeMachine coffeeMachine) {
        System.out.println("Wrzuć monetę");
    }
}
