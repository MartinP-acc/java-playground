package game;

public class SaveGameProxy implements SaveGame{

    private SaveGame saveGameFull;
    private String name;

    @Override
    public void initialize() {
        this.name = "Save Game "+String.valueOf(System.currentTimeMillis());

    }

    @Override
    public void loadGame() {
        saveGameFull = new SaveGameFull();
        saveGameFull.initialize();
        saveGameFull.loadGame();
    }

    @Override
    public String getName() {
        return this.saveGameFull.getName();
    }
}
