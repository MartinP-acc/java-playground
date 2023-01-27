package pl.com.calmandwritecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

public class IntersectTestScreen implements Screen {

    ShapeRenderer shapeRenderer;
    TestSegment leftSegment, topSegment, moveSegment;
    Circle circle;
    float squaredRadius;

    @Override
    public void show() {

        circle = new Circle(400,400,20);
        shapeRenderer = new ShapeRenderer();
        leftSegment = new TestSegment(new Vector2(300,300),new Vector2(600,300));
        topSegment = new TestSegment(new Vector2(300,300),new Vector2(300,100));
        moveSegment = new TestSegment(new Vector2(400,600),new Vector2(200,400));
        squaredRadius = (circle.radius*circle.radius);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.begin();
        shapeRenderer.line(leftSegment.start,leftSegment.end);
        shapeRenderer.line(topSegment.start,topSegment.end);
        shapeRenderer.circle(circle.x, circle.y, circle.radius);
        shapeRenderer.end();

        circle.setPosition(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());

        if (Intersector.intersectSegmentCircle(leftSegment.start,leftSegment.end,new Vector2(circle.x,circle.y),squaredRadius)){
            shapeRenderer.setColor(Color.RED);
        }else if (Intersector.intersectSegmentCircle(topSegment.start,topSegment.end,new Vector2(circle.x,circle.y),squaredRadius)){
            shapeRenderer.setColor(Color.GREEN);
        }else {
            shapeRenderer.setColor(Color.YELLOW);
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
