package engine;

import java.io.Serializable;

public enum GameEngineEnum implements Serializable {

    INSTANCE("zenek");

    private int hp = 100;
    private String charName="";

    private GameEngineEnum(String name){
        this.charName = name;
    }

    public void run(){
            System.out.println("uruchomiona");
    }
}
