package pl.com.calmandwritecode.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class BallsService {

    private final Array<Ball> balls;
    private final Sprite ballTexture;
    private final Sprite smallBallTexture;
    public BallsService(TextureAtlas atlas){
        balls = new Array<>();
        ballTexture = atlas.createSprite("ball");
        smallBallTexture = atlas.createSprite("small_ball");
        setOneBall();
    }

    public void setOneBall(){
        balls.clear();
        Ball ball = new Ball(ballTexture);
        balls.add(ball);
    }

    public void accelerateAll(){
        for (Ball ball : balls){
            ball.accelerateBall();
        }
    }

    public void slowDownAll(){
        for (Ball ball : balls){
            ball.slowDown();
        }
    }

    public void setPowerAll(){
        for (Ball ball : balls){
            ball.setPowerBall();
        }
    }

    public void shrinkAll(){
        for (Ball ball : balls){
            ball.changeTexture(smallBallTexture);
        }
    }

    public void drawBalls(SpriteBatch batch){
        for (Ball ball : balls){
            ball.draw(batch);
        }
    }

    public void stickTo(Paddle paddle){
        for (Ball ball : balls){
            ball.stickTo(paddle);
        }
    }

    public void serveAll(){
        for (Ball ball : balls){
            ball.serve();
        }
    }

    public void updateAll(Paddle paddle){
        for (int i=0; i<balls.size; i++){
            Ball ball = balls.get(i);
            if (ball.serveState){
                ball.stickTo(paddle);
            }else {
                ball.update();
                if (ball.isBelowScreen())
                    balls.removeIndex(i);
            }
        }
    }

    public boolean allBallsOutScreen(){
        return balls.isEmpty();
    }

    public void collisionPaddle(Paddle paddle){
        for (Ball ball : balls){
            paddle.collision(ball);
        }
    }

    public void collisionBrick(Brick brick){
        for (Ball ball : balls){
            if (brick.checkCollision(ball)){
                brick.collision(ball);
            }
        }
    }

    public boolean isPowerOn() {
        if (!balls.isEmpty()){
            return balls.get(0).powerBall;
        }
        return false;
    }

    public void cloneAll(){
        if (balls.size<10) {
            Array<Ball> cloneBalls = new Array<>();

            for (Ball ball : balls) {
                cloneBalls.add(Ball.cloneBall(ball));
            }

            balls.addAll(cloneBalls);
        }
    }

    public void releaseBalls() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)){
            for (Ball ball : balls){
                ball.serveState = false;
            }
        }
    }
}
