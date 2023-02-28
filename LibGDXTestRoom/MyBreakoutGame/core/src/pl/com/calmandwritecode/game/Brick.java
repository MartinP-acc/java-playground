package pl.com.calmandwritecode.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import pl.com.calmandwritecode.GameAssets;

public class Brick extends Rectangle {

    public static final int STUCK_HIT_LIMIT = 50;
    public static int stuckCounter = 0;

    public boolean destroyed = false;
    public boolean destroyable = true;
    protected Sprite texture;

    private Vector2 bottomLeft, topLeft, bottomRight, topRight;
    private Sound metalHitSound, brickHitSound;

    private String boundSide;
    protected int pointsWorth;
    public Brick(float x, float y, Sprite texture){
        super(x,y,texture.getWidth(),texture.getHeight());
        this.texture = texture;
        this.pointsWorth = 10;
        initCornerVec2();
        setSounds();
    }

    private void initCornerVec2(){
        bottomLeft = new Vector2(x,y);
        bottomRight = new Vector2(x+width,y);
        topLeft = new Vector2(x,y+height);
        topRight = new Vector2(x+width, y+height );
    }

    private void setSounds(){
        GameAssets gameAssets = GameAssets.getInstance();
        brickHitSound = gameAssets.get(GameAssets.BRICK_HIT);
        metalHitSound = gameAssets.get(GameAssets.METAL_HIT);
    }


    public void draw(SpriteBatch batch){
        batch.draw(texture,x,y);
    }

    public void collision(Ball ball){
        bounce(ball);
        destroyed=true;
        stuckCounter = 0;
        ball.update();
    }
    public void collision(Array<Shot> shots, boolean isPowerBall){
        for (int i=0; i<shots.size; i++){
            Shot shot = shots.get(i);
            if (shot.overlaps(this)){
                playHitSound();

                if (destroyable || isPowerBall) destroyed = true;
                if (!isPowerBall) shots.removeIndex(i);
            }
        }
    }
    public boolean checkCollision(Ball ball){
        boolean collision = false;
        boundSide = "none";
        if (ball.xSpeed>0) {
            if (Intersector.intersectSegmentCircle(bottomLeft, topLeft, ball.position, ball.radius*ball.radius)) {
                boundSide = "left";
                collision = true;
            }
        }
        if (ball.xSpeed<0) {
            if (Intersector.intersectSegmentCircle(bottomRight, topRight, ball.position, ball.radius*ball.radius)) {
                boundSide = "right";
                collision = true;
            }
        }
        if (!collision || (ball.x>x && ball.x<x+width)){
            if (ball.ySpeed>0) {
                if (Intersector.intersectSegmentCircle(bottomRight, bottomLeft, ball.position, ball.radius*ball.radius)) {
                        boundSide = "bottom";
                        collision = true;
                }
            }else if (ball.ySpeed<0) {
                if (Intersector.intersectSegmentCircle(topRight, topLeft, ball.position, ball.radius*ball.radius)) {
                    boundSide = "top";
                    collision = true;
                }
            }
        }
        return collision;
    }


    protected void bounce(Ball ball){
        playHitSound();

        if (!ball.powerBall) {
            switch (boundSide) {
                case "left":
                    if (ball.xSpeed > 0) ball.xSpeed = -ball.xSpeed;
                    break;
                case "right":
                    if (ball.xSpeed < 0) ball.xSpeed = -ball.xSpeed;
                    break;
                case "top":
                    if (ball.ySpeed < 0) ball.ySpeed = -ball.ySpeed;
                    break;
                case "bottom":
                    if (ball.ySpeed > 0) ball.ySpeed = -ball.ySpeed;
                    break;
                default:
                    break;
            }
            handleBallStuck();
        }
    }

    private void playHitSound() {
        if (destroyable) brickHitSound.play();
        else metalHitSound.play();
    }

    private void handleBallStuck() {
        if (stuckCounter > STUCK_HIT_LIMIT) {
            destroyed = true;
            stuckCounter = 0;
        }
    }

    public int getPointsWorth() {
        return pointsWorth;
    }

    public float getSparksRotation(){
        if (boundSide.equals("left")) return 90;
        if (boundSide.equals("right")) return 270;
        if (boundSide.equals("bottom")) return 180;
        return 0;
    }
}
