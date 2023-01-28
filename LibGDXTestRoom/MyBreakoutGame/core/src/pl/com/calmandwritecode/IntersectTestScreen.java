package pl.com.calmandwritecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;

public class IntersectTestScreen implements Screen {

    ShapeRenderer shapeRenderer;
    TestSegment leftSegment, topSegment, moveSegment,bottomSegment, rightSegment;
    BoundingBox bounds;
    Circle circle;
    Vector2 intersectPoint;
    Ray ray;
    float squaredRadius;
    float rayXend, rayYend;

    @Override
    public void show() {
        rayXend = -200;
        rayYend = - 200;
        ray = new Ray();
        intersectPoint = new Vector2();
        circle = new Circle(400,400,20);
        shapeRenderer = new ShapeRenderer();
        leftSegment = new TestSegment(new Vector2(300,300),new Vector2(600,300));
        topSegment = new TestSegment(new Vector2(300,300),new Vector2(300,100));
        moveSegment = new TestSegment(new Vector2(400,600),new Vector2(200,400));
        bottomSegment = new TestSegment(new Vector2(300,100), new Vector2(600,100));
        rightSegment = new TestSegment(new Vector2(600,100), new Vector2(600,300));
        bounds = new BoundingBox(new Vector3(bottomSegment.start.x,bottomSegment.start.y,0),new Vector3(rightSegment.end.x,rightSegment.end.y,0));
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
        shapeRenderer.line(bottomSegment.start,bottomSegment.end);
        shapeRenderer.line(rightSegment.start,rightSegment.end);
        shapeRenderer.line(moveSegment.start,moveSegment.end);
        shapeRenderer.circle(circle.x, circle.y, circle.radius);
        shapeRenderer.circle(intersectPoint.x, intersectPoint.y,4);
        shapeRenderer.end();

        circle.setPosition(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
        moveSegment.start.set(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
        moveSegment.end.set(Gdx.input.getX()+rayXend,Gdx.graphics.getHeight()-Gdx.input.getY()+rayYend);

        if (Intersector.intersectSegments(moveSegment.start,moveSegment.end,topSegment.start,topSegment.end,intersectPoint)) shapeRenderer.setColor(Color.MAGENTA);
        else
        if (Intersector.intersectSegments(moveSegment.start,moveSegment.end,bottomSegment.start,bottomSegment.end,intersectPoint)) shapeRenderer.setColor(Color.RED);
        else
        if (Intersector.intersectSegments(moveSegment.start,moveSegment.end,leftSegment.start,leftSegment.end,intersectPoint)) shapeRenderer.setColor(Color.GREEN);
        else
        if (Intersector.intersectSegments(moveSegment.start,moveSegment.end,rightSegment.start,rightSegment.end,intersectPoint)) shapeRenderer.setColor(Color.BLUE);
        else
            shapeRenderer.setColor(Color.WHITE);

        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            rayYend+=1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            rayYend-=1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            rayXend+=1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            rayXend-=1;
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
