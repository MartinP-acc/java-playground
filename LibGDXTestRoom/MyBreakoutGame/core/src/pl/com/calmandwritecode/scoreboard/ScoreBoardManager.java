package pl.com.calmandwritecode.scoreboard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ScoreBoardManager {

    private final static String SCOREBOARD_PATH = "assets/scoreboard.json";
    private final Json json;

    public ScoreBoardManager() {
        this.json = new Json();
    }

    public ScoreBoard loadScoreBoard(){
        ScoreBoard scoreBoard;
        if (Gdx.files.internal(SCOREBOARD_PATH).exists()){
            FileHandle fileHandle = Gdx.files.internal(SCOREBOARD_PATH);
            scoreBoard = json.fromJson(ScoreBoard.class,fileHandle);
        }else {
            scoreBoard = new ScoreBoard();
        }
        return scoreBoard;
    }

    public void saveScoreBoard(ScoreBoard scoreBoard){
        File file = new File(SCOREBOARD_PATH);
        try (FileWriter fileWriter = new FileWriter(file)){
            json.setElementType(ScoreBoard.class,"scores", Score.class);
            json.toJson(scoreBoard,fileWriter);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
