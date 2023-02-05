package pl.com.calmandwritecode;

public class Level {

    private String levelTitle;
    private String brickMap;
    private float powerUpChance;

    public Level() {
    }

    public Level(String levelTitle, String brickMap, float powerUpChance) {
        this.levelTitle = levelTitle;
        this.brickMap = brickMap;
        this.powerUpChance = powerUpChance;
    }

    public String getLevelTitle() {
        return levelTitle;
    }

    public void setLevelTitle(String levelTitle) {
        this.levelTitle = levelTitle;
    }

    public String getBrickMap() {
        return brickMap;
    }

    public void setBrickMap(String brickMap) {
        this.brickMap = brickMap;
    }

    public float getPowerUpChance() {
        return powerUpChance;
    }

    public void setPowerUpChance(float powerUpChance) {
        this.powerUpChance = powerUpChance;
    }
}
