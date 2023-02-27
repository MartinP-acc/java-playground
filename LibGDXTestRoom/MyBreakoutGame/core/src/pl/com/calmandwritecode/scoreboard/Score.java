package pl.com.calmandwritecode.scoreboard;

import com.badlogic.gdx.utils.TimeUtils;
public class Score {

    private String playerName;
    private long score;
    private int level;
    private long timeStamp;

    public Score(){}
    public Score(String playerName, long score, int level) {
        this.playerName = playerName.toUpperCase();
        this.score = score;
        this.level = level;
        this.timeStamp = TimeUtils.millis();
    }

    public String getPlayerName() {
        return playerName;
    }

    public long getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
