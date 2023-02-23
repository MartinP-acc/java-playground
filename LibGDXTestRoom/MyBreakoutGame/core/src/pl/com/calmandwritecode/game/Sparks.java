package pl.com.calmandwritecode.game;

import com.badlogic.gdx.graphics.g2d.*;
import pl.com.calmandwritecode.BreakoutGame;

public class Sparks {

    private Animation<TextureRegion> sparksAnimation;
    private float stateTime;
    private boolean isPlaying;
    float x,y;
    float frameDuration;
    float rotation;

    public Sparks(TextureAtlas atlas){
        frameDuration = 0.05f;
        sparksAnimation = new Animation<TextureRegion>(frameDuration,atlas.findRegions("sparks"), Animation.PlayMode.NORMAL);
        stateTime = 0;
        isPlaying = false;

    }

    public void start(float x, float y, float rotation){
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        isPlaying = true;
        stateTime = 0;
    }

    public void draw(SpriteBatch batch, float delta){
        if (isPlaying){
            stateTime += delta;
            TextureRegion frame = sparksAnimation.getKeyFrame(stateTime,false);
            batch.draw(frame, x-frame.getRegionWidth()/2,y,frame.getRegionWidth()/2,frame.getRegionHeight(),frame.getRegionWidth(),frame.getRegionHeight(),1,1,rotation);
            if (stateTime>frameDuration*3) isPlaying=false;
        }
    }
}
