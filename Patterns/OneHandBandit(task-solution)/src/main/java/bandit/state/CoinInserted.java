package bandit.state;

import bandit.OneHandBandit;

import java.util.Random;

public class CoinInserted implements State{

    private Random score = new Random();

    @Override
    public void insertCoin(OneHandBandit oneHandBandit) {
        System.out.println("pociagnij za dzwignię");
    }

    @Override
    public void pullLever(OneHandBandit oneHandBandit) {
        if (score.nextInt(10)>7){
            System.out.println("Brawo ! wygrałeś - odbierz nagrodę");
            oneHandBandit.state = new WinState();
        }else{
            System.out.println("Niestety przegrałeś - zobacz ile brakło");
            oneHandBandit.state = new LoseState();
        }
    }

    @Override
    public void takeReward(OneHandBandit oneHandBandit) {
        System.out.println("jeszcze nic nie wygrałeś");
    }

    @Override
    public void showScore(OneHandBandit oneHandBandit) {
        System.out.println("jeszcze nie zagrałeś");
    }
}
