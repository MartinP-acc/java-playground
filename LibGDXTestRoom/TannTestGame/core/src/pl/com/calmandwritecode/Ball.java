package pl.com.calmandwritecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {

    int x;
    int y;
    int radius;
    int xSpeed;
    int ySpeed;

    public Ball(int x, int y, int radius, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public void update(){
        x += xSpeed;
        y += ySpeed;

        if (x<radius || x> Gdx.graphics.getWidth()-radius) xSpeed = -xSpeed;
        if (y<radius || y> Gdx.graphics.getHeight()-radius) ySpeed = -ySpeed;
    }

    public void draw(ShapeRenderer shape){
        shape.circle(x,y,radius);
    }
}
