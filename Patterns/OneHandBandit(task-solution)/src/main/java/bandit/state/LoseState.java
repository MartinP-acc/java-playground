package bandit.state;

import bandit.OneHandBandit;

import java.util.Random;

public class LoseState implements State{

    @Override
    public void insertCoin(OneHandBandit oneHandBandit) {
        System.out.println("Zobacz wynik i zagraj ponownie");
    }

    @Override
    public void pullLever(OneHandBandit oneHandBandit) {
        System.out.println("Zobacz wynik i zagraj ponownie");
    }

    @Override
    public void takeReward(OneHandBandit oneHandBandit) {
        System.out.println("Zobacz wynik i zagraj ponownie");
    }

    @Override
    public void showScore(OneHandBandit oneHandBandit) {
        System.out.println("wylosowałeś "+new Random().nextInt(8)+" wygrywa 8 i 9");
        oneHandBandit.state = new NoCoin();
    }
}
