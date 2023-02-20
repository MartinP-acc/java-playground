package pl.com.calmandwritecode.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import pl.com.calmandwritecode.BreakoutGame;
import pl.com.calmandwritecode.GameAssets;

public class Ball extends Circle {

    private Sound ballBounceSound;
    private Sprite currentBallTexture;
    private float velocity;

    public Vector2 position;
    public boolean serveState;
    public boolean powerBall;
    public float xSpeed;
    public float ySpeed;
    public float posOnPaddle;

    public static Ball cloneBall(Ball ball){
        Ball clone = new Ball();
        clone.currentBallTexture = ball.currentBallTexture;
        clone.x = ball.x;
        clone.y = ball.y;
        clone.radius = ball.radius;
        clone.xSpeed = -ball.xSpeed;
        clone.ySpeed = ball.ySpeed;
        clone.powerBall = ball.powerBall;
        clone.position = new Vector2(clone.x,clone.y);
        clone.ballBounceSound = ball.ballBounceSound;
        return clone;
    }

    private Ball(){}

    public Ball(Sprite currentBallTexture){
        GameAssets gameAssets = GameAssets.getInstance();
        this.currentBallTexture = currentBallTexture;
        xSpeed = 0;
        ySpeed = 0;
        posOnPaddle = 60;
        ballBounceSound = gameAssets.get(GameAssets.BOUNCE_SOUND_FILE);
        set(BreakoutGame.CENTER_X,61+currentBallTexture.getWidth()/2,currentBallTexture.getWidth()/2);
        position = new Vector2(x,y);
    }

    public void draw(SpriteBatch batch){
        batch.draw(currentBallTexture,x-radius,y-radius);
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
        position.set(x,y);
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

    public void stickTo(Paddle paddle){
        x = paddle.x+ posOnPaddle;
        y = paddle.y+ paddle.height+radius+1;
    }

    public void serve() {
        xSpeed = MathUtils.random(-4,4);
        ySpeed = MathUtils.random(2,4);
    }

    public void changeTexture(Sprite newTexture){
        currentBallTexture = newTexture;
        radius = newTexture.getHeight()/2;
    }

    public void slowDown(){
        if (velocity>1){
            xSpeed = xSpeed * 0.5f;
            ySpeed = ySpeed * 0.5f;
        }
    }

    public void setPowerBall(){
        powerBall = true;
        currentBallTexture.setColor(1,0,0,0.5f);
    }

    public boolean isBelowScreen(){
        return y<0;
    }
}
