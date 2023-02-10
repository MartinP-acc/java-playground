package pl.com.calmandwritecode.scoreboard;

import java.util.ArrayList;
import java.util.Comparator;

public class ScoreBoard {

    private final ArrayList<Score> scores;

    public ScoreBoard() {
        this.scores = new ArrayList<>();
    }

    public boolean worthToSave(long scorePoints){
        return scores.size()<10 || getWorseScore()<=scorePoints;
    }

    private long getWorseScore() {
        long worseScore = 0;
        for (Score score : scores){
            worseScore = Math.min(score.getScore(),worseScore);
        }
        return worseScore;
    }

    public void addNewScore(Score score){
        scores.add(score);
        if (scores.size()>10){
            removeWorseOldestScore();
        }
    }

    private void removeWorseOldestScore() {
        Score worseScore = scores.get(0);
        for (Score score : scores){
            if (score.getScore()<worseScore.getScore()){
                worseScore = score;
            } else if (score.getScore()==worseScore.getScore()){
                worseScore = score.getTimeStamp()<worseScore.getTimeStamp() ? score : worseScore;
            }
        }
        scores.remove(worseScore);
    }

    public ArrayList<Score> getScores() {
        scores.sort(new ScoreComparator().reversed());
        return scores;
    }

    private static class ScoreComparator implements Comparator<Score>{
        @Override
        public int compare(Score o1, Score o2) {
            if (o1.getScore() == o2.getScore()){
                return Long.compare(o1.getTimeStamp(),o2.getTimeStamp());
            }
            return Long.compare(o1.getScore(),o2.getScore());
        }
    }
}
