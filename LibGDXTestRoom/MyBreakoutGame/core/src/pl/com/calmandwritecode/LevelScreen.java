package pl.com.calmandwritecode;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import jdk.jfr.internal.LogLevel;

import java.util.Iterator;

public class LevelScreen implements Screen {

    private final float WIDTH;
    private final float HEIGHT;

    private BreakoutGame game;
    private OrthographicCamera camera;

    private Ball ball;
    private Paddle paddle;
    private Array<Brick> bricks;
    private Texture brickTexture;
    private Texture brickTexture2;
    private ShapeRenderer shapeRenderer;

    public LevelScreen(BreakoutGame game) {
        this.game = game;
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,WIDTH,HEIGHT);
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        ball = new Ball(WIDTH,HEIGHT);
        paddle = new Paddle(WIDTH);

        shapeRenderer = new ShapeRenderer();
        bricks = new Array<>();
        brickTexture = new Texture(Gdx.files.internal("brick4.png"));
        brickTexture2 = new Texture(Gdx.files.internal("brick6.png"));
        LevelBuilder builder = new LevelBuilder();
        bricks = builder.buildFromString(LevelBuilder.LEVEL3);

        //bricks.add(new Brick(300,400,brickTexture));
        //createLevel();
    }

    private void createLevel() {
        for (int x=30; x<WIDTH-99; x+=300){
            for (int y=650; y>HEIGHT/2; y-=35){
                bricks.add(new Brick(x,y,brickTexture));
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        ball.draw(game.batch);
        paddle.draw(game.batch);
        drawBricks(game.batch);
        findBrickCollision();
        game.batch.end();
        paddle.collision(ball);
        paddle.update();
        ball.update();

        if (ball.y<=0){
            game.setScreen(new WelcomeScreen(game));
            dispose();
        }
    }

    private void drawBricks(SpriteBatch batch) {
        for (Brick brick : bricks){
            brick.draw(batch);
        }
    }

    private void findBrickCollision() {
        float distance = Float.MAX_VALUE;
        float currentDistance;
        Brick closest = null;
        Circle circle = new Circle(ball.x+ ball.xSpeed, ball.y +ball.ySpeed, ball.radius);
        for (Brick brick : bricks){
            if (Intersector.overlaps(ball,brick)){
                currentDistance = ball.position.dst(brick.center);
                if (currentDistance<distance) {
                    distance = currentDistance;
                    closest = brick;
                }
            }
        }
        if (closest != null){
            closest.collision(ball);
            bricks.removeIndex(bricks.indexOf(closest,true));
            //findBrickCollision();
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
        ball.dispose();
    }
}
