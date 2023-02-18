package pl.com.calmandwritecode.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import pl.com.calmandwritecode.BreakoutGame;
import pl.com.calmandwritecode.GameAssets;

public class Ball extends Circle {

    private final TextureAtlas atlas;
    private Sprite ballTexture;
    public float xSpeed;
    public float ySpeed;
    public Vector2 position;
    public Vector2 futurePos;
    private  final Sound ballBounceSound;
    private float velocity;
    public boolean powerBall;

    public Ball(TextureAtlas atlas){
        this.atlas = atlas;
        GameAssets gameAssets = GameAssets.getInstance();
        ballTexture = atlas.createSprite("ball");
        stop();
        position = new Vector2(x,y);
        futurePos = new Vector2(x+xSpeed*10,y+ySpeed*10);
        ballBounceSound = gameAssets.get(GameAssets.BOUNCE_SOUND_FILE);
        set(BreakoutGame.CENTER_X,BreakoutGame.CENTER_Y-200,ballTexture.getWidth()/2);
    }

    public void draw(SpriteBatch batch){
        batch.draw(ballTexture,x-radius,y-radius);
    }

    public void update(){
        x+=xSpeed;
        y+=ySpeed;
        if (x<=radius && xSpeed<0){
            xSpeed = -xSpeed;
            playBounce();
        }
        if (x>=BreakoutGame.W_WIDTH-radius && xSpeed>0){
            xSpeed = -xSpeed;
            playBounce();
        }
        if (y>=BreakoutGame.W_HEIGHT-radius && ySpeed>0){
            ySpeed = -ySpeed;
            playBounce();
        }
        updateVectors();
    }

    public void updateVectors(){
        position.set(x,y);
        float xs = Math.abs(xSpeed);
        float ys = Math.abs(ySpeed);
        float multiplier = xs>ys ? 10/xs : 10/ys;
        futurePos.set(x+xSpeed*multiplier,y+ySpeed*multiplier);
    }

    public void accelerateBall(){
        if (velocity < radius) {
            xSpeed = xSpeed * 1.1f;
            ySpeed = ySpeed * 1.1f;
        }
        updateCurrentVelocity();
    }

    public void updateCurrentVelocity(){
        velocity = position.dst(x+xSpeed,y+ySpeed);
    }

    public void playBounce(){
        ballBounceSound.play();
    }

    public void stop(){
        xSpeed = 0;
        ySpeed = 0;
    }

    public void stickTo(Paddle paddle){
        x = paddle.x+ paddle.width/2;
        y = paddle.y+ paddle.height+radius+1;
    }

    public void serve() {
        xSpeed = MathUtils.random(-4,4);
        ySpeed = MathUtils.random(2,4);
    }

    public void shrink(){
        ballTexture = atlas.createSprite("small_ball");
        radius = ballTexture.getHeight()/2;
    }

    public void reset(){
        ballTexture = atlas.createSprite("ball");
        radius = ballTexture.getHeight()/2;
        powerBall = false;
        ballTexture.setColor(Color.WHITE);
    }

    public void slowDown(){
        if (velocity>1){
            xSpeed = xSpeed * 0.7f;
            ySpeed = ySpeed * 0.7f;
        }
    }

    public void setPowerBall(){
        powerBall = true;
        ballTexture.setColor(1,0,0,0.5f);
    }
}
