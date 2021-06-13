import bandit.OneHandBandit;

public class Main {

    public static void main(String[] args) {

        OneHandBandit oneHandBandit = new OneHandBandit();

        for (int i=0; i<10; i++){
            oneHandBandit.insertCoin();
            oneHandBandit.pullLever();
            oneHandBandit.takeReward();
            oneHandBandit.showScore();
            System.out.println("---------------------------");
        }

        oneHandBandit.insertCoin();
        oneHandBandit.takeReward();
    }
}
