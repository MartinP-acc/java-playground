import engine.GameEngine;
import engine.GameEngineEnum;

public class Main {

    public static void main(String[] args) {

        GameEngine engine = GameEngine.getInstance();

        GameEngine engine1 = GameEngine.getInstance();

        System.out.println(engine==engine1);

        // sposób enum

        GameEngineEnum game = GameEngineEnum.INSTANCE;
        game.run();
    }
}
