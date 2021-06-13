package bandit.state;

import bandit.OneHandBandit;

import java.util.Random;

public class WinState implements State{

    @Override
    public void insertCoin(OneHandBandit oneHandBandit) {
        System.out.println("odbierz wygraną");
    }

    @Override
    public void pullLever(OneHandBandit oneHandBandit) {
        System.out.println("odbierz wygraną");
    }

    @Override
    public void takeReward(OneHandBandit oneHandBandit) {
        System.out.println("odebrano "+new Random().nextInt(5000));
        oneHandBandit.state = new NoCoin();
    }

    @Override
    public void showScore(OneHandBandit oneHandBandit) {
        System.out.println("odbierz nagrodę");
    }
}
