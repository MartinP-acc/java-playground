package engine;

import java.io.Serializable;

public class GameEngine implements Serializable {

    private static final long serialVersionID =34242455;

    private int hp = 100;
    private String charName="zenek";
    private static GameEngine instance = new GameEngine();

    public void run(){
        while (true){
            //dowolne pierdoły
        }
    }

    public static GameEngine getInstance(){
        return instance;
    }

    protected Object readResolve(){
        return getInstance();
    }
}
