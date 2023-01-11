package pl.com.calmandwritecode;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Brick {

    int x,y,xSize,ySize;
    boolean destroyed;

    public Brick(int x, int y) {
        this.x = x;
        this.y = y;
        this.xSize = 60;
        this.ySize = 20;
    }

    public void draw(ShapeRenderer shape){
        shape.rect(x,y,xSize,ySize);
    }

    public void update(Ball ball){
        if (collideWith(ball)){
            if (ball.y < y+5 || ball.y > y+ySize-5) ball.ySpeed = - ball.ySpeed;
            if (ball.x <= x+5 && ball.xSpeed>0) ball.xSpeed = -ball.xSpeed;


            if (ball.x >= x+xSize-5 && ball.xSpeed<0) ball.xSpeed = -ball.xSpeed;

            destroyed = true;
        }
    }

    private boolean collideWith(Ball ball) {
        return ball.x + ball.radius >= x && ball.x - ball.radius <= x+xSize
                && ball.y + ball.radius >= y && ball.y - ball.radius <= y+ySize;
    }
}
