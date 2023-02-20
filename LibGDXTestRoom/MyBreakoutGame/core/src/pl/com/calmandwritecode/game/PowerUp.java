package pl.com.calmandwritecode.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class PowerUp extends Rectangle {

    public static final int ENLARGE_PADDLE = 1;
    public static final int SHRINK_PADDLE = 2;
    public static final int SHRINK_BALL = 3;
    public static final int STICK_PADDLE = 4;
    public static final int LASERS = 5;
    public static final int SPEED_UP = 6;
    public static final int SLOW_DOWN = 7;
    public static final int CLONE_BALL = 8;
    public static final int POWER_BALL = 9;
    public static final int LOSE_BALL = 10;
    public static final int EXTRA_BALL = 11;

    private final int gravity;
    private int powerType;
    private Sprite sprite;

    public PowerUp(float x, float y, TextureAtlas atlas){
        gravity = MathUtils.random(2,6);
        powerType = MathUtils.random(1,20);
        if (powerType>11) powerType-=11;

        switch (powerType){
            case ENLARGE_PADDLE : sprite = atlas.createSprite("enlarge_paddle"); break;
            case SHRINK_PADDLE : sprite = atlas.createSprite("shrink_paddle"); break;
            case SHRINK_BALL : sprite = atlas.createSprite("shrink_ball"); break;
            case STICK_PADDLE : sprite = atlas.createSprite("stick_paddle"); break;
            case LASERS : sprite = atlas.createSprite("lasers"); break;
            case SPEED_UP : sprite = atlas.createSprite("speed_up"); break;
            case SLOW_DOWN : sprite = atlas.createSprite("slow_down"); break;
            case CLONE_BALL : sprite = atlas.createSprite("clone_ball"); break;
            case POWER_BALL : sprite = atlas.createSprite("power_ball"); break;
            case LOSE_BALL : sprite = atlas.createSprite("lose_ball"); break;
            case EXTRA_BALL : sprite = atlas.createSprite("extra_ball"); break;
        }

        if (powerType<LOSE_BALL){
            int randomInt = MathUtils.random(1,4);
            if (randomInt == 1) sprite = atlas.createSprite("random_bonus");
        }

        if (sprite != null) set(x,y,sprite.getWidth(),sprite.getHeight());
    }

    public int getPowerType() {
        return powerType;
    }

    public void draw(SpriteBatch batch){
        batch.draw(sprite,x,y);
    }

    public void update(){
        y-=gravity;
    }

    public long getWorthPoint(){
        return 50;
    }

}
