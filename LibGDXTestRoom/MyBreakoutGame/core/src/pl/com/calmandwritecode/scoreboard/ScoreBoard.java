package pl.com.calmandwritecode.scoreboard;

import java.util.ArrayList;
import java.util.Comparator;

public class ScoreBoard {

    private final ArrayList<Score> scores;

    public ScoreBoard() {
        this.scores = new ArrayList<>();
        for(int i=0; i<10; i++){
            scores.add(new Score("AAA",10*i,0));
        }
    }

    public boolean worthToSave(long scorePoints){
        return scores.size()<10 || getWorseScore()<=scorePoints;
    }

    private long getWorseScore() {
        long worseScore = Long.MAX_VALUE;
        for (Score score : scores){
            if (score.getScore()<worseScore){
                worseScore = score.getScore();
            }
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
            } else if (score.getScore()==worseScore.getScore() &&
                    score.getTimeStamp()<worseScore.getTimeStamp()){
                worseScore = score;
            }
        }
        scores.remove(worseScore);
    }

    public ArrayList<Score> getScores() {
        scores.sort(new ScoreComparator());
        return scores;
    }

    private static class ScoreComparator implements Comparator<Score>{
        @Override
        public int compare(Score o1, Score o2) {
            if (o1.getScore() == o2.getScore()){
                return Long.compare(o2.getTimeStamp(),o1.getTimeStamp());
            }
            return Long.compare(o2.getScore(),o1.getScore());
        }
    }
}
