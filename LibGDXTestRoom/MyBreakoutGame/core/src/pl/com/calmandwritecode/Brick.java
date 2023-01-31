package pl.com.calmandwritecode;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Brick extends Rectangle {

    public boolean destroyed = false;
    public Vector2 center;
    protected Sprite texture;

    private Vector2 bottomLeft;
    private Vector2 topLeft;
    private Vector2 bottomRight;
    private Vector2 topRight;

    private String boundSide;

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
        Circle futureball = new Circle(ball.position,ball.radius);
        if (ball.xSpeed>0) {
            if (Intersector.intersectSegments(ball.position.x, ball.position.y, ball.futurePos.x, ball.futurePos.y,
                    bottomLeft.x, bottomLeft.y, topLeft.x, topLeft.y, intersectionPoint)) {
                boundSide = "left";
                nearestIntersection = intersectionPoint;
            }
        }
        if (ball.xSpeed<0) {
            if (Intersector.intersectSegments(ball.position.x, ball.position.y, ball.futurePos.x, ball.futurePos.y,
                    bottomRight.x, bottomRight.y, topRight.x, topRight.y, intersectionPoint)) {
                if (boundSide.equals("none") || nearestIntersection.dst(ball.position) < intersectionPoint.dst(ball.position)) {
                    boundSide = "right";
                    nearestIntersection = intersectionPoint;
                }
            }
        }
        if (boundSide.equals("none")){
            if (ball.ySpeed>0) {
                if (Intersector.intersectSegments(ball.position.x, ball.position.y, ball.futurePos.x, ball.futurePos.y,
                        bottomRight.x, bottomRight.y, bottomLeft.x, bottomLeft.y, intersectionPoint)) {
                    if (boundSide.equals("none") || nearestIntersection.dst(ball.position) < intersectionPoint.dst(ball.position)) {
                        boundSide = "bottom";
                        nearestIntersection = intersectionPoint;
                    }
                }
            }else if (ball.ySpeed<0) {
                if (Intersector.intersectSegments(ball.position.x, ball.position.y, ball.futurePos.x, ball.futurePos.y,
                        topRight.x, topRight.y, topLeft.x, topLeft.y, intersectionPoint)) {
                    if (boundSide.equals("none") || nearestIntersection.dst(ball.position) < intersectionPoint.dst(ball.position)) {
                        boundSide = "top";
                        nearestIntersection = intersectionPoint;
                    }
                }
            }
        }
        return !boundSide.equals("none") ? nearestIntersection : null;
    }


    protected void bounce(Ball ball){
        switch (boundSide){
            case "left":
                if (ball.xSpeed>0)ball.xSpeed = -ball.xSpeed;
                break;
            case "right":
                if (ball.xSpeed<0)ball.xSpeed = -ball.xSpeed;
                break;
            case "top":
                if (ball.ySpeed<0)ball.ySpeed = -ball.ySpeed;
                break;
            case "bottom":
                if (ball.ySpeed>0)ball.ySpeed = -ball.ySpeed;
                break;
            default:break;
        }
        ball.updateVectors();
        ball.playBounce();
    }
}
