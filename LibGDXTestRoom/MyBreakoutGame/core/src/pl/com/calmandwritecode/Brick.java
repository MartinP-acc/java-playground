package pl.com.calmandwritecode;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Brick extends Rectangle {

    public boolean destroyed = false;
    public Vector2 center;
    Texture texture;
    Vector2 cornerLB;
    Vector2 cornerLT;
    Vector2 cornerRB;
    Vector2 cornerRT;

    public Brick(float x, float y, Texture texture){
        this.x = x;
        this.y = y;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.texture = texture;
        center = new Vector2(x+width/2,y+height/2);
        cornerLB = new Vector2(x,y);
        cornerLT = new Vector2(x,y+height);
        cornerRT = new Vector2(x+width,y+height);
        cornerRB = new Vector2(x+width,y);
    }

    public void draw(SpriteBatch batch){
        batch.draw(texture,x,y);
    }

    public void collision(Ball ball){
        if (ball.y<y || ball.y>y+height) ball.ySpeed =-ball.ySpeed;
        if (ball.x>x+width && ball.xSpeed<0) ball.xSpeed =-ball.xSpeed;
        if (ball.x<x && ball.xSpeed>0) ball.xSpeed =-ball.xSpeed;
        destroyed=true;
        ball.update();
    }
}
