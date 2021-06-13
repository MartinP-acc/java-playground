package bandit.state;

import bandit.OneHandBandit;

public class NoCoin implements State{

    @Override
    public void insertCoin(OneHandBandit oneHandBandit) {
        System.out.println("wrzuć monetę");
        oneHandBandit.state = new CoinInserted();
    }

    @Override
    public void pullLever(OneHandBandit oneHandBandit) {
        System.out.println("wrzuć monetę");
    }

    @Override
    public void takeReward(OneHandBandit oneHandBandit) {
        System.out.println("wrzuć monetę");
    }

    @Override
    public void showScore(OneHandBandit oneHandBandit) {
        System.out.println("wrzuć monetę");
    }
}
