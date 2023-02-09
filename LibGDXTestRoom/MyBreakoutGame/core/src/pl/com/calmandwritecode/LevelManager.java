package pl.com.calmandwritecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class LevelManager {

    private static final String LEVEL_PATH = "assets/levels/";
    private final Array<Level> levelList;
    private final Json json;
    private String message;

    public LevelManager(){
        this.levelList = new Array<>();
        this.json = new Json();
    }

    public boolean saveLevel(Level level){

        String fileWithPath = LEVEL_PATH+level.getLevelTitle()+".json";

        if (Gdx.files.internal(fileWithPath).exists()){
            message = "level title already exists";
            return false;
        }

        File file = new File(fileWithPath);
        try (FileWriter fileWriter = new FileWriter(file)){
            json.toJson(level,fileWriter);
        }catch (IOException exception){
            message = exception.toString();
            return false;
        }

        message = "level saved";
        return true;
    }

    public void loadLevelList(){
        FileHandle[] files = Gdx.files.internal(LEVEL_PATH).list();
        for (FileHandle file : files){
            Level level = json.fromJson(Level.class,file);
            levelList.add(level);
        }
    }

    public Level getLevel(int levelIndex){
        return levelList.get(levelIndex);
    }

    public String getMessage(){
        return message;
    }

    public boolean isLevelOnList(int index){
        return levelList.size > index;
    }
}
