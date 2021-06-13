package state;

public class CoffeeMachine {

    private State state = new NoCoinInserted();

    public void insertCoin(){
        state.insertCoin(this);
    }

    public void makeCoffee(){
        state.makeCoffee(this);
    }

    public void takeCup(){
        state.takeCup(this);
    }

    public void takeChange(){
        state.takeChange(this);
    }

    public void setState(State state) {
        this.state = state;
    }
}
