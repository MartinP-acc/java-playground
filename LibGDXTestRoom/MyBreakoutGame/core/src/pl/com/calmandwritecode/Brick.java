package pl.com.calmandwritecode;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Brick extends Rectangle {

    public boolean destroyed = false;
    public Vector2 center;
    protected Sprite texture;

    public Brick(float x, float y, Sprite texture){
        this.x = x;
        this.y = y;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.texture = texture;
        center = new Vector2(x+width/2,y+height/2);
    }

    public void draw(SpriteBatch batch){
        batch.draw(texture,x,y);
    }

    public void collision(Ball ball){
        bounce(ball);
        destroyed=true;
        ball.update();
    }

    protected void bounce(Ball ball){
        if (ball.y<y || ball.y>y+height) ball.ySpeed =-ball.ySpeed;
        else
        if (ball.x+ball.radius>x+width && ball.xSpeed<0) ball.xSpeed =-ball.xSpeed;
        else
        if (ball.x-ball.radius<x && ball.xSpeed>0) ball.xSpeed =-ball.xSpeed;
        ball.playBounce();
    }
}
