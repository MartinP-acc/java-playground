package bandit.state;

import bandit.OneHandBandit;

public interface State {

    void insertCoin(OneHandBandit oneHandBandit);
    void pullLever(OneHandBandit oneHandBandit);
    void takeReward(OneHandBandit oneHandBandit);
    void showScore(OneHandBandit oneHandBandit);
}
