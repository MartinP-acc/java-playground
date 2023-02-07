package pl.com.calmandwritecode;

public class PlayerData {

    private int lives;
    private long score;
    private int currentLevel;

    public PlayerData(){
        this.score = 0;
        this.lives = 3;
        this.currentLevel = 0;
    }

    public int getLives() {
        return lives;
    }

    public void decLife() {
        this.lives -= 1;
    }

    public void extraLife() {
        this.lives += 1;
    }

    public long getScore() {
        return score;
    }

    public void addToScore(long points) {
        this.score += points;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void nextLevel() {
        this.currentLevel += 1;
    }
}
