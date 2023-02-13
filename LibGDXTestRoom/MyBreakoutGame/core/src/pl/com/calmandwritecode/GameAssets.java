package pl.com.calmandwritecode;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class GameAssets {

    public static final String ATLAS_FILE = "breakout-tx.atlas";
    public static final String BACKGROUND_FILE = "puzzle.png";
    public static final String TF_BG_FILE = "text_field_bg.png";
    public static final String BOUNCE_SOUND_FILE = "ball_bounce.ogg";

    private static GameAssets instance;
    private final AssetManager assetManager;

    private GameAssets(){
        this.assetManager = new AssetManager();
    }

    public void loadAssets(){
        assetManager.load(BOUNCE_SOUND_FILE, Sound.class);
        assetManager.load(ATLAS_FILE, TextureAtlas.class);
        assetManager.load(BACKGROUND_FILE, Texture.class);
        assetManager.load(TF_BG_FILE, Texture.class);
    }

    public boolean isUpdated(){
        return assetManager.update();
    }

    public <T> T get(String GameAsset){
        return assetManager.get(GameAsset);
    }

    public void dispose(){
        assetManager.dispose();
    }

    public float loadProgress(){

        return assetManager.getProgress();
    }

    public static GameAssets getInstance() {
        if (instance == null) instance = new GameAssets();
        return instance;
    }
}
