import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

public enum GuessGame implements Serializable {

    GUESS_GAME;

    private int score = 0;

    public void play(){
        System.out.println("zgadnij 10 liczb z zakresu 0-9");
        for (int round = 1; round<=10; round++){
            System.out.print("zgadnij "+round+". liczbę :");
            int playerNumb = new Scanner(System.in).nextInt();
            if (playerNumb==(new Random().nextInt(9))){
                System.out.println("ZGADŁEŚ");
                score++;
            } else {
                System.out.println("TO NIE TA LICZBA");
            }
        }
        System.out.println("Twój wynik to : "+score+" punktów");
    }

    public int getScore() {
        return score;
    }
}
