package bandit;

import bandit.state.NoCoin;
import bandit.state.State;

public class OneHandBandit {

    public State state = new NoCoin();

    public void insertCoin(){
        state.insertCoin(this);
    }
    public void pullLever(){
        state.pullLever(this);
    }
    public void takeReward(){
        state.takeReward(this);
    }
    public void showScore(){
        state.showScore(this);
    }
}
