public class App {
    public static void main(String[] args) {
        GuessGame game = GuessGame.GUESS_GAME;

        game.play();

        int score = game.getScore();

        GuessGame anotherGameReference = GuessGame.GUESS_GAME;

        if(game == anotherGameReference  ) {
            System.out.println("Singleton!");
            if(score == anotherGameReference.getScore()) {
                System.out.println("I punkty się zgadzają!");
            }
        }
    }

}
