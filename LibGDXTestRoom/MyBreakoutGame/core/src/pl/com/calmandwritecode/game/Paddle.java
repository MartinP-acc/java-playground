package pl.com.calmandwritecode.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import pl.com.calmandwritecode.BreakoutGame;

public class Paddle extends Rectangle {

    public static final float MAGNETIC_TIME_LIMIT = 25000;
    public static final int LASER_LIMIT = 40;
    private final TextureAtlas atlas;
    private final Sprite laserGunSprite;
    private Sprite paddleTexture;
    private boolean magnetic;
    private int laserShots;
    private final Sparks sparks;
    private long magneticStartTime;


    public Paddle(TextureAtlas atlas){
        magnetic = false;
        this.atlas = atlas;
        paddleTexture = atlas.createSprite("paddle120");
        laserGunSprite = atlas.createSprite("laser_gun");
        x = BreakoutGame.CENTER_X;
        y = 40;
        width = paddleTexture.getWidth();
        height = paddleTexture.getHeight();
        laserShots = 0;
        sparks = new Sparks(atlas);
    }

    public void draw(SpriteBatch batch){
        if (isMagnetic()) batch.setColor(Color.SKY);
        batch.draw(paddleTexture,x,y);
        batch.setColor(Color.WHITE);
        if (laserShots > 0) {
            batch.draw(laserGunSprite, getLaser1X(), y);
            batch.draw(laserGunSprite, getLaser2X(), y);
        }
        sparks.draw(batch,Gdx.graphics.getDeltaTime());
    }

    public void update(){
        x=Gdx.input.getX()-width/2;
        if (x> BreakoutGame.W_WIDTH -width) x= BreakoutGame.W_WIDTH-width;
        if (x<0) x=0;
    }

    public void collision(Ball ball) {

        Vector2 start = new Vector2(x, y + height);
        Vector2 end = new Vector2(x + width, y + height);
        Vector2 center = new Vector2(ball.x, ball.y);
        boolean wasCollision = false;

        if (Intersector.intersectSegmentCircle(start, end, center, ball.radius*ball.radius)||
                Intersector.intersectSegmentCircle(new Vector2(x,y), new Vector2(x+width,y), center, ball.radius*ball.radius)) {
            if (ball.ySpeed<0)ball.ySpeed = -ball.ySpeed;
            ball.y += ball.ySpeed;
            if (ball.x < x + width/4) {
                ball.xSpeed -= 1;
                if (ball.x < x + width/8) ball.xSpeed -= 1;
            } else if (ball.x > x + width - width/4) {
                ball.xSpeed += 1;
                if (ball.x < x + width - width/8) ball.xSpeed -= 1;
            }
            wasCollision = true;
            sparks.start(ball.x,60,0);
            ball.updateCurrentVelocity();
        }

        if (Intersector.intersectSegmentCircle(new Vector2(x, y), new Vector2(x, y + height), center, ball.radius*ball.radius)){
            ball.x = x-ball.radius-5;
            ball.xSpeed = -5;
            wasCollision = true;
        }else
        if (Intersector.intersectSegmentCircle(new Vector2(x + width, y), new Vector2(x + width, y + height), center, ball.radius*ball.radius)) {
            ball.x = x+width+ball.radius+5;
            ball.xSpeed = 5;
            wasCollision = true;
        }

        if (wasCollision && magnetic){
            ball.playMagneticSound();
            ball.serveState = true;
            ball.posOnPaddle = ball.x - x;
            if (ball.posOnPaddle>width) ball.posOnPaddle = width;
            else if (ball.posOnPaddle<0) ball.posOnPaddle = 0;
        }
        else if (wasCollision)ball.playBounce();
    }

    public void enlarge() {
        if (width<120) paddleTexture = atlas.createSprite("paddle120");
        else if (width<180) paddleTexture = atlas.createSprite("paddle180");
        else if (width<240) paddleTexture = atlas.createSprite("paddle240");
        width = paddleTexture.getWidth();
    }

    public void shrink(){
        if (width>180) paddleTexture = atlas.createSprite("paddle180");
        else if (width>120) paddleTexture = atlas.createSprite("paddle120");
        else if (width>60) paddleTexture = atlas.createSprite("paddle60");
        width = paddleTexture.getWidth();
    }

    public void reset(){
        paddleTexture = atlas.createSprite("paddle120");
        width = paddleTexture.getWidth();
        laserShots = 0;
        magnetic = false;
    }

    public void loadLasers(){
        if (laserShots<31)
            laserShots +=10;
        else
            laserShots = LASER_LIMIT;
    }

    public void setPaddleStick(){
        magneticStartTime = TimeUtils.millis();
        magnetic = true;
    }

    public boolean isMagnetic() {
        if (TimeUtils.timeSinceMillis(magneticStartTime)>MAGNETIC_TIME_LIMIT) magnetic = false;
        return magnetic;
    }

    public boolean lasersActive(){
        return laserShots > 0;
    }

    public float getLaser1X(){
        return x - laserGunSprite.getWidth();
    }

    public float getLaser2X(){
        return x + width;
    }

    public void removeOneShot(){
        laserShots-=1;
    }

    public int getLaserShots() {
        return laserShots;
    }

    public float getMagneticRemain(){
        return MAGNETIC_TIME_LIMIT - TimeUtils.timeSinceMillis(magneticStartTime);
    }
}
