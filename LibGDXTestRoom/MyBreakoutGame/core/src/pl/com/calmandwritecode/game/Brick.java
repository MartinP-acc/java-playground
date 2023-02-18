package pl.com.calmandwritecode.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Brick extends Rectangle {

    public static int stuckCounter = 0;
    public boolean destroyed = false;
    public boolean destroyable = true;
    public Vector2 center;
    protected Sprite texture;

    private final Vector2 bottomLeft;
    private final Vector2 topLeft;
    private final Vector2 bottomRight;
    private final Vector2 topRight;

    private String boundSide;
    protected int pointsWorth;


    public Brick(float x, float y, Sprite texture){
        this.x = x;
        this.y = y;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.texture = texture;
        center = new Vector2(x+width/2,y+height/2);

        bottomLeft = new Vector2(x,y);
        bottomRight = new Vector2(x+width,y);
        topLeft = new Vector2(x,y+height);
        topRight = new Vector2(x+width, y+height );
        pointsWorth = 10;
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
        if (!collision){
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
        ball.playBounce();
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
            ball.updateVectors();
            if (stuckCounter > 40){
                destroyed = true;
                stuckCounter = 0;
            }
        }
    }

    public int getPointsWorth() {
        return pointsWorth;
    }


}
