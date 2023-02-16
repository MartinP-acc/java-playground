package pl.com.calmandwritecode.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import pl.com.calmandwritecode.BreakoutGame;
import pl.com.calmandwritecode.GameAssets;

public class Ball extends Circle {

    private final Sprite ballTexture;
    public float xSpeed;
    public float ySpeed;
    public Vector2 position;
    public Vector2 futurePos;
    private  final Sound ballBounceSound;

    public Ball(TextureAtlas atlas){
        GameAssets gameAssets = GameAssets.getInstance();

        set(BreakoutGame.CENTER_X,BreakoutGame.CENTER_Y-200,11);
        ballTexture = atlas.createSprite("ball");
        stop();
        position = new Vector2(x,y);
        futurePos = new Vector2(x+xSpeed*10,y+ySpeed*10);
        ballBounceSound = gameAssets.get(GameAssets.BOUNCE_SOUND_FILE);
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
        if (Math.abs(xSpeed)<10 && Math.abs(ySpeed)<10) {
            xSpeed = xSpeed * 1.1f;
            ySpeed = ySpeed * 1.1f;
        }
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
}
