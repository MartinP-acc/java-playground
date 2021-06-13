package state;

public class CupIsFull implements State{
    @Override
    public void insertCoin(CoffeeMachine coffeeMachine) {
        System.out.println("zapłacono - odbierz kawę");
    }

    @Override
    public void makeCoffee(CoffeeMachine coffeeMachine) {
        System.out.println("Kubek pełny - zabierz swoją kawę");
    }

    @Override
    public void takeCup(CoffeeMachine coffeeMachine) {
        System.out.println("Dziękujemy - pamiętaj zabrać resztę");
        coffeeMachine.setState(new CupTaken());
    }

    @Override
    public void takeChange(CoffeeMachine coffeeMachine) {
        System.out.println("Najpierw zabierz swoją kawę");
    }
}
