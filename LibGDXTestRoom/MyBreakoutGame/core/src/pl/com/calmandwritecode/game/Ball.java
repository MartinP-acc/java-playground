package pl.com.calmandwritecode.game;

import com.badlogic.gdx.Gdx;
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

    public static final long ACCELERATE_INTERVAL_TIME = 10000;
    public static final long POWER_BALL_LIMIT = 15000;
    public static final float PADDLE_TOP = 61;
    private static final float SPEED_UP = 1.5f;
    private static final float JUST_ACCELERATION = 1.1f;
    private static final float SLOW_DOWN = 0.5f;
    private Sound ballBounceSound, magneticSound;
    private Sound[] ricochet;
    private Sprite currentBallTexture;
    private Sparks sparks;
    private float velocity;
    private int ricochetIndex;

    public Vector2 position;
    public boolean serveState, powerBall;
    public float xSpeed, ySpeed, posOnPaddle = 60;

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
        clone.magneticSound = ball.magneticSound;
        clone.ricochet = ball.ricochet;
        clone.sparks = ball.sparks;
        clone.ricochetIndex = MathUtils.random(0,3);
        return clone;
    }

    private Ball(){}

    public Ball(Sprite currentBallTexture){
        GameAssets gameAssets = GameAssets.getInstance();
        this.currentBallTexture = currentBallTexture;
        xSpeed = 0;
        ySpeed = 0;
        ballBounceSound = gameAssets.get(GameAssets.BOUNCE_SOUND_FILE);
        magneticSound = gameAssets.get(GameAssets.MAGNETIC);
        ricochet = new Sound[]{
                gameAssets.get(GameAssets.RICOCHET_0),
                gameAssets.get(GameAssets.RICOCHET_1),
                gameAssets.get(GameAssets.RICOCHET_2),
                gameAssets.get(GameAssets.RICOCHET_3),
        };
        set(BreakoutGame.CENTER_X, PADDLE_TOP+currentBallTexture.getWidth()/2,currentBallTexture.getWidth()/2);
        position = new Vector2(x,y);
        sparks = new Sparks((TextureAtlas) gameAssets.get(GameAssets.ATLAS_FILE));
        ricochetIndex = MathUtils.random(0,3);
    }

    public void draw(SpriteBatch batch){
        if (powerBall) batch.setColor(0.9f,0.1f,0,1f);
        batch.draw(currentBallTexture,x-radius,y-radius);
        batch.setColor(Color.WHITE);
        sparks.draw(batch, Gdx.graphics.getDeltaTime());
    }


    public void update(){
        x+=xSpeed;
        y+=ySpeed;
        handleScreenBoundCollision();
        position.set(x,y);
    }

    private void handleScreenBoundCollision(){
        //LEFT SIDE of the screen
        if (x<=radius && xSpeed<0){
            xSpeed = -xSpeed;
            playRicochet();
            sparks.start(25,y,270);
        }
        //RIGHT SIDE of the screen
        if (x>=BreakoutGame.W_WIDTH-radius && xSpeed>0){
            xSpeed = -xSpeed;
            playRicochet();
            sparks.start(BreakoutGame.W_WIDTH-25,y,90);
        }
        //TOP of the screen
        if (y>=BreakoutGame.W_HEIGHT-radius && ySpeed>0){
            ySpeed = -ySpeed;
            playRicochet();
            sparks.start(x,BreakoutGame.W_HEIGHT-25,180);
        }
    }

    public void accelerateBall(boolean fullSpeed){
        float multiplier = fullSpeed ? SPEED_UP : JUST_ACCELERATION;
        if (velocity < radius) {
            xSpeed = xSpeed * multiplier;
            ySpeed = ySpeed * multiplier;
        }
        updateCurrentVelocity();
    }

    public void updateCurrentVelocity(){
        velocity = position.dst(x+xSpeed,y+ySpeed);
    }

    public void playBounce(){
        ballBounceSound.play();
    }



    public void playMagneticSound(){
        magneticSound.play();
    }

    public void playRicochet(){
        ricochet[ricochetIndex].play();
        ricochetIndex = MathUtils.random(0,3);
    }

    public void stickTo(Paddle paddle){
        x = paddle.x+ posOnPaddle;
        y = paddle.y+ paddle.height+radius+1;
    }

    public void serve() {
        xSpeed = MathUtils.random(-4,4);
        ySpeed = MathUtils.random(3,5);
    }

    public void changeTexture(Sprite newTexture){
        currentBallTexture = newTexture;
        radius = newTexture.getHeight()/2;
    }

    public void slowDown(){
        if (velocity>1){
            xSpeed = xSpeed * SLOW_DOWN;
            ySpeed = ySpeed * SLOW_DOWN;
        }
    }

    public void setPowerBall(){
        powerBall = true;
    }

    public boolean isBelowScreen(){
        return y<0;
    }


}
