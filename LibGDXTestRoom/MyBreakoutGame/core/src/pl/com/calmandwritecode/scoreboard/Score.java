package pl.com.calmandwritecode.scoreboard;

import com.badlogic.gdx.utils.TimeUtils;
public class Score {

    private final String playerName;
    private final long score;
    private final int level;
    private final long timeStamp;

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
