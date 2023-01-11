package pl.com.calmandwritecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle {

    int x;
    int y = 20;
    int xSize = 100;
    int ySize = 8;
    Color color = Color.WHITE;

    public Paddle(int x){
        this.x = x;

    }

    public void update (){
        x = Gdx.input.getX()- xSize/2;
        if (x<0) x=0;
        if (x>Gdx.graphics.getWidth()-xSize) x=Gdx.graphics.getWidth()-xSize;
    }

    public void draw(ShapeRenderer shape){
        shape.rect(x,y,xSize,ySize);
        shape.setColor(color);
    }

    public void checkCollision(Ball ball){
        if (collideWith(ball)){
            ball.ySpeed = -ball.ySpeed;
            int leftX = x + xSize/4;
            int rightX = x + 3*xSize/4;

            if (ball.x <= x+5 && ball.xSpeed>0) ball.xSpeed = -ball.xSpeed;
            else
            if (ball.x < leftX) ball.xSpeed -= 2;

            if (ball.x >= x+xSize-5 && ball.xSpeed<0) ball.xSpeed = -ball.xSpeed;
            else
            if (ball.x > rightX) ball.xSpeed += 2;
        }
    }

    private boolean collideWith(Ball ball) {
        return ball.x + ball.radius >= x && ball.x - ball.radius <= x+xSize
                && ball.y + ball.radius >= y && ball.y - ball.radius <= y+ySize;
    }
}
