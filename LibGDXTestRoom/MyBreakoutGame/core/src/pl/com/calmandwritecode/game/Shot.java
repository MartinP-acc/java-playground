package pl.com.calmandwritecode.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import pl.com.calmandwritecode.BreakoutGame;

public class Shot extends Rectangle {

    private final static int VELOCITY = 5;
    private final Sprite laserShot;

    public Shot(float x, TextureAtlas atlas){
        laserShot = atlas.createSprite("laser_shot");
        height = laserShot.getHeight();
        width = laserShot.getWidth();
        this.x = x;
        this.y = 60;
    }

    public void draw(SpriteBatch batch){
        batch.draw(laserShot,x,y);
    }

    public void update(){
        y+=VELOCITY;
    }

    public boolean isOutOfScreen(){
        return y > BreakoutGame.W_HEIGHT;
    }
}
