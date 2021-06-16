package game;

import static java.lang.Thread.sleep;

public class SaveGameFull implements SaveGame{

    private String name;
    private String gameData;

    @Override
    public void initialize() {
        this.name = "Save Game "+String.valueOf(System.currentTimeMillis());
        this.gameData = loadFromStorage();
    }

    @Override
    public void loadGame() {
        System.out.println("Game loaded.");
    }

    @Override
    public String getName() {
        return name;
    }

    public String loadFromStorage(){
        try{
            sleep(500);
        } catch (InterruptedException ex){
            ex.printStackTrace();
        }
        return name;
    }
}
