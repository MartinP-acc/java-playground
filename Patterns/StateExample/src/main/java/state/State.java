package state;

public interface State {

    void insertCoin(CoffeeMachine coffeeMachine);
    void makeCoffee(CoffeeMachine coffeeMachine);
    void takeCup(CoffeeMachine coffeeMachine);
    void takeChange(CoffeeMachine coffeeMachine);
}
