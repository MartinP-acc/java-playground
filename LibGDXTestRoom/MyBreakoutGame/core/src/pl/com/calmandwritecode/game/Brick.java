package pl.com.calmandwritecode.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Brick extends Rectangle {

    public boolean destroyed = false;
    public boolean destroyable = true;
    public Vector2 center;
    protected Sprite texture;

    private final Vector2 bottomLeft;
    private final Vector2 topLeft;
    private final Vector2 bottomRight;
    private final Vector2 topRight;

    private String boundSide;
    private Vector2 lastIntersection;
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
        ball.update();
    }

    public Vector2 ballIntersect(Ball ball){
        boundSide = "none";
        Vector2 intersectionPoint = new Vector2();
        Vector2 nearestIntersection = new Vector2();
        if (ball.xSpeed>0) {
            if (Intersector.intersectSegments(ball.position.x, ball.position.y, ball.futurePos.x, ball.futurePos.y,
                    bottomLeft.x-ball.radius,
                    bottomLeft.y-ball.radius,
                    topLeft.x-ball.radius,
                    topLeft.y+ball.radius, intersectionPoint)) {
                boundSide = "left";
                nearestIntersection = intersectionPoint;
            }
        }
        if (ball.xSpeed<0) {
            if (Intersector.intersectSegments(ball.position.x, ball.position.y, ball.futurePos.x, ball.futurePos.y,
                    bottomRight.x+ball.radius,
                    bottomRight.y-ball.radius,
                    topRight.x+ball.radius,
                    topRight.y+ball.radius, intersectionPoint)) {
                if (boundSide.equals("none") || nearestIntersection.dst(ball.position) < intersectionPoint.dst(ball.position)) {
                    boundSide = "right";
                    nearestIntersection = intersectionPoint;
                }
            }
        }
        if (boundSide.equals("none")){
            if (ball.ySpeed>0) {
                if (Intersector.intersectSegments(ball.position.x, ball.position.y, ball.futurePos.x, ball.futurePos.y,
                        bottomRight.x+ball.radius,
                        bottomRight.y-ball.radius,
                        bottomLeft.x-ball.radius,
                        bottomLeft.y-ball.radius, intersectionPoint)) {
                    if (boundSide.equals("none") || nearestIntersection.dst(ball.position) < intersectionPoint.dst(ball.position)) {
                        boundSide = "bottom";
                        nearestIntersection = intersectionPoint;
                    }
                }
            }else if (ball.ySpeed<0) {
                if (Intersector.intersectSegments(ball.position.x, ball.position.y, ball.futurePos.x, ball.futurePos.y,
                        topRight.x+ball.radius,
                        topRight.y+ball.radius,
                        topLeft.x-ball.radius,
                        topLeft.y+ball.radius, intersectionPoint)) {
                    if (boundSide.equals("none") || nearestIntersection.dst(ball.position) < intersectionPoint.dst(ball.position)) {
                        boundSide = "top";
                        nearestIntersection = intersectionPoint;
                    }
                }
            }
        }
        lastIntersection = nearestIntersection;
        return !boundSide.equals("none") ? nearestIntersection : null;
    }


    protected void bounce(Ball ball){
        switch (boundSide){
            case "left":
                if (ball.xSpeed>0)ball.xSpeed = -ball.xSpeed;
                ball.x = lastIntersection.x-ball.radius;
                ball.y = lastIntersection.y;
                break;
            case "right":
                if (ball.xSpeed<0)ball.xSpeed = -ball.xSpeed;
                ball.x = lastIntersection.x+ball.radius;
                ball.y = lastIntersection.y;
                break;
            case "top":
                if (ball.ySpeed<0)ball.ySpeed = -ball.ySpeed;
                ball.y = lastIntersection.y+ball.radius;
                ball.x = lastIntersection.x;
                break;
            case "bottom":
                if (ball.ySpeed>0)ball.ySpeed = -ball.ySpeed;
                ball.y = lastIntersection.y-ball.radius;
                ball.x = lastIntersection.x;
                break;
            default:break;
        }
        ball.updateVectors();
        ball.playBounce();
    }

    public int getPointsWorth() {
        return pointsWorth;
    }
}
