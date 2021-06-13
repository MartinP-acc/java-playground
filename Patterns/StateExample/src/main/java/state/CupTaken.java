package state;

public class CupTaken implements State{
    @Override
    public void insertCoin(CoffeeMachine coffeeMachine) {
        System.out.println("odbierz resztę");
    }

    @Override
    public void makeCoffee(CoffeeMachine coffeeMachine) {
        System.out.println("odbierz resztę");
    }

    @Override
    public void takeCup(CoffeeMachine coffeeMachine) {
        System.out.println("odbierz resztę");
    }

    @Override
    public void takeChange(CoffeeMachine coffeeMachine) {
        System.out.println("Dziekujemy - miłego dnia");
        coffeeMachine.setState(new NoCoinInserted());
    }

}
